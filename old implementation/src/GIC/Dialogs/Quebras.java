package GIC.Dialogs;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import BaseDialog.MyBaseDialog;
import Core.Core;
import MyTableModel.MyTableModel;
import ProCore.Artigo;
import ProCore.Vasilha;

// Nota:

// SUPER IMPORTANTE -> ver o tipo de quebra "SE todas as inseridas forem o mesmo tipo de quebra avisar o user"
// ou como eu estava a pensar ao adicionar uma vasilha a tabela fica com um tipo de quebra associado pois assim podesse fazer as quebras para um artigo todas de uma vez
// de qualquer das formas e necessario avisar o user de como funciona 

// ver qual o tipo de unidades se sao as do artigo ou da capcidade da vasilha pois tem de se ver o problema

// the sum of the quantities of the quebras can not pass the quantity produced or can...(Only quebra)
public class Quebras extends MyBaseDialog {
	private static final long serialVersionUID = 1L;

	// Radio buttons --------------------------------------
	private JLabel lblTipoDeQuebra;

	private ButtonGroup Button_Group;

	private JRadioButton rdbtnManuseamento;
	private final String Manuseamento_Button_String = "Manuseamento";

	private JRadioButton rdbtnDerrame;
	private final String Derrame_Button_String = "Derrame";

	private JRadioButton rdbtnOutra;
	private final String Outra_Button_String = "Outra";

	// Table
	private JTable table;
	private String ColData[] = new String[] { "Vasilha", "Quantidade", "Unidades", "Tipo Quebra" };

	// add vasilha -------------------------------------------

	private JComboBox<Vasilha> comboBox_AddVasilha;
	private JButton btn_AddVasilha;

	// remove vasilha ----------------------------------------

	private JButton btn_RemoveVasilha;

