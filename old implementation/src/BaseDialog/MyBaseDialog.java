package BaseDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Core.Core;
import MyTableModel.MyTableModel;
import ProCore.Artigo;

public class MyBaseDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	// Program Core Data Base where the information is save

	protected Core DataBase;

	// ----------------------------------------------------

	protected final JPanel buttonPanel;
	protected final JButton SalvarButton;
	protected final JButton cancelarButton;

	protected final JPanel ContentPanel;

	// artigo ----------------------------------------------
	protected JLabel lblArtigo;
	protected JComboBox<Artigo> comboBoxArtigo;

	// quantidade produzida --------------------------------
	protected JLabel lblQuantidade;
	protected JTextField textField_QuantidadeProduzida;
	protected JLabel lblUnidades_Quantidade_Artigo_Selecionado;

	// Data ------------------------------------------------
	protected JLabel lblData;
	protected JTextField textField_Data;
	protected JButton btnHoje;

	// artigo information ----------------------------------
	protected JLabel lblCod;
	protected JLabel lblUn;
	protected JLabel lblSaldo;
	protected JLabel lblGrauAlcoolico;
	protected JLabel lblTaxaRendimento;
	protected JLabel lblDensidade;

	protected JTextField textField_Codigo;
	protected JTextField textField_Unidades;
	protected JTextField textField_Saldo;
	protected JTextField textField_GrauAlcool;
	protected JTextField textField_Densidade;
	protected JTextField textField_TaxaRendimento;

	public MyBaseDialog(JFrame pai, Core dataBase, String Title) {
		super(pai, Title, true);

		this.DataBase = dataBase;
		// setBounds(100, 100, 650, 450);

		Container c = super.getContentPane();
		c.setLayout(new BorderLayout());
		// content Panel
		// ************************************************************
		ContentPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(ContentPanel);
		ContentPanel.setLayout(null);

		// para obrigar as barras do scroll pane aparecerem
		ContentPanel.setPreferredSize(new Dimension(1024, 768));// just to prove that the dimensions bars are working
		// scrollPane.setPreferredSize(new Dimension(400, 400));// just to prove that the dimension bares are working
		scrollPane.setPreferredSize(new Dimension(1024, 768));
		// ===============================================================================================
		// codigo --------------------------------------------------
		lblCod = new JLabel("Cod. :");
		lblCod.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblCod.setBounds(10, 7, 39, 20);
		ContentPanel.add(lblCod);

		textField_Codigo = new JTextField();
		textField_Codigo.setEditable(false);
		textField_Codigo.setBounds(55, 8, 86, 20);
		ContentPanel.add(textField_Codigo);
		textField_Codigo.setColumns(10);

		// unidades ------------------------------------------------
		lblUn = new JLabel("  Un : ");
		lblUn.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblUn.setBounds(10, 50, 39, 18);
		ContentPanel.add(lblUn);

		textField_Unidades = new JTextField();
		textField_Unidades.setEditable(false);
		textField_Unidades.setBounds(55, 50, 86, 20);
		ContentPanel.add(textField_Unidades);
		textField_Unidades.setColumns(10);

		// Saldo ---------------------------------------------------
		lblSaldo = new JLabel(" Saldo : ");
		lblSaldo.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblSaldo.setBounds(191, 7, 46, 20);
		ContentPanel.add(lblSaldo);

		textField_Saldo = new JTextField();
		textField_Saldo.setEditable(false);
		textField_Saldo.setBounds(247, 8, 86, 20);
		ContentPanel.add(textField_Saldo);
		textField_Saldo.setColumns(10);

		// Grau Alcoolico ------------------------------------------
		lblGrauAlcoolico = new JLabel("Grau Alcoolico :");
		lblGrauAlcoolico.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblGrauAlcoolico.setBounds(151, 50, 86, 20);
		ContentPanel.add(lblGrauAlcoolico);

		textField_GrauAlcool = new JTextField();
		textField_GrauAlcool.setEditable(false);
		textField_GrauAlcool.setBounds(247, 50, 86, 20);
		ContentPanel.add(textField_GrauAlcool);
		textField_GrauAlcool.setColumns(10);

		// Densidade -----------------------------------------------
		lblDensidade = new JLabel("  Densidade :");
		lblDensidade.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblDensidade.setBounds(380, 7, 79, 20);
		ContentPanel.add(lblDensidade);

		textField_Densidade = new JTextField();
		textField_Densidade.setEditable(false);
		textField_Densidade.setBounds(459, 8, 86, 20);
		ContentPanel.add(textField_Densidade);
		textField_Densidade.setColumns(10);

		// Taxa Rendimento -----------------------------------------
		lblTaxaRendimento = new JLabel("Taxa Rendimento :");
		lblTaxaRendimento.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblTaxaRendimento.setBounds(350, 50, 109, 18);
		ContentPanel.add(lblTaxaRendimento);

		textField_TaxaRendimento = new JTextField();
		textField_TaxaRendimento.setEditable(false);
		textField_TaxaRendimento.setBounds(459, 50, 86, 20);
		ContentPanel.add(textField_TaxaRendimento);
		textField_TaxaRendimento.setColumns(10);

		// Panel sparator
		// =====================================================================

		JPanel DataPanelSparator = new JPanel();
		DataPanelSparator.setBackground(Color.BLACK); // to set the color of the
														// panl to black
		DataPanelSparator.setBounds(0, 91, 1024, 4);
		ContentPanel.add(DataPanelSparator);

		// artigo
		// ============================================================================
		// artigo -----------------------------------------------
		lblArtigo = new JLabel("Artigo: ");
		lblArtigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblArtigo.setBounds(10, 114, 57, 22);
		ContentPanel.add(lblArtigo);

		// comboBoxArtigo = new JComboBox((DataBase.getArtigosArray())); // -> for the design mode
		comboBoxArtigo = new JComboBox<Artigo>((DataBase.getArtigosArray()));// como o to string de artigo mostra apenas o nome a combo box mostra o nome do artigo com o defaul
		comboBoxArtigo.setSelectedIndex(-1);// -1 by default no artigo is
											// selected
		// ******************************************************************************
		// add actionListener to comboBoxArtigo in the Subclass to do the changes needed
		// ******************************************************************************

		comboBoxArtigo.setBounds(72, 117, 147, 20);
		ContentPanel.add(comboBoxArtigo);

		// quantidade produzida --------------------------------
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

		// data ------------------------------------------------
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

		// ======================================================
		// butões em baixo ...
		// ******************************************************
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// BUTTONS ********************************
		SalvarButton = new JButton("Salvar");
		SalvarButton.setActionCommand("OK");

		// ******************************************************************************
		// add actionListener to SalvarButton in the Subclass to do the changes needed
		// ******************************************************************************

		buttonPanel.add(SalvarButton);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();// only to close the window in the moment its all i need
			}
		});
		cancelarButton.setActionCommand("Cancel");
		buttonPanel.add(cancelarButton);
		// *****************************************

		//getRootPane().setDefaultButton(SalvarButton); // to pressing enter the ok button ser o selecionado
		// **************************************************************************
		c.add(scrollPane, BorderLayout.CENTER);
		c.add(buttonPanel, BorderLayout.SOUTH);
		pack();

		setMaximumSize(new Dimension(1058, 858));// maximum size
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
}