package TestWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Core.Core;
import MyTableModel.MyTableModel;
import ProCore.Artigo;
import ProCore.Formula.ArtigoFormula;
import ProCore.Vasilha;

public class TestWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	protected Core DataBase;
	protected final JPanel buttonPanel, ContentPanel;
	protected final JButton SalvarButton, cancelarButton, btnHoje;
	protected JComboBox<Artigo> comboBoxArtigo;
	protected JTextField textField_Data, textField_QuantidadeProduzida, textField_Codigo, textField_Unidades, textField_Saldo, textField_GrauAlcool, textField_Densidade, textField_TaxaRendimento;
	protected JLabel lblData, lblQuantidade, lblUnidades_Quantidade_Artigo_Selecionado, lblArtigo, lblCod, lblUn, lblSaldo, lblGrauAlcoolico, lblTaxaRendimento, lblDensidade;

	// »»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»
	// ***************************************************************************************************
	// *********************** the Attributes from the son class goes in here ****************************
	// ***************************************************************************************************
	// «««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««

	// consumos Table ---------------------------------------
		private JLabel lblConsumos;

		private JTable table_Consumos;
		private String ColData_Consumos[] = new String[] { "Artigo", "Quantidade", "Unidades", "Vasilha" };

		private JButton btnNext;// Next Button

		private JTextField textField_N_ArtigosConsumos;// nº de artigos nos consumos

		// destination Table -----------------------------------
		private JTable table_Destino;
		private String ColData_Destino[] = new String[] { "Vasilha", "Quantidade", "Unidades" };

		// btn add vasilhas and remove them
		private JComboBox<Vasilha> comboBox_AddArtigo;
		private JButton btn_AddArtigo;
		private JButton btn_RemoveSelectedArtigo;
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
		// if this variables are global it is faster to check the values
		private Artigo SelectedArtigo = null;
		private ArrayList<ArtigoFormula> FormulaList_SelectedArtigo = new ArrayList<ArtigoFormula>();

		//
	
	// «««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««
	// ***************************************************************************************************
	// *********************** the Attributes from the son class goes in here ***************************
	// ***************************************************************************************************
	// »»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»

	public TestWindow(JFrame pai, Core dataBase, String Title) {
		super(pai, Title, true);
		this.DataBase = dataBase;
		//Container c = super.getContentPane();
		//c.setLayout(new BorderLayout());
		ContentPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(ContentPanel);
		ContentPanel.setLayout(null);
		ContentPanel.setPreferredSize(new Dimension(1024, 768));
		scrollPane.setPreferredSize(new Dimension(1024, 768));
		lblCod = new JLabel("Cod. :");
		lblCod.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblCod.setBounds(10, 7, 39, 20);
		ContentPanel.add(lblCod);
		textField_Codigo = new JTextField();
		textField_Codigo.setEditable(false);
		textField_Codigo.setBounds(55, 8, 86, 20);
		ContentPanel.add(textField_Codigo);
		textField_Codigo.setColumns(10);
		lblUn = new JLabel("  Un : ");
		lblUn.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblUn.setBounds(10, 50, 39, 18);
		ContentPanel.add(lblUn);
		textField_Unidades = new JTextField();
		textField_Unidades.setEditable(false);
		textField_Unidades.setBounds(55, 50, 86, 20);
		ContentPanel.add(textField_Unidades);
		textField_Unidades.setColumns(10);
		lblSaldo = new JLabel(" Saldo : ");
		lblSaldo.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblSaldo.setBounds(191, 7, 46, 20);
		ContentPanel.add(lblSaldo);
		textField_Saldo = new JTextField();
		textField_Saldo.setEditable(false);
		textField_Saldo.setBounds(247, 8, 86, 20);
		ContentPanel.add(textField_Saldo);
		textField_Saldo.setColumns(10);
		lblGrauAlcoolico = new JLabel("Grau Alcoolico :");
		lblGrauAlcoolico.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblGrauAlcoolico.setBounds(151, 50, 86, 20);
		ContentPanel.add(lblGrauAlcoolico);
		textField_GrauAlcool = new JTextField();
		textField_GrauAlcool.setEditable(false);
		textField_GrauAlcool.setBounds(247, 50, 86, 20);
		ContentPanel.add(textField_GrauAlcool);
		textField_GrauAlcool.setColumns(10);
		lblDensidade = new JLabel("  Densidade :");
		lblDensidade.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblDensidade.setBounds(380, 7, 79, 20);
		ContentPanel.add(lblDensidade);
		textField_Densidade = new JTextField();
		textField_Densidade.setEditable(false);
		textField_Densidade.setBounds(459, 8, 86, 20);
		ContentPanel.add(textField_Densidade);
		textField_Densidade.setColumns(10);
		lblTaxaRendimento = new JLabel("Taxa Rendimento :");
		lblTaxaRendimento.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblTaxaRendimento.setBounds(350, 50, 109, 18);
		ContentPanel.add(lblTaxaRendimento);
		textField_TaxaRendimento = new JTextField();
		textField_TaxaRendimento.setEditable(false);
		textField_TaxaRendimento.setBounds(459, 50, 86, 20);
		ContentPanel.add(textField_TaxaRendimento);
		textField_TaxaRendimento.setColumns(10);
		JPanel DataPanelSparator = new JPanel();
		DataPanelSparator.setBackground(Color.BLACK);
		DataPanelSparator.setBounds(0, 91, 1024, 4);
		ContentPanel.add(DataPanelSparator);
		lblArtigo = new JLabel("Artigo: ");
		lblArtigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblArtigo.setBounds(10, 114, 57, 22);
		ContentPanel.add(lblArtigo);

		comboBoxArtigo = new JComboBox((DataBase.getArtigosArray())); // -> for the design mode
		// comboBoxArtigo = new JComboBox<Artigo>((DataBase.getArtigosArray()));// como o to string de artigo mostra apenas o nome a combo box mostra o nome do artigo com o defaul
		comboBoxArtigo.setSelectedIndex(-1);
		comboBoxArtigo.setBounds(72, 117, 147, 20);
		ContentPanel.add(comboBoxArtigo);
		lblQuantidade = new JLabel("Quantidade Produzida:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblQuantidade.setBounds(264, 115, 165, 20);
		ContentPanel.add(lblQuantidade);
		textField_QuantidadeProduzida = new JTextField();
		textField_QuantidadeProduzida.setBounds(427, 114, 86, 20);
		ContentPanel.add(textField_QuantidadeProduzida);
		textField_QuantidadeProduzida.setColumns(10);
		lblUnidades_Quantidade_Artigo_Selecionado = new JLabel("");
		lblUnidades_Quantidade_Artigo_Selecionado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblUnidades_Quantidade_Artigo_Selecionado.setBounds(512, 115, 90, 20);
		ContentPanel.add(lblUnidades_Quantidade_Artigo_Selecionado);
		lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblData.setBounds(623, 116, 46, 18);
		ContentPanel.add(lblData);
		textField_Data = new JTextField("yyyy/MM/dd");
		textField_Data.setBounds(669, 114, 95, 20);
		ContentPanel.add(textField_Data);
		textField_Data.setColumns(10);
		btnHoje = new JButton("Hoje");
		btnHoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				textField_Data.setText(sdf.format(new Date()));
			}
		});
		btnHoje.setBounds(774, 113, 69, 23);
		ContentPanel.add(btnHoje);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		SalvarButton = new JButton("Salvar");
		SalvarButton.setActionCommand("OK");
		buttonPanel.add(SalvarButton);
		cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();// only to close the window in the moment its all i need
			}
		});
		cancelarButton.setActionCommand("Cancel");
		buttonPanel.add(cancelarButton);
		getRootPane().setDefaultButton(SalvarButton);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		pack();
		setMaximumSize(new Dimension(1058, 858));// maximum size

		// »»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»
		// ***************************************************************************************************
		// ***************************** the code from the son class goes in here ****************************
		// ***************************************************************************************************
		// «««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««

		lblConsumos = new JLabel("Consumos :");
		lblConsumos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblConsumos.setBounds(10, 161, 95, 22);
		ContentPanel.add(lblConsumos);

		JPanel Formula_Panel = new JPanel(new BorderLayout());
		Formula_Panel.setBounds(10, 182, 659, 181);
		ContentPanel.add(Formula_Panel);

		// Creating JTable_Consumos --------------------------
		table_Consumos = new JTable(new MyTableModel(ColData_Consumos));
		table_Consumos.setFillsViewportHeight(true);// para encher o espaco do panel
		table_Consumos.setRowHeight(25); // to make the table rows have the default height of 25

		// Listener For the table to update Automaticly --------------------------------------------------------
		table_Consumos.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				System.out.println("Updating...");
			}
		});
		// -----------------------------------------------------------------------------------------------------

		JScrollPane scrollPane_Consumos = new JScrollPane(table_Consumos);
		scrollPane_Consumos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Formula_Panel.add(scrollPane_Consumos, BorderLayout.CENTER);

		// Next Button --------------------------------------
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNext.setBounds(600, 374, 69, 23);
		ContentPanel.add(btnNext);

		// TextField Quantidade Artigos ---------------------

		textField_N_ArtigosConsumos = new JTextField();
		textField_N_ArtigosConsumos.setBounds(679, 182, 57, 20);
		ContentPanel.add(textField_N_ArtigosConsumos);
		textField_N_ArtigosConsumos.setColumns(10);

		// ««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««
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
		
		comboBox_AddArtigo = new JComboBox<Vasilha>();
		comboBox_AddArtigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox_AddArtigo.setBounds(10, 592, 178, 20);
		ContentPanel.add(comboBox_AddArtigo);

		btn_AddArtigo = new JButton("Adiciona Vasilha");
		btn_AddArtigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_AddArtigo.setBounds(201, 592, 110, 23);
		ContentPanel.add(btn_AddArtigo);

		btn_RemoveSelectedArtigo = new JButton("Remove Vasilha Selecionada");
		btn_RemoveSelectedArtigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_RemoveSelectedArtigo.setBounds(8, 618, 180, 23);
		ContentPanel.add(btn_RemoveSelectedArtigo);
		
		
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
		
		JPanel panel = new JPanel();
		panel.setBounds(679, 239, 280, 124);
		ContentPanel.add(panel);
		
		JLabel lbl_FormulaData = new JLabel("Dados Formula:");
		lbl_FormulaData.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbl_FormulaData.setBounds(679, 213, 117, 14);
		ContentPanel.add(lbl_FormulaData);
		
		
		// «««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««««
		// ***************************************************************************************************
		// ***************************** the code from the son class goes in here ****************************
		// ***************************************************************************************************
		// »»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»
	}

	// apenas porque fica com cor azul e ajuda distinguir melhor xD
	private TableColumn TableColumn;
	private Component Component;
	private MyTableModel model;

	protected void initColumnSizes(JTable table, int coluns) {
		model = (MyTableModel) table.getModel();// model igualar a variable global que eu tenho
		TableColumn = null;

		Component = null;

		int headerWidth = 0;
		int cellWidth = 0;
		Object[] longValues = model.longValues;// array com os valores mais longs de cada coluna
		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

		for (int i = 0; i < coluns; i++) {// percorre as colunas
			TableColumn = table.getColumnModel().getColumn(i);

			Component = headerRenderer.getTableCellRendererComponent(null, TableColumn.getHeaderValue(), false, false, 0, 0);
			headerWidth = Component.getPreferredSize().width;

			Component = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(table, longValues[i], false, false, 0, i);
			cellWidth = Component.getPreferredSize().width;

			TableColumn.setPreferredWidth(Math.max(headerWidth, cellWidth));
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWindow dialog = new TestWindow(null,new Core(),"Test");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
