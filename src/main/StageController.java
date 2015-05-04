package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class StageController extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //WTF?!
	long currentTime = 0;
	long previousTime = 0;
	long elapsedTime;
	boolean init = false;
	Pawn player;
	Map mapa;
	Treinador jogador;
	Teste mainTeste;
	//INIT
	public StageController(Teste T)
	{
		this.jogador = T.jogador;
		this.mainTeste = T;
		//this.setBackground(Color.RED);
		this.setBackground(new Color (0,0,0,0));
		//this.setOpaque(false);
		mapa = new Map("grass.png");
		player = new Pawn("OldWomanWalk.png", 3, 4, 3);
		T.addKeyListener(new PawnController(player));
		//player.setLayout(new GridLayout());
		//player.setBounds(0, 0, 0, 0);
		
		T.getContentPane().add(player);
		T.getContentPane().add(mapa);
		//T.add(mapa);
		//T.add(player);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (!init) init(e);		
		this.Update(e.getWhen());
		
		this.Draw();
	}	
	
	public void init(ActionEvent e)
	{
		previousTime = e.getWhen();
		init = true;
	}
	public void Update(long time)
	{
		currentTime = time;
		elapsedTime = currentTime - previousTime;
				
		player.update(elapsedTime);
							
		previousTime = currentTime;
		if (player.getX() > 100 && !player.isInBattle)
		{
			player.isInBattle = true;
			if(Math.random() < 2)
			{
				Battle battle = new Battle(jogador);
				battle.setVisible(true);
				battle.mapa = mainTeste;
				mainTeste.setVisible(false);
				System.out.println("BATTLE BEGIN");
			}
		}
		
	}
	
	public void Draw()
	{
		mapa.Draw();
		player.Draw();
	}



}
