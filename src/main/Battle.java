package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;

public class Battle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnItem;
	private JButton btnAtacar;
	private JButton btnTrocarPok;
	private JButton btnRun;
	public JProgressBar jogadorLife;
	public JProgressBar AILife;
	public JLabel lblMeuPokemon;
	public JLabel lblAIPokemon;
	
	public Treinador jogador;
	public AIController AI;
	public JTextPane textPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Battle frame = new Battle();
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
	public Battle() {
		setUpBattle();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][grow]", "[][][][][][][grow][][]"));
		
		lblAIPokemon = new JLabel(AI.getPokemonAtivo().nome);
		contentPane.add(lblAIPokemon, "cell 5 0");
		
		AILife = new JProgressBar();
		AILife.setStringPainted(true);
		AILife.setValue(100);
		contentPane.add(AILife, "cell 5 1");
		
		btnAtacar = new JButton("Atacar");
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtaquesDialog ataquesDiag = new AtaquesDialog(jogador.getPokemonAtivo().ataques);
				ataquesDiag.mainBattle = Battle.this;
				ataquesDiag.setVisible(true);
			}
		});
		
		lblMeuPokemon = new JLabel(jogador.getPokemonAtivo().nome);
		contentPane.add(lblMeuPokemon, "cell 0 4");
		
		jogadorLife = new JProgressBar();
		jogadorLife.setStringPainted(true);
		jogadorLife.setValue(100);
		contentPane.add(jogadorLife, "cell 0 5");
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		textPane.setEditable(false);
		textPane.setBackground(contentPane.getBackground());
		contentPane.add(textPane, "cell 3 5 3 4,grow");
		contentPane.add(btnAtacar, "cell 0 7");
		
		btnItem = new JButton("Item");
		contentPane.add(btnItem, "cell 2 7");
		
		btnTrocarPok = new JButton("Trocar Pok");
		contentPane.add(btnTrocarPok, "cell 0 8");
		
		btnTrocarPok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Mostra lista de pokemons
				TrocarPokemon trocarFrame = new TrocarPokemon(Battle.this, Battle.this.jogador);
				trocarFrame.setVisible(true);
			}
		});
		
		btnRun = new JButton("Run");
		contentPane.add(btnRun, "cell 2 8");
	}
	
	private void setUpBattle() {
		jogador = new Treinador("Alfredo");
		AI = new AIController("AKJSHAJK");
	}

	public void updateBattle() {
		lblMeuPokemon.setText(jogador.getPokemonAtivo().nome);
		lblAIPokemon.setText(AI.getPokemonAtivo().nome);
		int HealthPoint = jogador.getPokemonAtivo().getPercentageLifePoints();
		jogadorLife.setValue(HealthPoint);
		HealthPoint = AI.getPokemonAtivo().getPercentageLifePoints();
		AILife.setValue(HealthPoint);
	}
	
}
