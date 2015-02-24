package GIC.Dialogs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import BaseDialog.MyBaseDialog;
import Core.Core;
import MyTableModel.MyTableModel;
import ProCore.Artigo;
import ProCore.Vasilha;
import TableCellEditor.RowEditor;

// Notas:
//// ver qual o tipo de unidades se sao as do artigo ou da capcidade da vasilha pois tem de se ver o problema

public class SaidaTensferenciaDialog extends MyBaseDialog {
	private static final long serialVersionUID = 1L;

	// Table_Proveniencia ------------------------------------
	private JLabel lblProveniencia;
	private JTable table_Proviniencia;
	private String ColData_Proviniencia_DestinoVasilha[] = new String[] { "Vasilha", "Quantidade", "Unidades" };

	private JComboBox<Vasilha> comboBox_AddVasilha;
	private JButton btn_AddVasilha;

	private JButton btn_RemoveVasilha;

	// Table_Destino --------------------------------------
	private JLabel lblDestino;
	private JTable table_DestinoVasilha;

	// Radio Buttons
	private ButtonGroup Button_Group;
	private JRadioButton rdbtnVasilha;
	private JRadioButton rdbtnCliente;

	// card LayOut
	private JPanel Cards;// painel com card Layout

	private JPanel card1_Vasilha;
	private final String Vasilha_Button_String = "Vasilha";

	private JPanel card2_Cliente;
	private final String Cliente_Button_String = "Cliente";

