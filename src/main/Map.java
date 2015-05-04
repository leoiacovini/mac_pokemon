package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Map extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	BufferedImage img;
	
	public Map(String nome) {
		try {
			URL path = getClass().getResource(nome);
			img = ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g; 
		setBackground(Color.GREEN);
		//System.out.println("Height:" + img.getHeight()+ " Width:" + img.getWidth());
		g2d.drawImage(img, 0, 0, null);
	}
	
	public void Draw()
	{
		this.repaint();
	}
}
