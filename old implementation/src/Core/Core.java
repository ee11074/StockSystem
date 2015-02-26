package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Scanner;

import HistoryFiles.Fabricacao;
import HistoryFiles.Fabricacao.ArtigoForm;
import HistoryFiles.Fabricacao.Destino;
import MyException.MyException;
import ProCore.Artigo;
import ProCore.Formula;
import ProCore.Unidades;
import ProCore.Vasilha;
import ProCore.Formula.ArtigoFormula;
// Notas:
// -> ver se encontro Maneiras de fazer procuras mais rapidas
// -> verificar se nao existem artigos iguais ao ler os artigos
// -> talvez (ordem crescente nos ficheiros para a verificaçao dos artigos ser mais rapida)verificar se os artigo das formulas existem
// verificar se nao existem vasilhas repetidas e todos os artigos das vasilhas existem
// ir gardando os dados em ficheiros ou guardar o historico pois se o computador for abaixo ao fazer durante o programa perdence ambos os dados(ou gurdar tudo so no fim)
public class Core {
	public ArrayList<Artigo> Artigos = new ArrayList<Artigo>();
	public ArrayList<Formula> Formulas = new ArrayList<Formula>();
	public ArrayList<Vasilha> Vasilhas = new ArrayList<Vasilha>();

	public Core() throws MyException {
		AddArtigosFile();
		AddFormulasFile();
		AddVasilhasFile();
	}

	// get Artigos
	// ----------------------------------------------------------------------------
	public ArrayList<Artigo> getArtigos() {
		return Artigos;
	}

	public ArrayList<Artigo> getArtigosFabricados() {
		ArrayList<Artigo> ArrayArtigo = new ArrayList<Artigo>();
		for (Artigo Art : Artigos) {
			if (!getFormula_ID_art(Art.getCod()).isEmpty())
				ArrayArtigo.add(Art);
		}
		return ArrayArtigo;
	}

	public Artigo[] getArtigosArray() {// to get in array form for the combo box
		return Artigos.toArray(new Artigo[Artigos.size()]);
	}

	public Artigo getArtigoObject(int cod) {// devolve dados de um artigo de um formula
		for (Artigo Art : Artigos) {
			if (Art.getCod() == cod) {
				return Art;
			}
		}
		return null;
	}

	// get Formulas
	// ---------------------------------------------------------------------------

	public ArrayList<Formula> getFormulas() {
		return Formulas;
	}

	public ArrayList<ArtigoFormula> getFormula_ID_art(int cod) {// returns empty array if does not have a formula
		for (Formula form : Formulas) {
			if (form.getCodArtFabric() == cod) {
				return form.getForm();// the getFrom Allocates new memory for the changes out side don't affect the value inside
			}
		}
		return new ArrayList<Formula.ArtigoFormula>();
	}

	// get Vasilhas
	// ---------------------------------------------------------------------------

	public ArrayList<Vasilha> getVasilhas() {
		return Vasilhas;
	}

	public ArrayList<Vasilha> getVasilhas_Artigo(int cod) {// devolve Array list com vasilhas onde se o artigo
		ArrayList<Vasilha> Vs = new ArrayList<Vasilha>();
		for (Vasilha Vas : Vasilhas) {
			if (Vas.getCodArtigo() == cod) {
				Vs.add(Vas);
			}
		}
		return Vs;
	}

	// ---------------------------------------------------------------------------

	public void AddArtigo(int Cod, String Name, Unidades UNID, double Saldo, double GrauAlcol, double Densidade, double TaxaRendimento) {
		Artigos.add(new Artigo(Cod, Name, UNID, Saldo, GrauAlcol, Densidade, TaxaRendimento));
	}

	public void AddFormula(Formula F) {
		Formulas.add(F);
	}

	public void AddArtigosFile() throws MyException {
		Scanner LerFich;
		try {
			LerFich = new Scanner(new File("Resources/TextFiles/Artigos.txt"));

			String LineData[];
			int Line = 0;

			int Cod;
			String Name;
			Unidades Uni;
			double Saldo;
			double GrauAlcol;
			double Densidade;
			double TaxaRendimento;

			while (LerFich.hasNext()) {
				LineData = LerFich.nextLine().split(";");
				Line++;

				if (LineData.length != 7) {
					LerFich.close();
					throw new MyException("Error in File: 'Artigos' in Line: " + Line);
				} else {
					try {
						Cod = Integer.parseInt(LineData[0]);
						Name = LineData[1];

						// kg, l, ml, g, un
						try {
							Uni = Unidades.valueOf((LineData[2].toLowerCase()));
						} catch (IllegalArgumentException e) {
							LerFich.close();
							throw new MyException("Error (The selected (kg, l, ml, g, un) unit does not exist) in File: 'Artigos' in Line: " + Line);
						}

						Saldo = Double.parseDouble(LineData[3]);
						GrauAlcol = Double.parseDouble(LineData[4]);
						Densidade = Double.parseDouble(LineData[5]);
						TaxaRendimento = Double.parseDouble(LineData[6]);

						AddArtigo(Cod, Name, Uni, Saldo, GrauAlcol, Densidade, TaxaRendimento);

					} catch (NumberFormatException ex) {
						LerFich.close();
						throw new MyException("Error (converting values) in File: 'Artigos' in Line: " + Line);
					}
				}
			}
			LerFich.close();
		} catch (FileNotFoundException e1) {
			throw new MyException("Error Finding the file 'Artigos.txt'");
		}
	}

