package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Pawn extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image img;
	private String imgName;
	private BufferedImage imgBuff;
	private Image teste;
	
	public int spriteHeight; // tamanho de cada sprite
	public int spriteWidth;
	
	private int x, y; // posicao do ponto esquerdo superior do sprite no JPanel
	private int dx, dy; //velocidade
	//private int xGrid, yGrid; // posicao do ponto esquerdo superior do sprite na grid.
	
	private int u, v; //posicao relativa (u,v) no sprite p.e. de (0;0) a (spriteHeight; spriteWidth) � o primeiro sprite
	Direcao dir = Direcao.Down;
	Direcao anterior = Direcao.Down;
	public boolean isInBattle = false;
	private long framePeriod;
	
	private long time = 0;
	//
	
	/**
	 * Create the panel.
	 */
	public Pawn(String name, int numSpritesX, int numSpritesY, int fps) {
		this.setBackground(new Color (0,0,0,0));
		//this.setForeground(new Color (0,0,0,0));
		this.imgName = name;
		//this.setBackground(Color.PINK);
		ImageIcon imgIcon = new ImageIcon(this.getClass().getResource(this.imgName));
		img = imgIcon.getImage();
		
		try {
			URL path = getClass().getResource("OldWomanWalk.png");
			imgBuff = ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		spriteHeight = img.getHeight(this)/4;
		spriteWidth = img.getWidth(this)/3;
		x = 0;
		y = 0;
		dx = dy = 1;
		//setFocusable(true);
		u = 2*spriteWidth;
		v = 1*spriteHeight;
		this.setBounds(0,0,900,600);
		
		//this.setBorder(new EmptyBorder(0,0,0,0));
		this.framePeriod = 1000/fps; // miliseconds to change a single frame
		setOpaque(false);
		
		System.out.println(this.getBackground());
		}
	
	//GET	
	public int getX() { return x; }
	public int getY() { return y;}
	//public Image getPlayerImg() { return img; }
	
	public void paintComponent(Graphics g)
	{
		//System.out.println(this.getBackground());
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		//g2d.setColor(getBackground());
		//
		//this.setBackground(Color.BLUE);
		//this.setBackground(new Color(0,0,0,0));
		//getRootPane().getGraphics().clearRect(0, 0, 450, 300);;
		//g2d.clearRect(0, 0, 450, 300);
		//g2d.drawRect(20, 20, 500, 500);
		//g2d.fillRect(0, 0, 500, 500);
		//g2d.drawImage(img, x, y, null);
		g2d.drawImage(teste, x, y , null);
		g2d.drawImage(imgBuff, x, y, x + spriteWidth, y + spriteHeight, u, v, u + spriteWidth, v+spriteHeight, null);
	}
	
	public void Draw()
	{
		getRootPane().repaint();
		this.repaint();
		
		
		//this.repaint(x, y, x+spriteWidth, y+spriteHeight);
		//this.update(getGraphics());
		//this.paint(this.getGraphics());
	}
	
	
	
	
	
	
	
	public void update(long elapsedTime)
	{
		time += elapsedTime;
		if (time >= framePeriod)
		{
			time -= framePeriod;
			updateSprite();
		}
		
		
	}
	
	public void move(Direcao d)
	{
		anterior = dir;
		dir = d;
		
		switch(dir)
		{
		case Parado:
			break;
		case Down:
			y += dy;
			break;
		case Left:
			x -= dx;
			break;
		case Right:
			x += dx;
			break;
		case Up:
			y -= dy;
			break;
		default:
			break;		
		}
		
	}
	public void updateSprite()
	{
		// get initial sprite
		if (dir != anterior)
		{
			switch(dir)
			{
			case Down:
				u = 2*spriteWidth;
				v = 2*spriteHeight;
				break;
			case Left:
				u = 0*spriteWidth;
				v = 1*spriteHeight;
				break;
			case Parado:
				u = 2*spriteWidth;
				v = 1*spriteHeight;
				break;
			case Right:
				u = 1*spriteWidth;
				v = 1*spriteHeight;
				break;
			case Up:
				u = 1*spriteWidth;
				v = 3*spriteHeight;
				break;
			default:
				break;			
			}
		}
		 // UPDATE ANIMATION!
			switch(dir)
			{
			case Down:
				if (v == 2*spriteHeight) v = 3*spriteHeight;
				else v = 2*spriteHeight;
				break;
			case Left:
				if(v == 1*spriteHeight) v = 3*spriteHeight;
				else v = 1*spriteHeight;
				break;
			case Parado:
				u = 2*spriteWidth;
				v = 1*spriteHeight;
				break;
			case Right:
				if(v == 1*spriteHeight) v = 2*spriteHeight;
				else v = 1*spriteHeight;
				break;
			case Up:
				if(u == 1*spriteWidth){ u = 2*spriteWidth; v = 0;}
				else {u = 1*spriteWidth; v = 3*spriteHeight;}
				break;
			default:
				break;			
			}
			
			
			
		
		
	}

	public int getNextSqrt() {
		
		int initialLocation = x - (x%15);
		int targetLocation = initialLocation;
		switch(dir)
		{
		case Down:
			break;
		case Left:
			
			break;
		case Parado:
			break;
		case Right:
			if(x % 15 != 0) targetLocation += 15; 
			break;
		case Up:
			break;
		default:
			break;		
		}
		return targetLocation;
	}
	
}
