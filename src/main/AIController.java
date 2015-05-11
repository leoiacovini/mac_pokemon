package main;
import java.util.Random;

public class AIController extends Treinador {
	
	int numPoks ;
	
	public AIController(String nome, Pokemon[] poks) {
		super(nome, poks);
		this.nome = "AI";
		numPoks = poks.length;
	}
	
	
	public String turno(Pokemon oponente) {
		Random gerador = new Random();
		Double rand = Math.random();
		if (rand < 0.8)
		{
			// atacar
			int index = gerador.nextInt(3);
			return pokemons[0].ataques[index].nome;
		} else  {
			// usar item
			return "Item";
			}
	}
	
	public Actions generateAction() {
		Double rand = Math.random();
		if (rand < 0.8)
		{
			// ataca
			return Actions.Atacar;
		} else {
			// usar item
			return Actions.Item;
		} 	
	}
	
	public Ataque generateAtack() {
		Random gerador = new Random();
		int index = gerador.nextInt(3);
		getPokemonAtivo().ataques[index].ID = index;
		return getPokemonAtivo().ataques[index];
	}
	
	
}
