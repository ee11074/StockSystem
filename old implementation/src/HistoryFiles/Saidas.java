package HistoryFiles;

import java.io.Serializable;
import java.util.ArrayList;

public class Saidas implements Serializable {
	private static final long serialVersionUID = 1L;

	public class Proveniencia implements Serializable {
		private static final long serialVersionUID = 1L;

		protected double Quantidade;
		protected int Vasilha;

		public Proveniencia(double Quantidade, int Vasilha) {
			setQuantidade(Quantidade);
			setVasilha(Vasilha);
		}

		public double getQuantidade() {
			return Quantidade;
		}

		public int getVasilha() {
			return Vasilha;
		}

		public void setQuantidade(double quantidade) {
			Quantidade = quantidade;
		}

		public void setVasilha(int vasilha) {
			Vasilha = vasilha;
		}

		@Override
		public String toString() {
			return "Proveniencia [Quantidade=" + Quantidade + ", Vasilha="
					+ Vasilha + "]";
		}
	}

	protected long IdTrans;
	protected String dateTrans;
	protected int IdArtigoTrans;
	protected ArrayList<Proveniencia> VasProveniencia = new ArrayList<Proveniencia>();
	// private float Preco_Ultimo;
	// private float Preco_Medio;

	public Saidas(long IdTrans, String dateTrans, int IdArtigoTrans) {
	       setIdTrans(IdTrans);
	       setDateTrans(dateTrans);
	       setIdArtigoTrans(IdArtigoTrans);
		}
	
	// sets an gets --------------------------------------------------------------
	public long getIdTrans() {
		return IdTrans;
	}

	public void setIdTrans(long idTrans) {
		IdTrans = idTrans;
	}

	public String getDateTrans() {
		return dateTrans;
	}

	public void setDateTrans(String dateTrans) {
		this.dateTrans = dateTrans;
	}

	public int getIdArtigoTrans() {
		return IdArtigoTrans;
	}

	public void setIdArtigoTrans(int idArtigoTrans) {
		IdArtigoTrans = idArtigoTrans;
	}

	public ArrayList<Proveniencia> getArtigosProveniencia() {
		return VasProveniencia;
	}

	public void setArtigosProveniencia(ArrayList<Proveniencia> artigosProveniencia) {
		VasProveniencia = artigosProveniencia;
	}

	// --------------------------------------------------------------

	@Override
	public String toString() {
		return "Saída/Transferência [IdTrans=" + IdTrans + ", dateTrans=" + dateTrans + ", IdArtigoTrans=" + IdArtigoTrans + ", VasProveniencia=" + VasProveniencia;
	}
}