	public void AddFormulasFile() throws MyException {
		Scanner LerFich;
		try {
			LerFich = new Scanner(new File("Resources/TextFiles/Form.txt"));

			String LineData[];
			int Line = 0;

			int Cod;
			Formula F;
			ArrayList<ArtigoFormula> ArtForm = new ArrayList<ArtigoFormula>();

			while (LerFich.hasNext()) {
				LineData = LerFich.nextLine().split(";");
				Line++;

				if (LineData.length > 0) {
					try {
						Cod = Integer.parseInt(LineData[0]);
						F = new Formula(Cod);
						for (int x = 1; x < (LineData.length) - 1; x += 2) {
							F.add(Double.parseDouble(LineData[x]), Integer.parseInt(LineData[x + 1]));
						}
						AddFormula(F);
						ArtForm.clear();
					} catch (NumberFormatException ex) {
						LerFich.close();
						throw new MyException("Error (converting values) in File: 'Form' in Line: " + Line);
					}
				}
			}
			LerFich.close();
		} catch (FileNotFoundException e1) {
			throw new MyException("Error Finding the file 'Form.txt'");
		}
	}

	public void AddVasilhasFile() throws MyException {
		Scanner LerFich;
		try {
			LerFich = new Scanner(new File("Resources/TextFiles/Vasilhas.txt"));

			String LineData[];
			int Line = 0;

			int IdVasilha;
			double Capacidade;
			double Quantidade;
			int CodArtigo;

			while (LerFich.hasNext()) {
				LineData = LerFich.nextLine().split(";");
				Line++;

				if (LineData.length == 4) {
					try {
						IdVasilha = Integer.parseInt(LineData[0]);
						CodArtigo = Integer.parseInt(LineData[1]);
						Capacidade = Double.parseDouble(LineData[2]);
						Quantidade = Double.parseDouble(LineData[3]);

						Vasilhas.add(new Vasilha(IdVasilha, Capacidade, Quantidade, CodArtigo));
					} catch (NumberFormatException ex) {
						LerFich.close();
						throw new MyException("Error (converting values) in File: 'Form' in Line: " + Line);
					}
				} else {
					LerFich.close();
					throw new MyException("Error in File: 'Vasilhas' in Line: " + Line);
				}
			}
			LerFich.close();
		} catch (FileNotFoundException e1) {
			throw new MyException("Error Finding the file 'Form.txt'");
		}
	}

	// binary files output -----------------------------------------------------------------------
	public void saveFabricacoes(ArrayList<Fabricacao> ArrList) throws FileNotFoundException, IOException {
		ObjectOutputStream outP = new ObjectOutputStream(new FileOutputStream("Resources/BinaryFiles/Fabricacao.dat"));
		outP.writeObject(ArrList);
		outP.close();
	}

	// binary files input ------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public ArrayList<Fabricacao> getFabricacoes() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Fabricacao> InArr = new ArrayList<Fabricacao>();
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("Resources/BinaryFiles/Fabricacao.dat"));
		InArr.addAll((ArrayList<Fabricacao>) in.readObject());
		in.close();
		return InArr;
	}

	public static void main(String[] args) throws MyException {

		Core c = new Core();
		ArrayList<Fabricacao> Arr = new ArrayList<Fabricacao>();

		Fabricacao Fra = new Fabricacao(123, "1992/06/06", 1234, 60, 15.5f, 20.6f);

		ArrayList<ArtigoForm> ArtigosProveniente = new ArrayList<ArtigoForm>();
		ArtigosProveniente.add(Fra.new ArtigoForm(236, 5.5, 1));
		ArtigosProveniente.add(Fra.new ArtigoForm(234, 4.5, 2));
		ArrayList<Destino> VasDestino = new ArrayList<Destino>();
		VasDestino.add(Fra.new Destino(11.9, 15));

		Fra.setArtigosProveniente(ArtigosProveniente);
		Fra.setVasDestino(VasDestino);

		Arr.add(Fra);

		Fabricacao Fraa = new Fabricacao(3213, "1993/06/06", 1334, 63, 25.5f, 16.6f);

		ArrayList<ArtigoForm> ArtigosProveniente2 = new ArrayList<ArtigoForm>();
		ArtigosProveniente2.add(Fraa.new ArtigoForm(231, 5.5, 56));
		ArtigosProveniente2.add(Fraa.new ArtigoForm(123, 4.5, 45));
		ArrayList<Destino> VasDestino2 = new ArrayList<Destino>();
		VasDestino2.add(Fraa.new Destino(25.66, 466));

		Fraa.setArtigosProveniente(ArtigosProveniente2);
		Fraa.setVasDestino(VasDestino2);

		Arr.add(Fraa);

		try {
			c.saveFabricacoes(Arr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ArrayList<Fabricacao> FilesData = c.getFabricacoes();
			for (int x = 0; x < FilesData.size(); x++) {
				System.out.println(FilesData.get(x));
				ArrayList<ArtigoForm> artig = FilesData.get(x).getArtigosProveniente();
				System.out.println(artig.size());
				for (int y = 0; y < artig.size(); y++) {
					System.out.println(artig.get(y));
				}
				ArrayList<Destino> artig1 = FilesData.get(x).getVasDestino();
				for (int y = 0; y < artig1.size(); y++) {
					System.out.println(artig1.get(y));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
