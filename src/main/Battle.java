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

public class Battle extends JFrame {

	private JPanel contentPane;
	private JButton btnItem;
	private JButton btnAtacar;
	private JButton btnTrocarPok;
	private JButton btnRun;
	private JProgressBar progressBar;
	private JProgressBar progressBar_1;

	
	private Treinador jogador;
	private AIController AI;
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][]", "[][][][][][][][][]"));
		
		JLabel lblAiPokemin = new JLabel("AI Pokemon");
		contentPane.add(lblAiPokemin, "cell 5 0");
		
		progressBar_1 = new JProgressBar();
		progressBar_1.setStringPainted(true);
		progressBar_1.setValue(100);
		contentPane.add(progressBar_1, "cell 5 1");
		
		btnAtacar = new JButton("Atacar");
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtaquesDialog ataquesDiag = new AtaquesDialog(jogador.pokemons[0].ataques);
				ataquesDiag.show();
			}
		});
		
		JLabel lblMeuPokemon = new JLabel("Meu Pokemon");
		contentPane.add(lblMeuPokemon, "cell 0 4");
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(100);
		contentPane.add(progressBar, "cell 0 5");
		contentPane.add(btnAtacar, "cell 0 7");
		
		btnItem = new JButton("Item");
		contentPane.add(btnItem, "cell 2 7");
		
		btnTrocarPok = new JButton("Trocar Pok");
		contentPane.add(btnTrocarPok, "cell 0 8");
		
		btnRun = new JButton("Run");
		contentPane.add(btnRun, "cell 2 8");
	}
	
	private void setUpBattle() {
		
	}

}
