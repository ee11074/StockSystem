package ProCore;

import java.util.ArrayList;

// Notas:
// IMPORTANTE: se o artigo nao tiver formula devolve empty array list

public class Formula {
	private int CodArtFabric;
	private ArrayList<ArtigoFormula> ArrArt = new ArrayList<ArtigoFormula>();

	public Formula(int CodArtFabric) {
		this.CodArtFabric = CodArtFabric;
	}

	// getters -------------------------------------------------------------------------------------

	public int getCodArtFabric() {
		return CodArtFabric;
	}

	public ArrayList<ArtigoFormula> getForm() {// returns Empty ArrayList if the article does not have form
		// and all allocates new memory for all the elements in the array list for the changes made in the dialogs to track the user don't affect the inside values
		ArrayList<ArtigoFormula> NewArr = new ArrayList<ArtigoFormula>();
		for (ArtigoFormula ArtFor : ArrArt) {
			NewArr.add(new ArtigoFormula(ArtFor));
		}
		return ArrArt;
	}

	// setters -------------------------------------------------------------------------------------
	public void setCodArtFabric(int codArtFabric) {
		CodArtFabric = codArtFabric;
	}

	public void setArrArt(ArrayList<ArtigoFormula> arrArt) {
		ArrArt = arrArt;
	}

	public void add(double quantidade, int CodArt) {
		ArrArt.add(new ArtigoFormula(quantidade, CodArt));
	}

	@Override
	public String toString() {
		String msg = "Formula [CodArtFabric=" + CodArtFabric + ", Arr = [ ";
		for (ArtigoFormula Arrt : ArrArt) {
			msg += Arrt.toString();
		}
		msg += " ]";
		return msg;
	}

	// CLASS *****************************************************************
	public class ArtigoFormula {
		private double quantidade;
		private int CodArt;

		public ArtigoFormula(double quantidade, int CodArt) {
			this.quantidade = quantidade;
			this.CodArt = CodArt;
		}

		public ArtigoFormula(ArtigoFormula a) {
			this.quantidade = a.getQuantidade();
			this.CodArt = a.getCodArt();
		}

		@Override
		public String toString() {
			return " [ quantidade=" + quantidade + ", CodArt=" + CodArt + " ]";
		}

		public double getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(double quantidade) {
			this.quantidade = quantidade;
		}

		public int getCodArt() {
			return CodArt;
		}

		public void setCodArt(int codArt) {
			CodArt = codArt;
		}
	}
	// CLASS *****************************************************************
}