	// constructor -----------------------------------------
	public SaidaTensferenciaDialog(JFrame pai, Core dataBase) {
		super(pai, dataBase, "Saida/Transferencia");

		// *********************************************************************
		// ************** adding actionListener to comboBoxArtigo **************
		// *********************************************************************
		comboBoxArtigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act) {
				Artigo artigo = (Artigo) comboBoxArtigo.getSelectedItem();

				if (artigo == null) {
					// **********************************************************
					// tratar da exception nao existem artigos
				} else {

					// show artigo information in the TextFields from above
					textField_Codigo.setText(artigo.getCod() + "");
					textField_Unidades.setText(artigo.getUNID().toString());
					textField_Saldo.setText(artigo.getSaldo() + "");
					textField_GrauAlcool.setText(artigo.getGrauAlcol() + "");
					textField_Densidade.setText(artigo.getDensidade() + "");
					textField_TaxaRendimento.setText(artigo.getTaxaRendimento() + "");

					lblUnidades_Quantidade_Artigo_Selecionado.setText(artigo.getUNID().toString());

					ArrayList<Vasilha> Vasilhas = DataBase.getVasilhas_Artigo(artigo.getCod());

					comboBox_AddVasilha.removeAllItems();// remove todos os artigos da combo box das vasilhas para nao haver ca espigas xD
					if (Vasilhas.isEmpty()) { // Artigo out of Stock
						((MyTableModel) table_Proviniencia.getModel()).deleteData();// deletes table Prveniencia data
						((MyTableModel) table_DestinoVasilha.getModel()).deleteData();// deletes table Destino Vasilha data
						JOptionPane.showMessageDialog(SaidaTensferenciaDialog.this, "Nao existem Vasilhas com o artigo \"" + artigo.toString() + "\" em stock!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
					} else {
						// ****************************** Init Table Proveniencia ******************************
						for (Vasilha vas : Vasilhas)
							comboBox_AddVasilha.addItem(vas);// adding vasilhas to the combobox

						// seting cell true "editable"
						// -----------------------------------------------------------------------
						((MyTableModel) table_Proviniencia.getModel()).setColumCellEditor(1, true);
						// ----------------------------------------------------------------------

						initColumnSizes(table_Proviniencia, ColData_Proviniencia_DestinoVasilha.length);// modificaçao de alguns parametros defaulda das colunsa da table

						table_Proviniencia.repaint();// pinta a jtable para se ver logo as mudancas

						// ****************************** init Table Destino Vasilha ******************************
						ArrayList<Object[]> data = new ArrayList<Object[]>();
						data.add(new Object[] { "", new Double(0), artigo.getUNID().toString() });

						RowEditor rowEditor = new RowEditor(table_DestinoVasilha);
						ArrayList<Vasilha> VasilhasArtForm = DataBase.getVasilhas_Artigo(artigo.getCod());
						JComboBox comboBox = new JComboBox(VasilhasArtForm.toArray());// Jcombo box comtem objectos do tipo vasilha

						rowEditor.setEditorAt(0, new DefaultCellEditor(comboBox));
						table_DestinoVasilha.getColumn("Vasilha").setCellEditor(rowEditor);

						((MyTableModel) table_DestinoVasilha.getModel()).setData(data);
						// seting cell true "editable"
						// -----------------------------------------------------------------------
						((MyTableModel) table_DestinoVasilha.getModel()).setColumCellEditor(0, true);
						// ----------------------------------------------------------------------

						initColumnSizes(table_DestinoVasilha, ColData_Proviniencia_DestinoVasilha.length);// modificaçao de alguns parametros defaulda das colunsa da table

						table_DestinoVasilha.repaint();// pinta a jtable para se ver logo as mudancas
					}
				}
			}
		});
		// ***************************************************************************************************
		// quantidade produzida --------------------------------
		lblQuantidade.setText("                Quantidade :");

		// ***************************************************************************************************
		// ************** Table Proveniencia **************
		// ***************************************************************************************************
		lblProveniencia = new JLabel("Proveniencia:");
		lblProveniencia.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblProveniencia.setBounds(10, 175, 102, 20);
		ContentPanel.add(lblProveniencia);

		// table Panel -------------------------------------------
		JPanel Destino_Panel = new JPanel(new BorderLayout());
		Destino_Panel.setBounds(10, 200, 630, 124);
		ContentPanel.add(Destino_Panel);

		// Creating JTable_Proveniecia ---------------------------
		table_Proviniencia = new JTable(new MyTableModel(ColData_Proviniencia_DestinoVasilha));
		table_Proviniencia.setFillsViewportHeight(true);// para encher o espaco do panel
		table_Proviniencia.setRowHeight(25); // to make the table rows have the default height of 25

		table_Proviniencia.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {
				System.out.println("**** Verifing Data ****");
				double soma = 0;
				MyTableModel model = (MyTableModel) table_Proviniencia.getModel();
				for (int x = 0; x < model.getRowCount(); x++) {
					Object ob = model.getValueAt(x, 1);
					if (ob instanceof Double) {// contem numero valido
						soma += ((Double) ob).doubleValue();
					}
				}
				table_DestinoVasilha.setValueAt(new Double(soma), 0, 1);
			}
		});

		JScrollPane scrollPane_TableProveniencica = new JScrollPane(table_Proviniencia);
		scrollPane_TableProveniencica.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Destino_Panel.add(scrollPane_TableProveniencica, BorderLayout.CENTER);

		// add vasilha -------------------------------------------
		comboBox_AddVasilha = new JComboBox<Vasilha>();
		comboBox_AddVasilha.setBounds(10, 335, 102, 20);
		ContentPanel.add(comboBox_AddVasilha);

		btn_AddVasilha = new JButton("Add Vasilha");
		btn_AddVasilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Artigo artigo = (Artigo) comboBoxArtigo.getSelectedItem();
				if (artigo == null) {// if the user don't select one Artigo from the comboBox
					JOptionPane.showMessageDialog(SaidaTensferenciaDialog.this, "Nao Foi Selecionado nenhum Artigo!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
				} else {
					Vasilha vas = (Vasilha) comboBox_AddVasilha.getSelectedItem();
					if (vas == null) {// if the user don't select one vasilha from the comboBox
						JOptionPane.showMessageDialog(SaidaTensferenciaDialog.this, "Nao exitem mais Vasilhas com o artigo \"" + artigo.toString() + "\" em stock!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
					} else {// can add selected vasilha
						Object[] dataRow = new Object[3];
						dataRow[0] = vas;// to string automaticamente e assim e mais facil selecionar a vasilha
						dataRow[1] = new Double(0);
						dataRow[2] = artigo.getUNID().toString();

						((MyTableModel) table_Proviniencia.getModel()).addRow(dataRow);

						comboBox_AddVasilha.removeItem(vas);
						table_Proviniencia.repaint();
					}
				}
			}
		});
		btn_AddVasilha.setBounds(128, 335, 109, 23);
		ContentPanel.add(btn_AddVasilha);

		// remove vasilha ----------------------------------------
		btn_RemoveVasilha = new JButton("Remove Selected Vasilha");
		btn_RemoveVasilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table_Proviniencia.getSelectedRow() < 0) {// if there is not any tow selected the table.getSelectedRow() returns -1 as default
					JOptionPane.showMessageDialog(SaidaTensferenciaDialog.this, "Nao Foi Selecionada nenhuma vasilha da tabela para remover!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
				} else {
					Object vas = ((MyTableModel) table_Proviniencia.getModel()).getValueAt(table_Proviniencia.getSelectedRow(), 0);
					((MyTableModel) table_Proviniencia.getModel()).RemoveRow_PosArray(table_Proviniencia.getSelectedRow()); // Gives the selected arraylist pos
					if (vas instanceof Vasilha) {
						comboBox_AddVasilha.addItem((Vasilha) vas);
					}
				}
			}
		});
		btn_RemoveVasilha.setBounds(10, 366, 151, 23);
		ContentPanel.add(btn_RemoveVasilha);

		// ***************************************************************************************************
		// ************** Table Destino **************
		// ***************************************************************************************************

		lblDestino = new JLabel("Destino: ");
		lblDestino.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDestino.setBounds(10, 417, 69, 20);
		ContentPanel.add(lblDestino);

		// radio buttons -----------------------------------------------
		Button_Group = new ButtonGroup();// to control all the jRadioButton because i only want to select one at the time

		rdbtnVasilha = new JRadioButton(Vasilha_Button_String);
		rdbtnVasilha.setSelected(true);
		rdbtnVasilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout) Cards.getLayout()).show(Cards, Vasilha_Button_String);
			}
		});
		rdbtnVasilha.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		rdbtnVasilha.setBounds(72, 418, 123, 23);
		Button_Group.add(rdbtnVasilha);
		ContentPanel.add(rdbtnVasilha);

		rdbtnCliente = new JRadioButton(Cliente_Button_String);
		rdbtnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) Cards.getLayout()).show(Cards, Cliente_Button_String);
			}
		});
		rdbtnCliente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		rdbtnCliente.setBounds(191, 418, 95, 23);
		Button_Group.add(rdbtnCliente);
		ContentPanel.add(rdbtnCliente);

		// CardLayout to change the layout of Vasilha e Cliente
		Cards = new JPanel(new CardLayout());// creating the panel that olds all the cards panels
		Cards.setBounds(10, 448, 630, 48);

		card1_Vasilha = new JPanel(new BorderLayout()); // creating the fabricavel card panel
		Cards.add(card1_Vasilha, Vasilha_Button_String);// adding the fabricavel panel to the cardlayout panel whit the search associated string

		card2_Cliente = new JPanel(new BorderLayout()); // creating the compravel card panel
		card2_Cliente.setBackground(Color.RED);
		Cards.add(card2_Cliente, Cliente_Button_String);// adding the Compravel panel to the cardlayout panel whit the search associated string

		ContentPanel.add(Cards);
		// table destino

		table_DestinoVasilha = new JTable(new MyTableModel(ColData_Proviniencia_DestinoVasilha));
		table_DestinoVasilha.setFillsViewportHeight(true);// para encher o espaco do panel
		table_DestinoVasilha.setRowHeight(25); // to make the table rows have the default height of 25

		JScrollPane scrollPane_DestinoVasilha = new JScrollPane(table_DestinoVasilha);
		scrollPane_DestinoVasilha.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		card1_Vasilha.add(scrollPane_DestinoVasilha, BorderLayout.CENTER);

		// ***************************************************************************************************
		// ************** butões em baixo ActionListener for the save button **************
		// ***************************************************************************************************
	}
}