	// constructor -----------------------------------------
	public Quebras(JFrame pai, Core dataBase) {
		super(pai, dataBase, "Quebras");

		// *********************************************************************
		// ************** adding actionListener to comboBoxArtigo **************
		// *********************************************************************
		comboBoxArtigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act) {
				Artigo artigo = (Artigo) comboBoxArtigo.getSelectedItem();

				if (artigo != null) {
					// show artigo information in the TextFields from above
					textField_Codigo.setText(artigo.getCod() + "");
					textField_Unidades.setText(artigo.getUNID().toString());
					textField_Saldo.setText(artigo.getSaldo() + "");
					textField_GrauAlcool.setText(artigo.getGrauAlcol() + "");
					textField_Densidade.setText(artigo.getDensidade() + "");
					textField_TaxaRendimento.setText(artigo.getTaxaRendimento() + "");

					lblUnidades_Quantidade_Artigo_Selecionado.setText(artigo.getUNID().toString());
					textField_QuantidadeProduzida.setText("0");

					ArrayList<Vasilha> Vasilhas = DataBase.getVasilhas_Artigo(artigo.getCod());

					comboBox_AddVasilha.removeAllItems();// remove todos os artigos da combo box das vasilhas para nao haver ca espigas xD
					if (Vasilhas.isEmpty()) { // Artigo out of Stock
						((MyTableModel) table.getModel()).deleteData();// deletes table data
						JOptionPane.showMessageDialog(Quebras.this, "Nao existem Vasilhas com o artigo \"" + artigo.toString() + "\" em stock!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
					} else {
						for (Vasilha vas : Vasilhas)
							comboBox_AddVasilha.addItem(vas);// adding vasilhas to the combobox

						// seting cell true "editable"
						// ------------------------------------------
						((MyTableModel) table.getModel()).setColumCellEditor(1, true);
						// ----------------------------------------------------------------------

						initColumnSizes(table, ColData.length);// modifica�ao de alguns parametros defaulda das colunsa da table

					}
				} else {
					ResetAllData();// Makes all the boxes clean and tables to
					JOptionPane.showMessageDialog(Quebras.this, "Artigo Selecionado invalido!", "Mansagem de erro!", JOptionPane.PLAIN_MESSAGE);
				}
				table.repaint();
			}
		});
		// ***************************************************************************************************
		// ************** RADIO BUTTON **************
		// ***************************************************************************************************
		lblTipoDeQuebra = new JLabel("Tipo de Quebra: ");
		lblTipoDeQuebra.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTipoDeQuebra.setBounds(10, 192, 123, 20);
		ContentPanel.add(lblTipoDeQuebra);

		Button_Group = new ButtonGroup();// to control all the jRadioButton because i only want to select one at the time

		rdbtnManuseamento = new JRadioButton(Manuseamento_Button_String);
		rdbtnManuseamento.setBounds(10, 219, 123, 23);
		Button_Group.add(rdbtnManuseamento);
		ContentPanel.add(rdbtnManuseamento);

		rdbtnDerrame = new JRadioButton(Derrame_Button_String);
		rdbtnDerrame.setBounds(147, 219, 95, 23);
		Button_Group.add(rdbtnDerrame);
		ContentPanel.add(rdbtnDerrame);

		rdbtnOutra = new JRadioButton(Outra_Button_String);
		rdbtnOutra.setSelected(true); // this button is the one that is selected by default
		rdbtnOutra.setBounds(247, 219, 109, 23);
		Button_Group.add(rdbtnOutra);
		ContentPanel.add(rdbtnOutra);

		// tabela ------------------------------------

		JPanel Destino_Panel = new JPanel(new BorderLayout());
		Destino_Panel.setBounds(10, 269, 630, 181);
		ContentPanel.add(Destino_Panel);

		// Creating JTable_Destino ---------------------------
		table = new JTable(new MyTableModel(ColData));
		table.setFillsViewportHeight(true);// para encher o espaco do panel
		table.setRowHeight(25); // to make the table rows have the default height of 25

		JScrollPane scrollPane_Table = new JScrollPane(table);
		scrollPane_Table.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Destino_Panel.add(scrollPane_Table, BorderLayout.CENTER);

		// add vasilha -------------------------------------------
		comboBox_AddVasilha = new JComboBox<Vasilha>();
		comboBox_AddVasilha.setBounds(10, 475, 102, 23);
		ContentPanel.add(comboBox_AddVasilha);

		btn_AddVasilha = new JButton("Add Vasilha");
		btn_AddVasilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Artigo artigo = (Artigo) comboBoxArtigo.getSelectedItem();
				if (artigo == null) {// if the user don't select one Artigo from the comboBox
					ResetAllData();
					JOptionPane.showMessageDialog(Quebras.this, "Nao Foi Selecionado nenhum Artigo!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
				} else {
					Vasilha vas = (Vasilha) comboBox_AddVasilha.getSelectedItem();
					if (vas == null) {// if the user don't select one vasilha from the comboBox
						JOptionPane.showMessageDialog(Quebras.this, "Nao exitem mais Vasilhas com o artigo \"" + artigo.toString() + "\" em stock!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
					} else {// can add selected vasilha
						Object[] dataRow = new Object[4];
						dataRow[0] = vas;// to string automaticamente e assim e mais facil selecionar a vasilha
						dataRow[1] = new Double(0);
						dataRow[2] = artigo.getUNID().toString();
						dataRow[3] = getSelectedButtonText();// it has the string for from the selected radio button

						((MyTableModel) table.getModel()).addRow(dataRow);

						comboBox_AddVasilha.removeItem(vas);
					}
				}
				table.repaint();
			}
		});
		btn_AddVasilha.setBounds(128, 475, 109, 23);
		ContentPanel.add(btn_AddVasilha);

		// remove vasilha ----------------------------------------
		btn_RemoveVasilha = new JButton("Remove Selected Vasilha");
		btn_RemoveVasilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() < 0) {// if there is not any tow selected the table.getSelectedRow() returns -1 as default
					JOptionPane.showMessageDialog(Quebras.this, "Nao Foi Selecionada nenhuma vasilha da tabela para remover!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
				} else {
					Object vas = ((MyTableModel) table.getModel()).getValueAt(table.getSelectedRow(), 0);
					((MyTableModel) table.getModel()).RemoveRow_PosArray(table.getSelectedRow()); // Gives the selected arraylist pos
					if (vas instanceof Vasilha) {
						comboBox_AddVasilha.addItem((Vasilha) vas);
					}
				}
			}
		});
		btn_RemoveVasilha.setBounds(10, 510, 180, 23);
		ContentPanel.add(btn_RemoveVasilha);

		// ***************************************************************************************************
		// ************** but�es em baixo ActionListener for the save button **************
		// ***************************************************************************************************

	}

	// ***************************************************************************************************
	// ************************************* Important Methods *******************************************
	// ***************************************************************************************************

	private void ResetAllData() {
		textField_Codigo.setText("");
		textField_Unidades.setText("");
		textField_Saldo.setText("");
		textField_GrauAlcool.setText("");
		textField_Densidade.setText("");
		textField_TaxaRendimento.setText("");

		lblUnidades_Quantidade_Artigo_Selecionado.setText("");
		textField_QuantidadeProduzida.setText("");

		MyTableModel model1 = (MyTableModel) table.getModel();
		model1.deleteData();

		comboBox_AddVasilha.removeAllItems();
	}

	public String getSelectedButtonText() {
		for (Enumeration<AbstractButton> buttonsInGroups = Button_Group.getElements(); buttonsInGroups.hasMoreElements();) {
			AbstractButton button = buttonsInGroups.nextElement();
			if (button.isSelected()) {
				return button.getText();
			}
		}
		return "";
	}
}
