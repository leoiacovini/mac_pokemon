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
	
	public Pokemon(){
		this.ID = 1;
		this.nome = "Bulbasaur";
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
