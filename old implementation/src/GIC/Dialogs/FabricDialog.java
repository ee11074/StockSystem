package GIC.Dialogs;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.TabExpander;

import BaseDialog.MyBaseDialog;
import Core.Core;
import MyException.MyException;
import MyTableModel.MyTableModel;
import ProCore.Artigo;
import ProCore.Formula.ArtigoFormula;
import ProCore.Vasilha;
import TableCellEditor.RowEditor;

// Notas:
// -> Ao adicionar dados para a tabela se acontecer algun erro avisar o user e indicar o tipo de erro nao fazendo aparecer nada na tabela
// -> Falta assuciar vasilhas aos artigos das formulas ao adicionar a tabela 
//     1� verificar se a vasilha tem quantidade suficiente para a formula se nao adiciona outro artigo com o mesmo nome de outra vasilha
//     2� se nao houver quantidade suficiente em vasilhar de um determinado artigo para fabricar um artigo aparece a quantidade que ha 
//      avisando o user deste facto e com a linha deste artigo a vermelho na tabel
//    
// -> Antes de salvar fazer aparecer uma janela a dizer as alteracoes que vai fazer com botao confirmar e cancelar
// -> ver a sena dos long values para  tabela
// -> algumas exception posso deixar passar de forma a o utilizador perceber melhor o erro em vez de saltar fora e limpar o ecra logo xD
// -> ver o problema de eu so querer selecionar artigos que podem ser fabricados(e como tal so artigos que tenham formula) e como tal alterar a combo box by default da MyBaseDialog
// -> ver erros the falta de vasilhas se aviso o utilizado ou nao (aparece vasio a combo box)
public class FabricDialog extends MyBaseDialog {
	private static final long serialVersionUID = 1L;

	// consumos Table ---------------------------------------
	private JLabel lblConsumos;

	private JTable table_Consumos;
	private String ColData_Consumos[] = new String[] { "Artigo", "Quantidade", "Unidades", "Vasilha" };

	private JButton btn_Add1MoreSelectedArtigo;
	private JButton btn_RemoveSelectedArtigo;

	private JTextField textField_N_ArtigosConsumos;// n� de artigos nos consumos

	private JLabel lbl_FormulaData;

	private JTable table_Formula;
	private String ColData_Formula[] = new String[] { "Artigo", "Quantidade" };

	// destination Table -----------------------------------
	private JTable table_Destino;
	private String ColData_Destino[] = new String[] { "Vasilha", "Quantidade", "Unidades" };

	// btn add vasilhas and remove them
	private JComboBox<Vasilha> comboBox_AddVasilha;
	private JButton btn_AddVasilha;
	private JButton btn_RemoveSelectedVasilha;
	// Rendimento ------------------------------------------
	private JLabel lbl_Rendimento;
	private JTextField textField_Rendimento;
	private JLabel lblUnidades_Rendimento;

	// preco -----------------------------------------------
	private JLabel lblPrecoUltimo;
	private JTextField textField_PrecoUltimo;
	private JLabel lblEurosymbol_1;

	private JLabel lblPrecoMedio;
	private JTextField textField_PrecoMedio;
	private JLabel lblEurosymbol_2;

	// -----------------------------------------------------
	// if this variables are global it is faster to check the values and because new objects are created when calling the values from the core i can ajust them to the table values
	private ArrayList<ArtigoFormula> FormulaList_SelectedArtigo = new ArrayList<ArtigoFormula>();// this array list as always the same size as the table

