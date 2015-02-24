package ProCore;

// Notas:
// unidades das vasilhas devem ser sempre as mesmas +erguntar depois...

public class Vasilha {
	private int IdVasilha;
	private double Capacidade;
	private double Quantidade;
	private int CodArtigo; // artigo associado a vasilha xD

	public Vasilha(int IdVasilha, double Capacidade, double Quantidade, int CodArtigo) {
          setVasilhaId(IdVasilha);
          setCapacidade(Capacidade);
          setQuantidade(Quantidade);
          setCodArtigo(CodArtigo);
	}

	public int getIdVasilha() {
		return IdVasilha;
	}

	public double getCapacidade() {
		return Capacidade;
	}

	public double getQuantidade() {
		return Quantidade;
	}

	public int getCodArtigo() {
		return CodArtigo;
	}

	public void setVasilhaId(int id) {
		IdVasilha = id;
	}

	public void setCapacidade(double capacidade) {
		Capacidade = capacidade;
	}

	public void setQuantidade(double quantidade) {
		Quantidade = quantidade;
	}

	public void setCodArtigo(int codArtigo) {
		CodArtigo = codArtigo;
	}

	@Override
	public String toString() {
		return IdVasilha + "";
	}	
}
