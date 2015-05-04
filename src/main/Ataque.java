package main;

public class Ataque {
	
	Integer ID;
	String nome;
	Tipos tipo;
	Integer maxDamage;
	Integer minDamage;
	Integer prioridade;	
	
	
	public Ataque(String name, Tipos type, int baseDamage, int prio) {
		this.nome = name;
		this.tipo = type;
		this.prioridade = prio;
		
		this.minDamage = baseDamage - 10;
		this.maxDamage = baseDamage + 10;
		
	}
	
	public Ataque(AtackType typeAtk)
	{
		switch(typeAtk)
		{
		case Growl:
			setValues("Growl", Tipos.Normal, 5, 10, 20);
			break;
		case LeechSeeds:
			setValues("LeechSeeds", Tipos.Normal, 5, 10, 20);
			break;
		case RazorLeaf:
			setValues("RazorLeaf", Tipos.Normal, 5, 10, 20);
			break;
		case SeedBomb:
			setValues("SeedBomb", Tipos.Normal, 5, 10, 20);
			break;
		case Tackle:
			setValues("Tackle", Tipos.Normal, 5, 10, 20);
			break;
		case VineWhip:
			setValues("VineWhip", Tipos.Normal, 5, 10, 20);
			break;
		default:
			break;
			
		}
	}
	
	private void setValues(String nome, Tipos tipo, Integer prioridade, Integer minDmg, Integer maxDmg)
	{
		this.nome = nome;
		this.tipo = tipo;
		this.prioridade = prioridade;
		this.maxDamage = maxDmg;
		this.minDamage = minDmg;
	}
}