	private void QuantidadeArtigoChange(boolean Intrud) {
		if (comboBoxArtigo.getSelectedItem() == null)
			return;// when there's not an article selected return
		String number = textField_QuantidadeProduzida.getText();
		double QuantValue = 1;// se ouver algum erro 1 e valor default
		MyTableModel model = (MyTableModel) table_Formula.getModel();
		try {
			QuantValue = Double.parseDouble(number);
			for (int x = 0; x < model.getRowCount(); x++) {
				model.setValueAt(new Double((FormulaList_SelectedArtigo.get(x).getQuantidade()) * QuantValue), x, 1);// gives the correct value for the table
			}
		} catch (NumberFormatException nfe) {
			if (Intrud && !number.isEmpty() && model.getRowCount() > 0) {// significa que se esta a mudar valores e existe tabela
				JOptionPane.showMessageDialog(FabricDialog.this, "Quantidade Produzida s� aceita se for um numero!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	public FabricDialog(JFrame pai, Core dataBase) {
		super(pai, dataBase, "Fabrica�ao");
		textField_QuantidadeProduzida.getDocument().addDocumentListener(new DocumentListener() {// faz update sempre que se muda o falor executa outro action listener depois de o a executar acabar...
					public void changedUpdate(DocumentEvent e) {
					}

					public void removeUpdate(DocumentEvent e) {
						QuantidadeArtigoChange(false);
					}

					public void insertUpdate(DocumentEvent e) {
						QuantidadeArtigoChange(true);
					}
				});
		// *********************************************************************
		// ************** adding actionListener to comboBoxArtigo **************
		// *********************************************************************
		comboBoxArtigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act) {
				Artigo SelectedArtigo = (Artigo) comboBoxArtigo.getSelectedItem();
				if (SelectedArtigo != null) {

					// show artigo information in the TextFields from above
					textField_Codigo.setText(SelectedArtigo.getCod() + "");
					textField_Unidades.setText(SelectedArtigo.getUNID().toString());
					textField_Saldo.setText(SelectedArtigo.getSaldo() + "");
					textField_GrauAlcool.setText(SelectedArtigo.getGrauAlcol() + "");
					textField_Densidade.setText(SelectedArtigo.getDensidade() + "");
					textField_TaxaRendimento.setText(SelectedArtigo.getTaxaRendimento() + "");

					lblUnidades_Quantidade_Artigo_Selecionado.setText(SelectedArtigo.getUNID().toString());
					lblUnidades_Rendimento.setText(SelectedArtigo.getUNID().toString());
					textField_QuantidadeProduzida.setText("1");// defaul 1 unidade das unidades do produto
					textField_Rendimento.setText("1");// by default the values from the rendimento are 1 becuase the formula is done for 1 unit of that artigo

					// init tables

					InitTables(SelectedArtigo);

				} else {
					ResetAllData();// Makes all the boxes clean and tables to
					JOptionPane.showMessageDialog(FabricDialog.this, "Artigo Selecionado invalido!", "Mansagem de erro!", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		// ***************************************************************************************************
		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		// Consumos Table
		// ===========================================================================================
		lblConsumos = new JLabel("Consumos :");
		lblConsumos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblConsumos.setBounds(10, 161, 95, 22);
		ContentPanel.add(lblConsumos);

		JPanel Formula_Consumos = new JPanel(new BorderLayout());
		Formula_Consumos.setBounds(10, 182, 659, 181);
		ContentPanel.add(Formula_Consumos);

		// Creating JTable_Consumos --------------------------
		table_Consumos = new JTable(new MyTableModel(ColData_Consumos));
		table_Consumos.setFillsViewportHeight(true);// para encher o espaco do panel
		table_Consumos.setRowHeight(25); // to make the table rows have the default height of 25

		// Listener For the table to update Automaticly --------------------------------------------------------
		table_Consumos.getModel().addTableModelListener(new TableModelListener() {// apenas vai servir para ver como estao as quantidades do produtos
					public void tableChanged(TableModelEvent e) {
						System.out.println("Updating...");
						MyTableModel model = (MyTableModel) table_Consumos.getModel();
						for (int x = 0; x < model.getRowCount(); x++) {
							double Value = (Double) model.getValueAt(x, 1);// Acho que vou buscar todos os valores de uma vez so pois sempre que voi buscar algum faz update da table
							// double FormValue = (FormulaList_SelectedArtigo.get(x).getQuantidade() * QuantidadeEscolhida);
							// afinal acho que ja nao vou mudar os valores do array list forms

							System.out.println("Update Table");
							// if (Value < FormValue) {

							// }
						}
					}
				});
		// -----------------------------------------------------------------------------------------------------

		JScrollPane scrollPane_Consumos = new JScrollPane(table_Consumos);
		scrollPane_Consumos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Formula_Consumos.add(scrollPane_Consumos, BorderLayout.CENTER);

		// Add/Remove consumos Button --------------------------------------
		btn_Add1MoreSelectedArtigo = new JButton("Adiciona Artigo Selec.");
		btn_Add1MoreSelectedArtigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int SelectRow = table_Consumos.getSelectedRow();
				if (SelectRow < 0) {// if there is not any tow selected the table.getSelectedRow() returns -1 as default
					JOptionPane.showMessageDialog(FabricDialog.this, "Nao Foi Selecionado nenhum Artigo da tabela Consumos para Adicionar!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
				} else {
					MyTableModel model = (MyTableModel) table_Consumos.getModel();

					RowEditor rowEditor = (RowEditor) table_Consumos.getColumn("Vasilha").getCellEditor();

					Object[] dataRow = new Object[4];
					dataRow[0] = model.getValueAt(SelectRow, 0);
					dataRow[1] = new Double(0.0);
					dataRow[2] = model.getValueAt(SelectRow, 2);
					dataRow[3] = "";

					ArrayList<Vasilha> VasilhasArtForm = DataBase.getVasilhas_Artigo(((Artigo) model.getValueAt(SelectRow, 0)).getCod());
					if (VasilhasArtForm.isEmpty()) {
						// +++++++++++++++++++++++++++++++++++++++++ Error +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
						// "Nao existem vasilhas em stock com o Artigo (cod: \"" + FormulaList_SelectedArtigo.get(x).getCod() + "\")!"
						// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
					}
					JComboBox comboBox = new JComboBox(VasilhasArtForm.toArray());// Jcombo box comtem objectos do tipo vasilha

					rowEditor.setEditorAt(table_Consumos.getRowCount(), new DefaultCellEditor(comboBox));

					table_Consumos.getColumn("Vasilha").setCellEditor(rowEditor);

					System.out.println(table_Consumos.getRowCount());

					// adding row to table
					model.addRow(dataRow);

					System.out.println(table_Consumos.getRowCount());
				}
			}
		});
		btn_Add1MoreSelectedArtigo.setBounds(10, 365, 200, 20);
		ContentPanel.add(btn_Add1MoreSelectedArtigo);

		btn_RemoveSelectedArtigo = new JButton("Remove Artigo Selec.");
		btn_RemoveSelectedArtigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int TableRow = table_Consumos.getSelectedRow();
				if (TableRow < 0) {// if there is not any tow selected the table.getSelectedRow() returns -1 as default
					JOptionPane.showMessageDialog(FabricDialog.this, "Nao Foi Selecionado nenhum Artigo da tabela para remover!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
				} else {
					boolean CanRemove = false;
					MyTableModel model = (MyTableModel) table_Consumos.getModel();
					Artigo SelectArt = (Artigo) model.getValueAt(TableRow, 0);
					for (int x = 0; x < model.getRowCount(); x++) {
						if (x == TableRow)
							continue;
						if (SelectArt == (Artigo) model.getValueAt(x, 0)) {// vao ter a mesma posicao de memoria pois ao adicionar uma nova linha o artigo � copiado do artigo selecionado
							CanRemove = true;
							break;
						}
					}
					if (CanRemove) {
						((MyTableModel) table_Consumos.getModel()).RemoveRow_PosArray(TableRow); // Gives the selected arraylist pos
					} else {
						JOptionPane.showMessageDialog(FabricDialog.this, "O Artigo seleccionado n�o pode ser removido pois segundo a formula e preciso consumir pelo menos um artigo desse tipo!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		btn_RemoveSelectedArtigo.setBounds(240, 365, 200, 20);
		ContentPanel.add(btn_RemoveSelectedArtigo);

		// TextField Quantidade Artigos ---------------------

		textField_N_ArtigosConsumos = new JTextField();
		textField_N_ArtigosConsumos.setBounds(679, 182, 57, 20);
		ContentPanel.add(textField_N_ArtigosConsumos);
		textField_N_ArtigosConsumos.setColumns(10);

		// Table Check Formulas ---------------------------------------------------------------------
		lbl_FormulaData = new JLabel("Dados Formula:");
		lbl_FormulaData.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbl_FormulaData.setBounds(679, 213, 117, 14);
		ContentPanel.add(lbl_FormulaData);

		JPanel panel_Formula = new JPanel(new BorderLayout());
		panel_Formula.setBounds(679, 239, 280, 124);
		ContentPanel.add(panel_Formula);

		// Creating JTable_Consumos --------------------------
		table_Formula = new JTable(new MyTableModel(ColData_Formula));
		table_Formula.setFillsViewportHeight(true);// para encher o espaco do panel
		table_Formula.setRowHeight(25); // to make the table rows have the default height of 25

		JScrollPane scrollPane_Formula = new JScrollPane(table_Formula);
		panel_Formula.add(scrollPane_Formula, BorderLayout.CENTER);

		// ������������������������������������������������������������������������������������������������������������
		// Destino Table
		// ===============================================================================================

		JLabel lblDestino = new JLabel("Destino :");
		lblDestino.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDestino.setBounds(10, 417, 69, 20);
		ContentPanel.add(lblDestino);

		JPanel Destino_Panel = new JPanel(new BorderLayout());
		Destino_Panel.setBounds(10, 436, 659, 148);
		ContentPanel.add(Destino_Panel);

		// Creating JTable_Destino ---------------------------
		table_Destino = new JTable(new MyTableModel(ColData_Destino));
		table_Destino.setFillsViewportHeight(true);// para encher o espaco do panel
		table_Destino.setRowHeight(25); // to make the table rows have the default height of 25

		JScrollPane scrollPane_Destion = new JScrollPane(table_Destino);
		scrollPane_Destion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Destino_Panel.add(scrollPane_Destion, BorderLayout.CENTER);

		// button to add vasilhas / remove them

		comboBox_AddVasilha = new JComboBox<Vasilha>();
		comboBox_AddVasilha.setBounds(10, 592, 178, 20);
		ContentPanel.add(comboBox_AddVasilha);

		btn_AddVasilha = new JButton("Adiciona Vasilha");
		btn_AddVasilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Artigo artigo = (Artigo) comboBoxArtigo.getSelectedItem();
				if (artigo == null) {// if the user don't select one Artigo from the comboBox
					JOptionPane.showMessageDialog(FabricDialog.this, "Nao Foi Selecionado nenhum Artigo!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
				} else {
					Vasilha vas = (Vasilha) comboBox_AddVasilha.getSelectedItem();
					if (vas == null) {// if the user don't select one vasilha from the comboBox
						JOptionPane.showMessageDialog(FabricDialog.this, "Nao exitem mais Vasilhas com o artigo \"" + artigo.toString() + "\" em stock!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
					} else {// can add selected vasilha
						Object[] dataRow = new Object[3];
						dataRow[0] = vas;// to string automaticamente e assim e mais facil selecionar a vasilha
						dataRow[1] = new Double(0);
						dataRow[2] = artigo.getUNID().toString();

						((MyTableModel) table_Destino.getModel()).addRow(dataRow);

						comboBox_AddVasilha.removeItem(vas);
					}
				}
			}
		});
		btn_AddVasilha.setBounds(201, 592, 110, 23);
		ContentPanel.add(btn_AddVasilha);

		btn_RemoveSelectedVasilha = new JButton("Remove Vasilha Selecionada");
		btn_RemoveSelectedVasilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_Destino.getSelectedRow() < 0) {// if there is not any tow selected the table.getSelectedRow() returns -1 as default
					JOptionPane.showMessageDialog(FabricDialog.this, "Nao Foi Selecionada nenhuma vasilha da tabela para remover!", "Error Message!", JOptionPane.PLAIN_MESSAGE);
				} else {
					Object vas = ((MyTableModel) table_Destino.getModel()).getValueAt(table_Destino.getSelectedRow(), 0);
					((MyTableModel) table_Destino.getModel()).RemoveRow_PosArray(table_Destino.getSelectedRow()); // Gives the selected arraylist pos
					if (vas instanceof Vasilha) {
						comboBox_AddVasilha.addItem((Vasilha) vas);
					}
				}
			}
		});
		btn_RemoveSelectedVasilha.setBounds(8, 618, 180, 23);
		ContentPanel.add(btn_RemoveSelectedVasilha);

		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		// Rendimento ===========================================
		textField_Rendimento = new JTextField();
		textField_Rendimento.setBounds(107, 391, 86, 20);
		ContentPanel.add(textField_Rendimento);
		textField_Rendimento.setColumns(10);

		lbl_Rendimento = new JLabel("Rendimento: ");
		lbl_Rendimento.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbl_Rendimento.setBounds(10, 391, 95, 21);
		ContentPanel.add(lbl_Rendimento);

		lblUnidades_Rendimento = new JLabel("");
		lblUnidades_Rendimento.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblUnidades_Rendimento.setBounds(197, 391, 86, 17);
		ContentPanel.add(lblUnidades_Rendimento);

		// ======================================================
		// Show Price TextFilds
		// ======================================================
		lblPrecoUltimo = new JLabel("Preco Ultimo:");
		lblPrecoUltimo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPrecoUltimo.setBounds(10, 649, 103, 17);
		ContentPanel.add(lblPrecoUltimo);

		textField_PrecoUltimo = new JTextField();
		textField_PrecoUltimo.setBounds(117, 649, 86, 20);
		ContentPanel.add(textField_PrecoUltimo);
		textField_PrecoUltimo.setColumns(10);

		lblEurosymbol_1 = new JLabel("\u20AC");
		lblEurosymbol_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblEurosymbol_1.setBounds(213, 648, 24, 19);

		ContentPanel.add(lblEurosymbol_1);
		lblPrecoMedio = new JLabel("Preco Medio: ");
		lblPrecoMedio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPrecoMedio.setBounds(10, 699, 103, 21);
		ContentPanel.add(lblPrecoMedio);

		textField_PrecoMedio = new JTextField();
		textField_PrecoMedio.setBounds(117, 699, 86, 20);
		ContentPanel.add(textField_PrecoMedio);
		textField_PrecoMedio.setColumns(10);

		lblEurosymbol_2 = new JLabel("\u20AC");
		lblEurosymbol_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblEurosymbol_2.setBounds(213, 699, 24, 19);
		ContentPanel.add(lblEurosymbol_2);

		// ***************************************************************************************************
		// ************** but�es em baixo ActionListener for the save button **************
		// ***************************************************************************************************

		SalvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				MyTableModel model = (MyTableModel) table_Consumos.getModel();
				for (int x = 0; x < model.getRowCount(); x++) {
					Object Value = model.getValueAt(x, 3);
					if (Value instanceof Vasilha) {// basta fazer if(!(Value instanceof Vasilha))// mostra mensagen de erro se nao a vasilha e valida xDD
						System.out.println((Vasilha) Value);
					}
				}
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			}
		});
	}

	// ***************************************************************************************************
	// ************************************* Importante Methods *****************************************
	// ***************************************************************************************************

	private void ResetAllData() {
		textField_Codigo.setText("");
		textField_Unidades.setText("");
		textField_Saldo.setText("");
		textField_GrauAlcool.setText("");
		textField_Densidade.setText("");
		textField_TaxaRendimento.setText("");

		lblUnidades_Quantidade_Artigo_Selecionado.setText("");
		lblUnidades_Rendimento.setText("");
		textField_QuantidadeProduzida.setText("");
		textField_Rendimento.setText("");

		MyTableModel model1 = (MyTableModel) table_Consumos.getModel();
		model1.deleteData();

		MyTableModel model2 = (MyTableModel) table_Destino.getModel();
		model2.deleteData();

		MyTableModel model3 = (MyTableModel) table_Formula.getModel();
		model3.deleteData();

		FormulaList_SelectedArtigo.clear();

		comboBox_AddVasilha.removeAllItems();
		
		table_Consumos.repaint();
		table_Destino.repaint();
		table_Formula.repaint();
	}

	private void InitTables(Artigo SelectedArtigo) {
		try {
			// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			// ########################################## Init Table Consumos &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			FormulaList_SelectedArtigo = DataBase.getFormula_ID_art(SelectedArtigo.getCod());// new memory it is allocated for this so the values fro the core are not changes when changing this values

			if (FormulaList_SelectedArtigo.isEmpty()) {
				throw new MyException("Nao existe Formula para o Artigo: \"" + SelectedArtigo.toString() + "\"");
			}
			// #################################################
			RowEditor rowEditor = new RowEditor(table_Consumos);
			// #################################################

			ArrayList<Object[]> data = new ArrayList<Object[]>();// new Object[formArtigos.size()][4];

			// ----------------------
			ArrayList<Object[]> dataForm = new ArrayList<Object[]>();// for the Table Formulas
			// ----------------------
			for (int x = 0; x < FormulaList_SelectedArtigo.size(); x++) {
				Artigo ArtForm = DataBase.getArtigoObject(FormulaList_SelectedArtigo.get(x).getCodArt());

				// +++++++++++++++++++++++++++++ error +++++++++++++++++++++++++++++++++++++++++++++++++++++++
				if (ArtForm == null) {
					throw new MyException("Artigo -> cod:\"" + FormulaList_SelectedArtigo.get(x).getCodArt() + "\" da formula nao se encontra presente na base de dados!");
				}
				// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				Object[] DataRow = new Object[4];

				DataRow[0] = DataBase.getArtigoObject(FormulaList_SelectedArtigo.get(x).getCodArt());// toString from Artigo give the Artigo name as default
				DataRow[1] = new Double(FormulaList_SelectedArtigo.get(x).getQuantidade());
				DataRow[2] = ArtForm.getUNID().toString();
				DataRow[3] = "";// if vasio nao foi selecionada informacoa
				data.add(DataRow);

				// table Formulas
				Object[] DataRowForm = new Object[2];
				DataRowForm[0] = DataRow[0];
				DataRowForm[1] = new Double(FormulaList_SelectedArtigo.get(x).getQuantidade());
				dataForm.add(DataRowForm);
				// --------------

				// ############################################################################################################
				// ################################## Make JComboBox Over The 4� column #######################################
				// ############################################################################################################
				ArrayList<Vasilha> VasilhasArtForm = DataBase.getVasilhas_Artigo(ArtForm.getCod());
				if (VasilhasArtForm.isEmpty()) {
					// +++++++++++++++++++++++++++++++++++++++++ Error +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
					// "Nao existem vasilhas em stock com o Artigo (cod: \"" + FormulaList_SelectedArtigo.get(x).getCod() + "\")!"
					// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				}
				JComboBox comboBox = new JComboBox(VasilhasArtForm.toArray());// Jcombo box comtem objectos do tipo vasilha

				rowEditor.setEditorAt(x, new DefaultCellEditor(comboBox));
				// ############################################################################################################
				// ############################################################################################################
				// ############################################################################################################
			}
			// ##########################################################
			table_Consumos.getColumn("Vasilha").setCellEditor(rowEditor);// only set the new default editor int the column that i need that epecial editor
			// ##########################################################

			((MyTableModel) table_Consumos.getModel()).setData(data);
			// seting cell true "editable"
			// ------------------------------------------
			// ((MyTableModel) table_Consumos.getModel()).setColumCellEditor(0, true);
			((MyTableModel) table_Consumos.getModel()).setColumCellEditor(1, true);
			((MyTableModel) table_Consumos.getModel()).setColumCellEditor(3, true);
			// ----------------------------------------------------------------------

			initColumnSizes(table_Consumos, ColData_Consumos.length);// modifica�ao de alguns parametros defaulda das colunsa da table
			// -----------------------------------------------------------------------------------------------------

			// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			// ########################################## Init Table Destino &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			ArrayList<Vasilha> VasilhasArt = DataBase.getVasilhas_Artigo(SelectedArtigo.getCod());
			if (VasilhasArt.isEmpty()) {
				// +++++++++++++++++++++++++++++++++++++++++ Error +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				// "Nao existem vasilhas do Artigo (cod: \"" + SelectedArtigo.getCod() + "\")!"
				// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			}
			comboBox_AddVasilha.removeAllItems();
			for (Vasilha vas : VasilhasArt)
				comboBox_AddVasilha.addItem(vas);// adding vasilhas to the combobox

			// seting cell true "editable"
			// ------------------------------------------
			((MyTableModel) table_Destino.getModel()).setColumCellEditor(1, true);
			// ------------------------------------------

			initColumnSizes(table_Destino, ColData_Destino.length);// modifica�ao de alguns parametros defaulda das colunsa da table

			// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			// ########################################## Init Table Formula &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			((MyTableModel) table_Formula.getModel()).setData(dataForm);
			initColumnSizes(table_Formula, ColData_Formula.length);

		} catch (MyException e) {
			MyTableModel model1 = (MyTableModel) table_Consumos.getModel();
			model1.deleteData();

			MyTableModel model2 = (MyTableModel) table_Destino.getModel();
			model2.deleteData();

			MyTableModel model3 = (MyTableModel) table_Formula.getModel();
			model3.deleteData();

			FormulaList_SelectedArtigo.clear();

			comboBox_AddVasilha.removeAllItems();

			JOptionPane.showMessageDialog(FabricDialog.this, e.getMessage(), "Mansagem de erro!", JOptionPane.PLAIN_MESSAGE);
		}
		table_Consumos.repaint();
		table_Destino.repaint();
		table_Formula.repaint();
	}
}
