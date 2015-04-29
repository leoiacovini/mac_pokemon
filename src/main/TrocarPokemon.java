package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TrocarPokemon extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	public Treinador jogador;
	public Battle mainBattle;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrocarPokemon frame = new TrocarPokemon(null, null);
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
	public TrocarPokemon(Battle battle, Treinador trainer) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.jogador = trainer;
		this.mainBattle = battle;
		generatePokemons();
		// for cada pokemon -> criar quadrado -> attr valores -> add no panel + 30px Y
	}
	
	private void  generatePokemons() {
		int y = 6;
		for (int i = 0; i < 6; i++)
		{
			panel = new JPanel();
			if (jogador.pokemons[i].isAlive){
				panel.setBackground(Color.ORANGE);
				if (i == 0) panel.setBackground(Color.BLUE); 
			}
			else panel.setBackground(Color.gray);
			panel.setBounds(6, y, 438, 69);
			panel.addMouseListener(new selectPokemon(i));
			
			contentPane.add(panel);
			panel.setLayout(new MigLayout("", "[37px][61px]", "[16px][]"));
			JLabel lblNome = new JLabel(jogador.pokemons[i].nome);
			panel.add(lblNome, "cell 1 0,alignx left,aligny top");
			JLabel lblVidavida = new JLabel( jogador.pokemons[i].HP +  "/" + jogador.pokemons[i].HPMAX);
			panel.add(lblVidavida, "cell 1 1,alignx left,aligny top");
			y += 79;
		}
	}
	
	class selectPokemon implements MouseListener
	{
		Integer pok;
		public selectPokemon(Integer p)
		{
			this.pok = p;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
			System.out.println("ETA" + pok);
			TrocarPokemon.this.mainBattle.jogador.trocarPokemon(pok);
			TrocarPokemon.this.mainBattle.updateBattle();
			TrocarPokemon.this.dispose();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
