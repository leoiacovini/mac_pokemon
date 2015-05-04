package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PawnController extends KeyAdapter
{
	private Pawn player;
	
	public PawnController(Pawn player)
	{
		this.player = player;
	}
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            player.move(Direcao.Left);
            //System.out.println("-1");
        }

        if (key == KeyEvent.VK_RIGHT) {
        	player.move(Direcao.Right);
            //System.out.println("+1");
        }

        if (key == KeyEvent.VK_UP) {
        	player.move(Direcao.Up);
        }

        if (key == KeyEvent.VK_DOWN) {
        	player.move(Direcao.Down);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	
            System.out.println("LeftUp");
        }

        if (key == KeyEvent.VK_RIGHT) {
        	System.out.println("RightUp");
        }

        if (key == KeyEvent.VK_UP) {
        }

        if (key == KeyEvent.VK_DOWN) {
        }
    } 


}
