package main;

public class Treinador {
	
	static Integer baseID = 0;
	
	Pokemon pokemons[]; // 6 Pokemons
	Item itens[]; // X Itens
	String nome;
	Integer ID;
	
	public Treinador(String nome, Pokemon[] pks) {
		this.nome = nome;
		this.ID = Treinador.baseID++;
		
		this.pokemons = pks;
		
		itens = new Item[5];
		for (int i = 0; i < 5; i++) {
			itens[i] = new Item(); 
		}
	}
	
	public boolean playerDidLose() {
		
		for (Pokemon pokemon :  pokemons) {
			if (pokemon != null && pokemon.isAlive) {
				return false;
			}
		}
		return true;
	}
	
	public void atacarWithPokemon(Integer ataqIndex, Pokemon oponente) {
		// Mostra ataques do pokemon atual
		// -> Passa a bola pro Pokemon
		// Escolhe ataque e efetua
		
		pokemons[0].atacar(ataqIndex, oponente);
		
	}
	
	public void usarItem(Item item) {
		// Mostra itens itens
		// -> Passa para itens
		// Aplica efeito do item
		
		pokemons[0].usarItem(item);
		
	}
	
	public void trocarPokemon(Integer pokIndex) {
		// Mostra pokemons, seleciona apenas os vivos
		// Escolhe pokemon, e marca como ativo

		Pokemon auxPok = pokemons[0]; //  Primeiro Pokemons é sempre o ativo
		pokemons[0].isActive = false;
		pokemons[0] = pokemons[pokIndex];
		pokemons[pokIndex].isActive = true;
		pokemons[pokIndex] = auxPok;
		
	}
	
	public Pokemon getPokemonAtivo()
	{
		return this.pokemons[0];
	}
	
}
