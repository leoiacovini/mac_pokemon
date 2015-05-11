package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	public MapaFrame mapa;
	
	public JTextPane textPane;
	private JLabel lblImageAi;
	private JLabel lblImage;

	/**
	 * Create the frame.
	 */
	public Battle(Treinador jog) {
		setResizable(false);
		
		jogador = jog;
		
		setUpBattle();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 388);
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
				AtaquesDialog ataquesDiag = new AtaquesDialog(jogador.getPokemonAtivo().ataques, Battle.this);
				ataquesDiag.mainBattle = Battle.this;
				ataquesDiag.setVisible(true);
			}
		});
		
		lblImageAi = new JLabel("Image AI");
		contentPane.add(lblImageAi, "cell 5 2");
		
		lblImage = new JLabel("Image");
		contentPane.add(lblImage, "cell 0 5");
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		textPane.setEditable(false);
		textPane.setBackground(contentPane.getBackground());
		contentPane.add(textPane, "cell 3 5 3 4,grow");
		
		lblMeuPokemon = new JLabel(jogador.getPokemonAtivo().nome);
		contentPane.add(lblMeuPokemon, "flowy,cell 0 6,aligny bottom");
		
		jogadorLife = new JProgressBar();
		jogadorLife.setStringPainted(true);
		jogadorLife.setValue(100);
		contentPane.add(jogadorLife, "cell 0 6,aligny bottom");
		contentPane.add(btnAtacar, "cell 0 7");
		
		btnItem = new JButton("Item");
		contentPane.add(btnItem, "cell 2 7");
		
		btnItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Mostra Lista de itens
				ItemsDialog dialog = new ItemsDialog(jogador, Battle.this);
				dialog.setVisible(true);
			}
		});
		
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
		
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapa.setVisible(true);
				Battle.this.dispose();
			}
		});
		
		contentPane.add(btnRun, "cell 2 8");
		
		updateBattle();
		
	}
	
	
	private void setUpBattle() {
		Pokemon[] AIPoks = new Pokemon[1];
		AIPoks[0] = Pokedex.getRandomPokemon();
		System.out.println("Pok Gerado: " + AIPoks[0].tipo);
		System.out.println("Pok Jogador: " + jogador.getPokemonAtivo().tipo);
		AI = new AIController("Jose de Maria", AIPoks);
	}

	public void updateBattle() {
		if (jogador.playerDidLose()) {
			JOptionPane.showMessageDialog(this, "Você Perdeu");
			System.out.println("O Jogador perdeu");
			endBattle();
			return;
		} else if (AI.playerDidLose()) {
			JOptionPane.showMessageDialog(this, "Você Venceu");
			System.out.println("O AI perdeu");
			endBattle();
			return;
		}
		
		lblMeuPokemon.setText(jogador.getPokemonAtivo().nome);
		lblAIPokemon.setText(AI.getPokemonAtivo().nome);
		int HealthPoint = jogador.getPokemonAtivo().getPercentageLifePoints();
		jogadorLife.setValue(HealthPoint);
		HealthPoint = AI.getPokemonAtivo().getPercentageLifePoints();
		AILife.setValue(HealthPoint);
		lblImage.setText("");
		lblImage.setIcon(new ImageIcon(jogador.getPokemonAtivo().img));
		lblImageAi.setText("");
		lblImageAi.setIcon(new ImageIcon(AI.getPokemonAtivo().img));
		
		if (!jogador.getPokemonAtivo().isAlive) {
			TrocarPokemon trocarFrame = new TrocarPokemon(Battle.this, Battle.this.jogador);
			trocarFrame.setVisible(true);
		}
	}
	
	public void endBattle() {
		
		for(Pokemon pk : jogador.pokemons) {
			pk.HP = pk.HPMAX;
			pk.isAlive = true;
		}
		mapa.setVisible(true);
		Battle.this.dispose();
	}
	
}
