package GIC.Dialogs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Core.Core;
import MyTableModel.MyTableModel;
import ProCore.Artigo;
import ProCore.Unidades;

// Nota:
// perguntar ao certo quais os parametros de fabricavel e compravel pois eu nao sei ao certo o que emgloba a cheveta e nao queria estar a fazer sem saber

public class NovoArtigoDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	// Program Core Data Base where the information is save

	private Core DataBase;

	// ----------------------------------------------------

	private final JPanel buttonPanel;
	private final JButton SalvarButton;
	private final JButton cancelarButton;

	private final JPanel ContentPanel;

	// New Artigo Data -----------------------------------
	private JLabel lbl_Codigo;
	private JTextField textField_Codigo;

	private JLabel lbl_NomeDoArtigo;
	private JTextField textField_NomeArtigo;

	private JLabel lbl_Saldo;
	private JTextField textField_Saldo;

	private JLabel lbl_UnidadesAssociadas;
	private JComboBox<Unidades> comboBox_UnidadesAssociadas;

	// radio button to separate Fabricavel from compravel
	private ButtonGroup Button_Group_First;
	private JRadioButton rdbtn_fabricavel;
	private JRadioButton rdbtn_compravel;

	// Diferent Panels to fabricavel e compravel from cardlayout
	private JPanel Cards;

	private JPanel card1_Fabricavel;
	private final String FabricavelCardString = "Fabricavel";

	private JPanel card2_Compravel;
	private final String CompravelCardString = "Compravel";

	// Fabricable Card Data -------------------------------
	private JLabel lblFormula;

	private JLabel lbl_GrauAlcoolico;
	private JTextField textField_GrauAlcoolico;

	private JLabel lbl_Densidade;
	private JTextField textField_Densidade;

	private JLabel lbl_TaxaRendimento;
	private JTextField textField_TaxaRendimento;

	// table
	private JTable table_Formulas;
	private String ColData[] = new String[] { "Artigo", "Quantidade", "Unidades" };
	// -----

	private JComboBox<Artigo> comboBox_AddArtigo;
	private JButton btn_AddArtigo;
	private JButton btn_RemoveSelectedArtigo;
	
	// panel de vaislhas
	private JPanel panel_Vasilhas;
	private JPanel panel_VasilhaAssuciada;
	private JLabel lblVasilhasAssociadas;
	
	private ButtonGroup Button_Group_Vasilhas;	
	private JRadioButton rdbtn_CriarNova;	
	private JRadioButton rdbtn_UtilizarExistente;

	// need to add to the button group and creat te bas string 
	// ------------------------------------------------
	public NovoArtigoDialog(JFrame mainMenu, Core dataBase) {
		super(mainMenu, "Novo Artigo", true);

		this.DataBase = dataBase;
		// setBounds(100, 100, 650, 450);

		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		// content Panel
		// ************************************************************
		ContentPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(ContentPanel);

		// para obrigar as barras do scroll pane aparecerem
		ContentPanel.setPreferredSize(new Dimension(1024, 768));
		ContentPanel.setLayout(null);

		// scrollPane.setPreferredSize(new Dimension(400, 400));// just to prove that the dimension bares are working
		scrollPane.setPreferredSize(new Dimension(1024, 768));

		// ======================================================

		lbl_Codigo = new JLabel("Codigo: ");
		lbl_Codigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbl_Codigo.setBounds(23, 11, 64, 20);
		ContentPanel.add(lbl_Codigo);

		textField_Codigo = new JTextField();
		textField_Codigo.setBounds(92, 11, 86, 20);
		ContentPanel.add(textField_Codigo);
		textField_Codigo.setColumns(10);

		lbl_NomeDoArtigo = new JLabel("Nome do Artigo: ");
		lbl_NomeDoArtigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbl_NomeDoArtigo.setBounds(254, 11, 125, 20);
		ContentPanel.add(lbl_NomeDoArtigo);

		textField_NomeArtigo = new JTextField();
		textField_NomeArtigo.setBounds(378, 11, 294, 20);
		ContentPanel.add(textField_NomeArtigo);
		textField_NomeArtigo.setColumns(10);

		lbl_Saldo = new JLabel("Saldo: ");
		lbl_Saldo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbl_Saldo.setBounds(34, 59, 53, 20);
		ContentPanel.add(lbl_Saldo);

		textField_Saldo = new JTextField();
		textField_Saldo.setBounds(92, 59, 86, 20);
		ContentPanel.add(textField_Saldo);
		textField_Saldo.setColumns(10);

		lbl_UnidadesAssociadas = new JLabel("Unidades Associadas: ");
		lbl_UnidadesAssociadas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbl_UnidadesAssociadas.setBounds(218, 60, 161, 19);
		ContentPanel.add(lbl_UnidadesAssociadas);

		comboBox_UnidadesAssociadas = new JComboBox(Unidades.values());
		// comboBox_UnidadesAssociadas = new JComboBox<Unidades>(Unidades.values());
		comboBox_UnidadesAssociadas.setSelectedIndex(-1);// because i do not like to select a default value
		comboBox_UnidadesAssociadas.setBounds(378, 61, 64, 20);
		ContentPanel.add(comboBox_UnidadesAssociadas);

		// ***************************************************************************************************
		// ************** RADIO BUTTON **************
		// ***************************************************************************************************
		Button_Group_First = new ButtonGroup();// to control all the jRadioButton because i only want to select one at the time

		rdbtn_fabricavel = new JRadioButton(FabricavelCardString);
		rdbtn_fabricavel.setSelected(true);
		rdbtn_fabricavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout) Cards.getLayout()).show(Cards, FabricavelCardString);
			}
		});
		rdbtn_fabricavel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		rdbtn_fabricavel.setBounds(23, 121, 109, 23);
		Button_Group_First.add(rdbtn_fabricavel);
		ContentPanel.add(rdbtn_fabricavel);

		rdbtn_compravel = new JRadioButton("Compravel");
		rdbtn_compravel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) Cards.getLayout()).show(Cards, CompravelCardString);
			}
		});
		rdbtn_compravel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		rdbtn_compravel.setBounds(195, 121, 118, 23);
		Button_Group_First.add(rdbtn_compravel);
		ContentPanel.add(rdbtn_compravel);

		// CardLayout to change the layout of Fabricavel e compravel
		Cards = new JPanel(new CardLayout());// creating the panel that olds all the cards panels
		Cards.setBounds(23, 157, 849, 489);

		card1_Fabricavel = new JPanel(); // creating the fabricavel card panel
		Cards.add(card1_Fabricavel, FabricavelCardString);// adding the fabricavel panel to the cardlayout panel whit the search associated string
		card1_Fabricavel.setLayout(null);

		card2_Compravel = new JPanel(new BorderLayout()); // creating the compravel card panel
		card2_Compravel.setBackground(Color.RED);
		Cards.add(card2_Compravel, CompravelCardString);// adding the Compravel panel to the cardlayout panel whit the search associated string

		ContentPanel.add(Cards);

		// ***************************************************************************************
		// ***************************** Card Fabricavel *****************************************
		// ***************************************************************************************

		lblFormula = new JLabel("Formula: ");
		lblFormula.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFormula.setBounds(10, 2, 87, 22);
		card1_Fabricavel.add(lblFormula);

		// Formulas Table ------------------------------------

		JPanel panel_Formula = new JPanel(new BorderLayout());
		panel_Formula.setBounds(10, 25, 478, 183);
		card1_Fabricavel.add(panel_Formula);

		// Creating JTable_Destino ---------------------------
		table_Formulas = new JTable(new MyTableModel(ColData));
		table_Formulas.setFillsViewportHeight(true);// para encher o espaco do panel
		table_Formulas.setRowHeight(25); // to make the table rows have the default height of 25

		JScrollPane scrollPane_Table = new JScrollPane(table_Formulas);
		scrollPane_Table.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_Formula.add(scrollPane_Table, BorderLayout.CENTER);

		// ----------------------------------------------------
		
		comboBox_AddArtigo = new JComboBox();
		comboBox_AddArtigo.setBounds(10, 217, 178, 20);
		card1_Fabricavel.add(comboBox_AddArtigo);

		btn_AddArtigo = new JButton("Adiciona Artigo");
		btn_AddArtigo.setBounds(198, 216, 110, 23);
		card1_Fabricavel.add(btn_AddArtigo);

		btn_RemoveSelectedArtigo = new JButton("Remove Artigo Selecionado");
		btn_RemoveSelectedArtigo.setBounds(8, 240, 180, 23);
		card1_Fabricavel.add(btn_RemoveSelectedArtigo);

		// right data from the table --------------------------
		lbl_GrauAlcoolico = new JLabel("Grau Alcoolico: ");
		lbl_GrauAlcoolico.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbl_GrauAlcoolico.setBounds(523, 35, 110, 19);
		card1_Fabricavel.add(lbl_GrauAlcoolico);

		textField_GrauAlcoolico = new JTextField();
		textField_GrauAlcoolico.setBounds(632, 34, 110, 20);
		card1_Fabricavel.add(textField_GrauAlcoolico);
		textField_GrauAlcoolico.setColumns(10);

		lbl_Densidade = new JLabel("Densidade: ");
		lbl_Densidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbl_Densidade.setBounds(546, 83, 87, 20);
		card1_Fabricavel.add(lbl_Densidade);

		textField_Densidade = new JTextField();
		textField_Densidade.setBounds(632, 83, 110, 20);
		card1_Fabricavel.add(textField_Densidade);
		textField_Densidade.setColumns(10);

		lbl_TaxaRendimento = new JLabel("Taxa Rendimento: ");
		lbl_TaxaRendimento.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbl_TaxaRendimento.setBounds(498, 129, 135, 19);
		card1_Fabricavel.add(lbl_TaxaRendimento);

		textField_TaxaRendimento = new JTextField();
		textField_TaxaRendimento.setBounds(632, 130, 110, 20);
		card1_Fabricavel.add(textField_TaxaRendimento);
		textField_TaxaRendimento.setColumns(10);
		
		// vasilhas panel --------------------
		panel_Vasilhas = new JPanel();
		panel_Vasilhas.setBounds(10, 274, 829, 204);
		card1_Fabricavel.add(panel_Vasilhas);
		panel_Vasilhas.setLayout(null);
		
		Button_Group_Vasilhas = new ButtonGroup();
		
		rdbtn_CriarNova = new JRadioButton("Criar Nova");
		rdbtn_CriarNova.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		rdbtn_CriarNova.setBounds(6, 38, 124, 23);
		panel_Vasilhas.add(rdbtn_CriarNova);
		Button_Group_Vasilhas.add(rdbtn_CriarNova);
		
		rdbtn_UtilizarExistente = new JRadioButton("Utilizar Existente");
		rdbtn_UtilizarExistente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		rdbtn_UtilizarExistente.setBounds(132, 38, 156, 23);
		panel_Vasilhas.add(rdbtn_UtilizarExistente);
		Button_Group_Vasilhas.add(rdbtn_UtilizarExistente);
		
		panel_VasilhaAssuciada = new JPanel();
		panel_VasilhaAssuciada.setBounds(6, 68, 813, 125);
		panel_Vasilhas.add(panel_VasilhaAssuciada);
		panel_VasilhaAssuciada.setLayout(new CardLayout(0, 0));
		
		lblVasilhasAssociadas = new JLabel("Vasilhas Associadas: ");
		lblVasilhasAssociadas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblVasilhasAssociadas.setBounds(10, 11, 156, 20);
		panel_Vasilhas.add(lblVasilhasAssociadas);

		// ***************************************************************************************************
		// ************** but�es em baixo ... **************
		// ***************************************************************************************************

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// BUTTONS ********************************
		SalvarButton = new JButton("Salvar");
		SalvarButton.setActionCommand("OK");
		buttonPanel.add(SalvarButton);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelarButton.setActionCommand("Cancel");
		buttonPanel.add(cancelarButton);
		// *****************************************

		getRootPane().setDefaultButton(SalvarButton); // to pressing enter the ok button ser o selecionado
		// **************************************************************************
		c.add(scrollPane, BorderLayout.CENTER);
		c.add(buttonPanel, BorderLayout.SOUTH);
		pack();

		setMaximumSize(new Dimension(1058, 858));// maximum size
	}
}
