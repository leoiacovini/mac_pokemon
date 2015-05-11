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
	int numAtaques;
	
	public Pokemon(int id, String nome, Tipos type, Ataque[] atks, int HPMAX){
		this.ID = id;
		this.nome = nome;
		this.HP = HPMAX;
		this.HPMAX = HPMAX;
		this.tipo = type;
		this.isAlive = true;
		this.isActive = false;
		ataques = atks;
		int i;
		for(i = 0; i <= 3 && atks[i] != null; i++); 
		setImage();
		this.numAtaques = i;
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
		// Calcular Dano
		Integer max = ataques[ataqIndex].maxDamage;
		Integer min = ataques[ataqIndex].minDamage;
		
		double dano = ((Math.random() * (max - min)) +  min);
		
		System.out.println("Oponente: " + oponente.tipo);
		
		dano *= Helpers.calcularDamageModfier(ataques[ataqIndex].tipo, oponente.tipo);
		
		oponente.levarDano((int) dano);
		
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
