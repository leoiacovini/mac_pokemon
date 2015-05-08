package main;
import java.util.Random;


public class Pokedex {

	static public Pokemon[] pokemons = new Pokemon[151];
	
	static public Pokemon getRandomPokemon()
	{
		Random random = new Random();
		int index = random.nextInt(150);
		return new Pokemon(index+1, pokemons[index].nome, pokemons[index].tipo, pokemons[index].ataques, pokemons[index].HPMAX);
		
	}
}
