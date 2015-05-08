package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MapaFrame extends JFrame {

	
	Treinador jogador;
	
	//private JPanel contentPane;
	//public Pawn player;
	public Timer time;
	/**
	 * Launch the application.
	 */
	StageController stage;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapaFrame frame = new MapaFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MapaFrame(Treinador jog) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 00, 450, 300);
		
		this.jogador = jog;
		
		//setBounds(0, 0, 900, 600);
		//JPanel contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		//setContentPane(this);		
		//contentPane.setLayout(null);
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		this.setBackground(Color.CYAN);
		stage = new StageController(this);
		//setContentPane(stage);
		//contentPane.add(player);	
		//getContentPane().add(stage);
		time = new Timer (1, stage);
		time.start();		
		
	}
	
	public void paint(Graphics g)
	{
		System.out.println("Eu entro aqui");
		super.paint(g);
	}
}
