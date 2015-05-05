package main;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import javax.swing.JList;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SelectPokemon extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectPokemon frame = new SelectPokemon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	JList<String> list = new JList<String>();
	JLabel pkName = new JLabel("Name");
	JLabel pkID = new JLabel("ID - ");
	JList<String> ataques = new JList<String>();
	JLabel lblTipo = new JLabel("Tipo");
	JLabel[] lblPokemon = new JLabel[6];
	Pokemon[] poks;
	Pokemon[] selectedPokemons = new Pokemon[6];
	
	
	public SelectPokemon() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][][][][][][grow]", "[grow][][grow]"));
		
		
		
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(250, 80));
		scrollPane.setBackground(Color.RED);
		contentPane.add(scrollPane, "cell 0 0 8 1,grow");
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				DBReader.queryPokemons();
				poks = DBReader.poks;
				Pokedex.pokemons = poks;
				DefaultListModel<String> itens = new DefaultListModel<String>();
				
				for (int i = 0; i < 151; i++) {
					itens.addElement(poks[i].nome);
				}		
				
				list.setModel(itens);
			}
		}).run();
		
		System.out.println("Loading");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, "cell 8 0 2 1,grow");
		
		
		ataques.setBackground(Color.ORANGE);
		panel.setLayout(new MigLayout("", "[30px][37px]", "[16px][][109px]"));
		panel.add(pkID, "cell 0 0,alignx left,aligny center");
		panel.add(pkName, "cell 1 0,growx,aligny center");
		
		
		panel.add(lblTipo, "cell 0 1 2 1");
		panel.add(ataques, "cell 0 2 2 1,grow");
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = list.getSelectedIndex();
				Pokemon pk = poks[index];
				pkName.setText(pk.nome );
				pkID.setText(String.valueOf(pk.ID + " - "));
				lblTipo.setText("Tipo" + pk.tipo);
				DefaultListModel<String> ataquesModel = new DefaultListModel<String>();
				for (int a = 0; a < 4; a++) {
					if (pk.ataques[a] != null) {
						ataquesModel.addElement(pk.ataques[a].nome);
					}
				}
				ataques.setModel(ataquesModel);
				pkName.setIcon(new ImageIcon(pk.img));
			}
		});
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		contentPane.add(panel_1, "cell 0 2 10 1,growx,aligny center");
		panel_1.setLayout(new MigLayout("", "[][][][][][][]", "[][][]"));
		
		lblPokemon[0] = new JLabel("1");
		panel_1.add(lblPokemon[0], "cell 0 0");
		
		lblPokemon[1] = new JLabel("2");
		panel_1.add(lblPokemon[1], "cell 1 0");
		
		lblPokemon[2] = new JLabel("3");
		panel_1.add(lblPokemon[2], "cell 2 0");
		
		lblPokemon[3] = new JLabel("4");
		panel_1.add(lblPokemon[3], "cell 3 0");
		
		lblPokemon[4] = new JLabel("5");
		panel_1.add(lblPokemon[4], "cell 4 0");
		
		lblPokemon[5] = new JLabel("6");
		panel_1.add(lblPokemon[5], "cell 5 0");

		this.i = 0;
		
		class addPokemon implements ActionListener {

			public addPokemon(int index) {
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (SelectPokemon.this.i <= 5) {
					int index = list.getSelectedIndex();
					Pokemon pok = poks[index];
					selectedPokemons[SelectPokemon.this.i] = new Pokemon(index+1, pok.nome, pok.tipo, pok.ataques);
					lblPokemon[SelectPokemon.this.i].setText("");
					lblPokemon[SelectPokemon.this.i].setIcon(new ImageIcon(pok.img));
					SelectPokemon.this.i++;
					System.out.println(SelectPokemon.this.i);
					
				}
			}
			
		}
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new addPokemon(this.i));

		contentPane.add(btnNewButton, "flowx,cell 9 1,alignx right");
		
		JButton btnDone = new JButton("DONE");
		
		btnDone.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Treinador jogador = new Treinador("Alfredo", selectedPokemons);
				Teste mapa = new Teste(jogador);
				mapa.setVisible(true);
//				Battle mainBattle = new Battle(jogador);
//				mainBattle.updateBattle();
//				mainBattle.setVisible(true);
				SelectPokemon.this.dispose();
			}
		});
		
		contentPane.add(btnDone, "cell 9 1");
		
	}
	

	int i;
	
}
