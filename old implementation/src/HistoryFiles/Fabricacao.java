package HistoryFiles;

import java.io.Serializable;
import java.util.ArrayList;

public class Fabricacao implements Serializable{
	private static final long serialVersionUID = 1L;

	public class Destino implements Serializable{
		private static final long serialVersionUID = 1L;
		
		protected double Quantidade;
		protected int Vasilha;

		
		public Destino(double Quantidade,int Vasilha){
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
			return "Destino [Quantidade=" + Quantidade + ", Vasilha=" + Vasilha + "]";
		}

	}

	public class ArtigoForm extends Destino implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private int artigoId;

		public ArtigoForm(int artigoId, double Quantidade, int Vasilha) {
            super(Quantidade,Vasilha);  
			setArtigoId(artigoId);
		}

		public int getArtigoId() {
			return artigoId;
		}

		public void setArtigoId(int artigoId) {
			this.artigoId = artigoId;
		}

		@Override
		public String toString() {
			return "ArtigoForm [artigoId=" + artigoId + "]";
		}
	}

	private long IdFabri;
	private String dateFabri;
	private int IdArtigoFabric;
	private ArrayList<ArtigoForm> ArtigosProveniente = new ArrayList<ArtigoForm>();
	private double Redimento;
	private ArrayList<Destino> VasDestino = new ArrayList<Destino>();
	private float Preco_Ultimo;
	private float Preco_Medio;

	public Fabricacao(long IdFabri, String dateFabri, int IdArtigoFabric, double Redimento, float Preco_Ultimo, float Preco_Medio) {
       setIdFabri(IdFabri);
       setDateFabri(dateFabri);
       setIdArtigoFabric(IdArtigoFabric);
       setRedimento(Redimento);
       setPreco_Medio(Preco_Medio);
       setPreco_Ultimo(Preco_Ultimo);
	}

	// sets an gets --------------------------------------------------------------
	public long getIdFabri() {
		return IdFabri;
	}

	public void setIdFabri(long idFabri) {
		IdFabri = idFabri;
	}

	public String getDateFabri() {
		return dateFabri;
	}

	public void setDateFabri(String dateFabri) {
		this.dateFabri = dateFabri;
	}

	public int getIdArtigoFabric() {
		return IdArtigoFabric;
	}

	public void setIdArtigoFabric(int idArtigoFabric) {
		IdArtigoFabric = idArtigoFabric;
	}

	public ArrayList<ArtigoForm> getArtigosProveniente() {
		return ArtigosProveniente;
	}

	public void setArtigosProveniente(ArrayList<ArtigoForm> artigosProveniente) {
		ArtigosProveniente = artigosProveniente;
	}

	public double getRedimento() {
		return Redimento;
	}

	public void setRedimento(double redimento) {
		Redimento = redimento;
	}

	public ArrayList<Destino> getVasDestino() {
		return VasDestino;
	}

	public void setVasDestino(ArrayList<Destino> vasDestino) {
		VasDestino = vasDestino;
	}

	public float getPreco_Ultimo() {
		return Preco_Ultimo;
	}

	public void setPreco_Ultimo(float preco_Ultimo) {
		Preco_Ultimo = preco_Ultimo;
	}

	public float getPreco_Medio() {
		return Preco_Medio;
	}

	public void setPreco_Medio(float preco_Medio) {
		Preco_Medio = preco_Medio;
	}

	// --------------------------------------------------------------

	@Override
	public String toString() {
		return "Fabricacao [IdFabri=" + IdFabri + ", dateFabri=" + dateFabri + ", IdArtigoFabric=" + IdArtigoFabric + ", ArtigosProveniente=" + ArtigosProveniente + ", Redimento=" + Redimento + ", VasDestino=" + VasDestino + ", Preco_Ultimo=" + Preco_Ultimo + ", Preco_Medio=" + Preco_Medio + "]";
	}

}
