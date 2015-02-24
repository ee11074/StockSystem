package GIC;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Core.Core;
import GIC.Dialogs.FabricDialog;
import GIC.Dialogs.NovoArtigoDialog;
import GIC.Dialogs.Quebras;
import GIC.Dialogs.SaidaTensferenciaDialog;
import HistoryFiles.Fabricacao;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	private Core dataBase;
	private JPanel ContentPanel;

	private JButton btnFabricacoes;
	private JButton btnSadastransferncias;
	private JButton btnNovoArtigo;
	private JButton btnQuebras;

	private JComboBox<Listagens> comboBox;
    private static enum Listagens {
    	fabricacoes,saidas,outros;
    }
    
	public MainMenu(Core Data) {
		super("Menu");

		this.dataBase = Data;

		// setBounds(100, 100, 352, 372);
		
		// content Panel
		// ************************************************************
		ContentPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(ContentPanel);
		ContentPanel.setLayout(null);

		// para obrigar as barras do scroll pane aparecerem
		ContentPanel.setPreferredSize(new Dimension(335, 250));// just to prove that the dimensions bars are working
		// scrollPane.setPreferredSize(new Dimension(400, 400));// just to prove that the dimension bares are working
		scrollPane.setPreferredSize(new Dimension(335, 250));

		btnFabricacoes = new JButton("Fabricacao");
		btnFabricacoes.setBounds(10, 39, 130, 23);
		btnFabricacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FabricDialog dialog = new FabricDialog(MainMenu.this, dataBase);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnSadastransferncias = new JButton("Saidas/transf.");
		btnSadastransferncias.setBounds(160, 39, 130, 23);
		btnSadastransferncias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SaidaTensferenciaDialog dialog = new SaidaTensferenciaDialog(MainMenu.this, dataBase);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnNovoArtigo = new JButton("Novo Artigo");
		btnNovoArtigo.setBounds(10, 85, 130, 23);
		btnNovoArtigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					NovoArtigoDialog dialog = new NovoArtigoDialog(MainMenu.this, dataBase);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnQuebras = new JButton("Quebras");
		btnQuebras.setBounds(160, 87, 130, 23);
		btnQuebras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Quebras dialog = new Quebras(MainMenu.this, dataBase);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		comboBox = new JComboBox<Listagens>(Listagens.values());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		comboBox.setBounds(160, 140, 130, 20);

		JLabel lblNewLabel = new JLabel("Listagens:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(66, 138, 84, 20);

		ContentPanel.add(comboBox);
		ContentPanel.add(lblNewLabel);
		ContentPanel.add(btnNovoArtigo);
		ContentPanel.add(btnFabricacoes);
		ContentPanel.add(btnSadastransferncias);
		ContentPanel.add(btnQuebras);

		getContentPane().add(scrollPane);
		
		pack();
		setMaximumSize(new Dimension(1058, 858));// maximum size
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu(new Core());
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
