package ProCore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import ProCore.Formula.ArtigoFormula;

public class Core {
	public ArrayList<Artigo> Artigos = new ArrayList<Artigo>();
	public ArrayList<Formula> Formulas = new ArrayList<Formula>();
	public ArrayList<Vasilha> Vasilhas = new ArrayList<Vasilha>();

	public Core() {
	}

	public void AddArtigo(int Cod, String Name, Unidades UNID, double Saldo,
			double GrauAlcol, double Densidade, double TaxaRendimento) {
		Artigos.add(new Artigo(Cod, Name, UNID, Saldo, GrauAlcol, Densidade,
				TaxaRendimento));
	}
	public void AddFormula(Formula F){	
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
					throw new MyException("Error in File: 'Artigos' in Line: "
							+ Line);
				} else {
					try {
						Cod = Integer.parseInt(LineData[0]);
						Name = LineData[1];

						// kg, l, ml, g, un
						try {
							Uni = Unidades.valueOf((LineData[2].toLowerCase()));
						} catch (IllegalArgumentException e) {
							LerFich.close();
							throw new MyException(
									"Error (The selected (kg, l, ml, g, un) unit does not exist) in File: 'Artigos' in Line: "
											+ Line);
						}

						Saldo = Double.parseDouble(LineData[3]);
						GrauAlcol = Double.parseDouble(LineData[4]);
						Densidade = Double.parseDouble(LineData[5]);
						TaxaRendimento = Double.parseDouble(LineData[6]);

						AddArtigo(Cod, Name, Uni, Saldo, GrauAlcol, Densidade,TaxaRendimento);
						
					} catch (NumberFormatException ex) {
						LerFich.close();
						throw new MyException(
								"Error (converting values) in File: 'Artigos' in Line: "
										+ Line);
					}
				}
			}
			LerFich.close();
		} catch (FileNotFoundException e1) {
			throw new MyException("Error Finding the file 'Artigos.txt'");
		}
	}
	
	public void AddFormulasFile() throws MyException {// (ordem crescente nos ficheiros para a verificaçao dos artigos ser mais rapida) 
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
						for(int x=1;x< (LineData.length)-1; x+=2){
							F.add(Double.parseDouble(LineData[x]),Integer.parseInt(LineData[x+1]));
						}
						AddFormula(F);
						ArtForm.clear();
					} catch (NumberFormatException ex) {
						LerFich.close();
						throw new MyException(
								"Error (converting values) in File: 'Form' in Line: "
										+ Line);
					}
				}
			}
			LerFich.close();
		} catch (FileNotFoundException e1) {
			throw new MyException("Error Finding the file 'Form.txt'");
		}
	}
	public static void main(String[] args) throws MyException{

		Core c = new Core();
		c.AddArtigosFile();
		c.AddFormulasFile();
		for (Artigo DataA : c.Artigos) {
			System.out.println(DataA);
		}
		for (Formula DataF : c.Formulas) {
			System.out.println(DataF);
		}
	}
}
