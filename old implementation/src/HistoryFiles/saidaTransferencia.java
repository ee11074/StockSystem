package HistoryFiles;

import java.io.Serializable;
import java.util.ArrayList;

public class saidaTransferencia extends Saidas {
	private static final long serialVersionUID = 1L;

	public class Trans implements Serializable {
		private static final long serialVersionUID = 1L;

		protected double Quantidade;
		protected int Vasilha;

		public Trans(double Quantidade, int Vasilha) {
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
			return "Transferencia [Quantidade=" + Quantidade + ", Vasilha=" + Vasilha
					+ "]";
		}
	}

	protected ArrayList<Trans> dataTrans = new ArrayList<Trans>();

	public saidaTransferencia(long IdTrans, String dateTrans, int IdArtigoTrans) {
		super(IdTrans, dateTrans, IdArtigoTrans);
	}
}