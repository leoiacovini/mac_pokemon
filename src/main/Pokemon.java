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
	BufferedImage spriteFrente;
	BufferedImage spriteCostas;
	
	public Pokemon(int num){
		this.ID = 1;
		this.nome = "Bulbasaur" + num;
		this.HP = 140;
		this.HPMAX = 152;
		this.tipo = Tipos.Grass;
		this.isAlive = true;
		this.isActive = false;
		ataques = new Ataque[4];
		ataques[0] = new Ataque(AtackType.Tackle);
		ataques[1] = new Ataque(AtackType.LeechSeeds);
		ataques[2] = new Ataque(AtackType.RazorLeaf);
		ataques[3] = new Ataque(AtackType.SeedBomb);
	}
	
	public void setImages(String imageCostas, String imageFrente) {
		
		try {
			URL url = new URL("assets/sprites/costas/" + imageCostas + ".jpg");
			spriteCostas = ImageIO.read(new File(url.getPath()));
			
			url = new URL("assets/sprites/frente/" + imageFrente + ".jpg");
			spriteFrente = ImageIO.read(new File(url.getPath()));
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
