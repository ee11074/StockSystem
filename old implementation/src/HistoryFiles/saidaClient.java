package HistoryFiles;

import java.io.Serializable;
import java.util.ArrayList;

public class saidaClient extends Saidas {
	private static final long serialVersionUID = 1L;

	public class Client implements Serializable {
		private static final long serialVersionUID = 1L;

		protected double Quantidade;
		protected int Vasilha;

		public Client(double Quantidade, int Vasilha) {
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
			return "Client [Quantidade=" + Quantidade + ", Vasilha=" + Vasilha
					+ "]";
		}
	}

	protected ArrayList<Client> dataClient = new ArrayList<Client>();

	public saidaClient(long IdTrans, String dateTrans, int IdArtigoTrans) {
		super(IdTrans, dateTrans, IdArtigoTrans);

	}
}