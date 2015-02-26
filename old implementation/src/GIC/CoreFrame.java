package GIC;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CoreFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static int nItem = 0;
	private JPanel contentPane;

	private JTable table;
	private DefaultTableModel model;

	private String ColData[] = new String[] { "<html>nº</html>",
			"<html>Data</html>", "<html>Código</html>", "<html>Grau(%)</html>",
			"<html>Nome Produto</html>", "<html>F. Consumo</html>",
			"Alcool Incorporado", "<html>un</html>",
			"<html>Rendimento (%)</html>",
			"<html>(Fabricação)<br>1-> Entrada<br>2-> Compra</html>",
			"<html>un</html>",
			"<html>(Proveniência)<br>1-> nº Vasilha<br>2-> Fornecedor</html>",
			"<html>(Consumos)<br>1-> Consumos<br>2-> Venda</html>",
			"<html>un</html>",
			"<html>(Destino)<br>1-> nº Vasilha<br>2-> Cliente</html>",
			"<html>(Quebras)<br>1-> Derrame<br>2-> Manusiamento</html>",
			"<html>un</html>",
			"<html>(Saldo)<br>1-> Vasilha<br>2-> Produto</html>",
			"<html>un</html>" };

	public CoreFrame() {
		super("Gestao do inventorio");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// makes
																				// the
																				// window
																				// feels
																				// a
																				// looks
																				// like
																				// the
																				// native
																				// operating
																				// system
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				closeWindow();
			}
		});

		setBounds(100, 100, 534, 388);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAdd = new JMenu("Add");
		menuBar.add(mnAdd);

		JMenuItem menuItem = new JMenuItem("1");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRow1();// temp
			}
		});
		mnAdd.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("2");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRow2();// temp
			}
		});
		mnAdd.add(menuItem_1);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		String[] col = new String[] {};
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, ColData));

		SetColonSizes();

		// ******************************************************
		// table colon title height
		JTableHeader header = table.getTableHeader();
		Dimension d = header.getPreferredSize();
		d.height = 65;
		header.setPreferredSize(d);
		// ******************************************************

		model = (DefaultTableModel) table.getModel();

		table.setRowHeight(40);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPane.setViewportView(table);

	}

	private void addRow1() {// vai receber todos os parametros para adicionar um
							// linha
		model.addRow(new Object[] { "<html>" + (++nItem) + "</html>",
				"<html>01/01/2014</html>", "<html>83D</html>",
				"<html>13</html>", "<html>Random Name</html>",
				"<html>H = N * P</html>", "<html>" + 0 + "</html>",
				"<html>ml</html>", "<html>66</html>",
				"<html>1-> 2<br>2-> ---</html>", "<html>ml</html>",
				"<html>1-> 2<br>2-> ---</html>",
				"<html>1-> 2<br>2-> ---</html>", "<html>ml</html>",
				"<html>1-> 2<br>2-> ---</html>",
				"<html>1-> 2<br>2-> ---</html>", "<html>ml</html>",
				"<html>1-> 2<br>2-> ---</html>", "<html>ml</html>" });
	}

	private void addRow2() {// vai receber todos os parametros para adicionar um
							// linha
		model.addRow(new Object[] { "<html>" + (++nItem) + "</html>",
				"<html>01/01/2014</html>", "<html>83D</html>",
				"<html>13</html>", "<html>Random Name</html>",
				"<html>H = N * P</html>", "<html>" + 0 + "</html>",
				"<html>ml</html>", "<html>66</html>",
				"<html>1-> ---<br>2-> 2</html>", "<html>ml</html>",
				"<html>1-> ---<br>2-> 2</html>",
				"<html>1-> ---<br>2-> 2</html>", "<html>ml</html>",
				"<html>1-> ---<br>2-> 2</html>",
				"<html>1-> ---<br>2-> 2</html>", "<html>ml</html>",
				"<html>1-> ---<br>2-> 2</html>", "<html>ml</html>" });
	}

	private void closeWindow() {
		Object[] op = { "Sim", "Nao" };
		if (0 == JOptionPane.showOptionDialog(this,
				"Deseja mesmo fechar a aplicaçao?", "Gestao de inventorio", 0,
				JOptionPane.QUESTION_MESSAGE, null, op, op[0])) {
			if (SaveInventory()) {
				dispose();
			} else {
				// tentar criar um ficheiro de backup caso aja erro a passar
				// para ficheiro binario xD
			}
		} else {

		}
	}

	private boolean loadInventory() {
		return true;
	}

	private boolean SaveInventory() {
		return true;
	}

	private void SetColonSizes() {
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(0).setMaxWidth(1000);

		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(1).setMinWidth(95);
		table.getColumnModel().getColumn(1).setMaxWidth(1000);

		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setMinWidth(60);
		table.getColumnModel().getColumn(2).setMaxWidth(1000);

		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setMinWidth(60);
		table.getColumnModel().getColumn(3).setMaxWidth(1000);

		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setMinWidth(200);
		table.getColumnModel().getColumn(4).setMaxWidth(1000);

		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setMinWidth(90);
		table.getColumnModel().getColumn(5).setMaxWidth(1000);

		table.getColumnModel().getColumn(6).setPreferredWidth(145);
		table.getColumnModel().getColumn(6).setMinWidth(145);
		table.getColumnModel().getColumn(6).setMaxWidth(1000);

		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setMinWidth(50);
		table.getColumnModel().getColumn(7).setMaxWidth(1000);

		table.getColumnModel().getColumn(8).setPreferredWidth(115);
		table.getColumnModel().getColumn(8).setMinWidth(115);
		table.getColumnModel().getColumn(8).setMaxWidth(1000);

		table.getColumnModel().getColumn(9).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setMinWidth(100);
		table.getColumnModel().getColumn(9).setMaxWidth(1000);

		table.getColumnModel().getColumn(10).setPreferredWidth(50);
		table.getColumnModel().getColumn(10).setMinWidth(50);
		table.getColumnModel().getColumn(10).setMaxWidth(1000);

		table.getColumnModel().getColumn(11).setPreferredWidth(115);
		table.getColumnModel().getColumn(11).setMinWidth(115);
		table.getColumnModel().getColumn(11).setMaxWidth(1000);

		table.getColumnModel().getColumn(12).setPreferredWidth(110);
		table.getColumnModel().getColumn(12).setMinWidth(110);
		table.getColumnModel().getColumn(12).setMaxWidth(1000);

		table.getColumnModel().getColumn(13).setPreferredWidth(50);
		table.getColumnModel().getColumn(13).setMinWidth(50);
		table.getColumnModel().getColumn(13).setMaxWidth(1000);

		table.getColumnModel().getColumn(14).setPreferredWidth(105);
		table.getColumnModel().getColumn(14).setMinWidth(105);
		table.getColumnModel().getColumn(14).setMaxWidth(1000);

		table.getColumnModel().getColumn(15).setPreferredWidth(135);
		table.getColumnModel().getColumn(15).setMinWidth(135);
		table.getColumnModel().getColumn(15).setMaxWidth(1000);

		table.getColumnModel().getColumn(16).setPreferredWidth(50);
		table.getColumnModel().getColumn(16).setMinWidth(50);
		table.getColumnModel().getColumn(16).setMaxWidth(1000);

		table.getColumnModel().getColumn(17).setPreferredWidth(100);
		table.getColumnModel().getColumn(17).setMinWidth(100);
		table.getColumnModel().getColumn(17).setMaxWidth(1000);

		table.getColumnModel().getColumn(18).setPreferredWidth(50);
		table.getColumnModel().getColumn(18).setMinWidth(50);
		table.getColumnModel().getColumn(18).setMaxWidth(1000);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoreFrame frame = new CoreFrame();
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// to my close method work...
					frame.setLocationRelativeTo(null);// to center the frame on the screen
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
