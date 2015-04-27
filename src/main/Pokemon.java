package main;

public class Pokemon {

	Integer ID;
	String nome;
	Integer HP;
	Integer HPMAX;
	Ataque[] ataques; // 4 Ataques
	Tipos tipo;
	boolean isAlive;
	boolean isActive;
	
	public void atacar(Integer ataqIndex) {
		
		// Pegar pokemon oponente
		Pokemon oponente = new Pokemon();
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
	
}
