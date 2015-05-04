package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Pokemon {

	Integer ID;
	String nome;
	Integer HP;
	Integer HPMAX;
	Ataque[] ataques; // Até 4 Ataques
	Tipos tipo;
	boolean isAlive;
	boolean isActive;
	BufferedImage img;
	
	public Pokemon(int id, String nome, Tipos type, Ataque[] atks){
		this.ID = id;
		this.nome = nome;
		this.HP = 140;
		this.HPMAX = 152;
		this.tipo = type;
		this.isAlive = true;
		this.isActive = false;
		ataques = atks;
		setImage();
	}
	
	public void setImage() {
		
		try {
			URL path = getClass().getResource("sprites/" + ID + ".png");
			img = ImageIO.read(new File(path.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void atacar(Integer ataqIndex, Pokemon oponente) {
		
		// Pegar pokemon oponente
		// Caclular Dano
		Integer max = ataques[ataqIndex].maxDamage;
		Integer min = ataques[ataqIndex].minDamage;
		
		Integer dano = (int) ((Math.random() * (max - min)) +  min);
		
		oponente.levarDano(dano);
		
	}
	
	public void levarDano(Integer dano) {
		
		this.HP -= dano;
		if (this.HP <= 0) {
			this.HP = 0;
			this.isAlive = false;
		}
		
	}
	
	public void usarItem(Item item) {
		
		this.HP += item.healPower;
		if (HP > HPMAX) {
			HP = HPMAX;
		}
		
	}
	
	public int getPercentageLifePoints() {
		return (int)(((double)this.HP/this.HPMAX) * 100);
	}
	
}
