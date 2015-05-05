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
			//atacarWithPokemon(index, oponente);
			return pokemons[0].ataques[index].nome;
		} else if (rand < 0.95 && numPoks != 0) {
			// usar item
			return "Item";
			} else {
				//trocar pokemon
				trocarPokemon(gerador.nextInt(numPoks));
				return "Trocou";
			}	
	}
	
	public Actions generateAction() {
		Random gerador = new Random();
		Double rand = Math.random();
		if (rand < 0.8)
		{
			// ataca
			return Actions.Atacar;
		} if (rand < 0.95 && numPoks != 0) {
			// usar item
			return Actions.Item;
		} else {
			//trocar pokemon
			trocarPokemon(gerador.nextInt(numPoks));
			return Actions.Trocar;
		}	
	}
	
	public Ataque generateAtack() {
		Random gerador = new Random();
		Double rand = Math.random();
		int index = gerador.nextInt(3);
		getPokemonAtivo().ataques[index].ID = index;
		return getPokemonAtivo().ataques[index];
	}
	
	
}
