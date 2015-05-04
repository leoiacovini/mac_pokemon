package main;
import java.util.Random;

public class AIController extends Treinador {

	public AIController(String nome, Pokemon[] poks) {
		super(nome, poks);
		this.nome = "AI";
	}
	
	
	public void turno() {
		Random gerador = new Random();
		Double rand = Math.random();
		if (rand < 0.7)
		{
			// atacar
			//atacarWithPokemon(gerador.nextInt(4));
		} else if (rand < 0.9) {
			// usar item
			
			} else {
				//trocar pokemon
				trocarPokemon(gerador.nextInt(6));
			}
		
	}
	
}
