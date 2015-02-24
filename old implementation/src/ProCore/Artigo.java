package ProCore;

// Notas :
//private boolean FichTXT;// true -> passa a formula para ficheiro.txt como backup // false

public class Artigo {
	private int Cod;
	private String Name;
	private Unidades UNID;
	private double Saldo;
	private double GrauAlcol;
	private double Densidade;
	private double TaxaRendimento;

	public Artigo(int Cod, String Name, Unidades UNID, double Saldo, double GrauAlcol, double Densidade, double TaxaRendimento) {
		setCod(Cod);
		setName(Name);
		setUNID(UNID);
		setSaldo(Saldo);
		setGrauAlcol(GrauAlcol);
		setDensidade(Densidade);
		setTaxaRendimento(TaxaRendimento);
	}

	// *************************************************************************
	public int getCod() {
		return Cod;
	}

	public String getName() {
		return Name;
	}

	public Unidades getUNID() {
		return UNID;
	}

	public double getSaldo() {
		return Saldo;
	}

	public double getGrauAlcol() {
		return GrauAlcol;
	}

	public double getDensidade() {
		return Densidade;
	}

	public double getTaxaRendimento() {
		return TaxaRendimento;
	}

	// *************************************************************************
	public void setName(String name) {
		Name = name;
	}

	public void setCod(int cod) {
		Cod = cod;
	}

	public void setUNID(Unidades uNID) {
		UNID = uNID;
	}

	public void setSaldo(double saldo) {
		Saldo = saldo;
	}

	public void setGrauAlcol(double grauAlcol) {
		GrauAlcol = grauAlcol;
	}

	public void setDensidade(double densidade) {
		Densidade = densidade;
	}

	public void setTaxaRendimento(double taxaRendimento) {
		TaxaRendimento = taxaRendimento;
	}

	// *************************************************************************

	@Override
	public String toString() {// by default o to string so mostra o nome
		return Name;
	}

}
