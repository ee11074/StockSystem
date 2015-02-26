package GIC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FabricDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private final JPanel buttonPanel;
	private final JButton okButton;
	private final JButton cancelButton;

	private final JPanel ContentPanel;

	// artigo ----------------------------------------------
	private JLabel lblArtigo;
	private JComboBox comboBoxArtigo;

	// quantidade produzida --------------------------------
	private JLabel lblQuantidade;
	private JTextField textField_QuantidadeProduzida;
	private JComboBox comboBox_Unidades_QuantidadeProduzida;

	// Data ------------------------------------------------
	private JLabel lblData;
	private JTextField textField_Data;
	private JButton btnHoje;

	// artigo information ----------------------------------
	private JLabel lblCod;
	private JLabel lblUn;
	private JLabel lblSaldo;
	private JLabel lblGrauAlcoolico;
	private JLabel lblTaxaRendimento;
	private JLabel lblDensidade;

	private JTextField textField_Codigo;
	private JTextField textField_Unidades;
	private JTextField textField_Saldo;
	private JTextField textField_GrauAlcool;
	private JTextField textField_Densidade;
	private JTextField textField_TaxaRendimento;

	// Formula Table ---------------------------------------

	private JTable table_Formula;
	private DefaultTableModel model_Formulas;
	private String ColData_Formula[] = new String[] { "Artigo", "Quantidade",
			"Unidades", "Vasilha" };

	// destion Table ---------------------------------------
	private JTable table_Destino;
	private DefaultTableModel model_Destino;
	private String ColData_Destino[] = new String[] { "Vasilha", "Quantidade",
			"Unidades" };

	public FabricDialog(JFrame pai) {
		super(pai, "Fabrica�ao", true);

		// setBounds(100, 100, 650, 450);

		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		// content Panel
		// ************************************************************
		ContentPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(ContentPanel);
		ContentPanel.setLayout(null);

		// para obrigar as barras do scroll pane aparecerem
		ContentPanel.setPreferredSize(new Dimension(1024, 768));// just to prove
																// that the
																// dimensions
																// bars are
																// working
		// scrollPane.setPreferredSize(new Dimension(400, 400));// just to prove
		// that the
		// dimension
		// bares are
		// working
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

		comboBoxArtigo = new JComboBox();
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

		comboBox_Unidades_QuantidadeProduzida = new JComboBox();
		comboBox_Unidades_QuantidadeProduzida.setBounds(520, 114, 39, 20);
		ContentPanel.add(comboBox_Unidades_QuantidadeProduzida);

		// data ------------------------------------------------
		lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblData.setBounds(623, 116, 46, 18);
		ContentPanel.add(lblData);

		textField_Data = new JTextField();
		textField_Data.setBounds(669, 114, 95, 20);
		ContentPanel.add(textField_Data);
		textField_Data.setColumns(10);

		btnHoje = new JButton("Hoje");
		btnHoje.setBounds(774, 113, 69, 23);
		ContentPanel.add(btnHoje);

		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		// Formula Table
		// ===========================================================================================
		JLabel lblFormula = new JLabel("Formula :");
		lblFormula.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFormula.setBounds(10, 161, 79, 22);
		ContentPanel.add(lblFormula);

		JPanel Formula_Panel = new JPanel(new BorderLayout());
		Formula_Panel.setBounds(10, 182, 659, 181);
		ContentPanel.add(Formula_Panel);

		table_Formula = new JTable();
		table_Formula.setModel(new DefaultTableModel(new Object[][] {},
				ColData_Formula));
		table_Formula.setFillsViewportHeight(true);// para encher o espaco do
													// panel
		// columns size (not resizable)
		// ------------------------------------------

		table_Formula.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_Formula.getColumnModel().getColumn(0).setMinWidth(200);
		table_Formula.getColumnModel().getColumn(0).setMaxWidth(1000);

		table_Formula.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_Formula.getColumnModel().getColumn(1).setMinWidth(150);
		table_Formula.getColumnModel().getColumn(1).setMaxWidth(250);

		table_Formula.getColumnModel().getColumn(2).setPreferredWidth(90);
		table_Formula.getColumnModel().getColumn(2).setMinWidth(90);
		table_Formula.getColumnModel().getColumn(2).setMaxWidth(120);

		table_Formula.getColumnModel().getColumn(3).setPreferredWidth(150);
		table_Formula.getColumnModel().getColumn(3).setMinWidth(150);
		table_Formula.getColumnModel().getColumn(3).setMaxWidth(250);

		// ::::::::::::::::::::::::::::::::::::::::::::::::::::::
		// table colon title height
		JTableHeader header1 = table_Formula.getTableHeader();
		Dimension d1 = header1.getPreferredSize();
		d1.height = 30;
		header1.setPreferredSize(d1);
		// ::::::::::::::::::::::::::::::::::::::::::::::::::::::

		model_Formulas = (DefaultTableModel) table_Formula.getModel();// to add
																		// rows

		table_Formula.setRowHeight(30);

		JScrollPane scrollPane_Formulas = new JScrollPane(table_Formula);
		scrollPane_Formulas
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Formula_Panel.add(scrollPane_Formulas, BorderLayout.CENTER);
		// ������������������������������������������������������������������������������������������������������������
		// Destino Table
		// ===============================================================================================

		JLabel lblDestino = new JLabel("Destino :");
		lblDestino.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDestino.setBounds(10, 417, 69, 20);
		ContentPanel.add(lblDestino);

		JPanel Destino_Panel = new JPanel(new BorderLayout());
		Destino_Panel.setBounds(10, 436, 659, 181);
		ContentPanel.add(Destino_Panel);

		table_Destino = new JTable();
		table_Destino.setModel(new DefaultTableModel(new Object[][] {},
				ColData_Destino));
		table_Destino.setFillsViewportHeight(true);// para encher o espaco do
													// panel

		// columns size (not resizable)
		// ------------------------------------------

		table_Destino.getColumnModel().getColumn(0).setPreferredWidth(150);
		table_Destino.getColumnModel().getColumn(0).setMinWidth(150);
		table_Destino.getColumnModel().getColumn(0).setMaxWidth(1000);

		table_Destino.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_Destino.getColumnModel().getColumn(1).setMinWidth(150);
		table_Destino.getColumnModel().getColumn(1).setMaxWidth(1000);

		table_Destino.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_Destino.getColumnModel().getColumn(2).setMinWidth(150);
		table_Destino.getColumnModel().getColumn(2).setMaxWidth(1000);
		// ::::::::::::::::::::::::::::::::::::::::::::::::::::::
		// table colon title height
		JTableHeader header2 = table_Destino.getTableHeader();
		Dimension d2 = header2.getPreferredSize();
		d2.height = 30;
		header2.setPreferredSize(d2);
		// ::::::::::::::::::::::::::::::::::::::::::::::::::::::

		model_Destino = (DefaultTableModel) table_Destino.getModel();// to add
																		// rows

		table_Destino.setRowHeight(30);

		JScrollPane scrollPane_Destion = new JScrollPane(table_Destino);
		scrollPane_Destion
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Destino_Panel.add(scrollPane_Destion, BorderLayout.CENTER);
		
		JButton btnVerificacaoDeQuantidade = new JButton("Verificacao de quantidade");
		btnVerificacaoDeQuantidade.setBounds(739, 262, 157, 23);
		ContentPanel.add(btnVerificacaoDeQuantidade);
		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		// but�es em baixo ...
		// ******************************************************
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// BUTTONS ********************************
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPanel.add(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRowFormula();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPanel.add(cancelButton);
		// *****************************************

		getRootPane().setDefaultButton(okButton);	//to pressing enter the ok button ser o selecionado
		// **************************************************************************
		c.add(scrollPane, BorderLayout.CENTER);
		c.add(buttonPanel, BorderLayout.SOUTH);
		pack();

		setMaximumSize(new Dimension(1058, 858));// maximum size
	}

	@Override
	public void paint(Graphics g) {	//override of the method paint to tweak the maximum size of the frame
		Dimension d = getSize();
		Dimension m = getMaximumSize();
		boolean resize = d.width > m.width || d.height > m.height;
		d.width = Math.min(m.width, d.width);
		d.height = Math.min(m.height, d.height);
		if (resize) {
			Point p = getLocation();
			setVisible(false);
			setSize(d);
			setLocation(p);
			setVisible(true);
		}
		super.paint(g);
	}

	private void addRowFormula() {	//vai receber todos os parametros para adicionar um linha
		model_Formulas.addRow(new Object[] { "a", "ds", "2", "5" });
	}
}