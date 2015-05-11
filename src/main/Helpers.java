package main;

import java.rmi.dgc.DGC;

public class Helpers {

	static public Tipos typeFromString(String typeStr) {
		
		switch (typeStr) {
		case "grass":
			return Tipos.Grass;
		case "water":
			return Tipos.Water;
		case "fire":
			return Tipos.Fire;
		case "normal":
			return Tipos.Normal;
		case "ghost":
			return Tipos.Ghost;
		case "dark":
			return Tipos.Dark;
		case "poison":
			return Tipos.Poison;
		case "fighting":
			return Tipos.Fighting;
		case "psychic":
			return Tipos.Psychic;
		case "rock":
			return Tipos.Rock;
		case "ground":
			return Tipos.Ground;
		case "steel":
			return Tipos.Steel;
		case "bug":
			return Tipos.Bug;
		case "ice":
			return Tipos.Ice;
		case "dragon":
			return Tipos.Dragon;
		case "electric":
			return Tipos.Electric;
		case "flying":
			return Tipos.Flying;
		case "unknown":
			return Tipos.Unknown;
		default:
			return null;
		}
	}
	
	static public double calcularDamageModfier(Tipos atacante, Tipos defensor) {
		
		double dmgMultiplier = 1;
		
		System.out.println(atacante.ordinal());
		
		double[][] dmgMatrix = {
				{1, 1, 1, 1, 1, 0.5, 1, 0, 0.5, 1, 1, 1, 1, 1, 1, 1, 1}, // Normal
				{2, 1, 0.5, 0.5, 1, 2, 0.5, 0, 2, 1, 1, 1, 1, 0.5, 2, 1, 2}, // Fight
				{1, 2, 1, 1, 1, 0.5, 2, 1, 0.5, 1, 1, 2, 0.5, 1, 1, 1, 1}, // Flying
				{1, 1, 1, 0.5, 0.5, 0.5, 1, 0.5, 0, 1, 1, 2, 1, 1, 1, 1, 1, 2 }, // Poison
				{1, 1, 0, 2, 1, 2, 0.5, 1, 2, 2, 1, 0.5, 2, 1, 1, 1, 1}, // Ground
				{1, 0.5, 2, 1, 0.5, 1, 2, 1, 0.5, 2, 1, 1, 1, 1, 2, 1, 1}, // Rock
				{1, 0.5, 0.5, 0.5, 1, 1, 1, 0.5, 0.5, 0.5, 1, 2, 1, 2, 1, 1, 2}, // Bug
				{0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 0.5}, // Ghost
				{1, 1, 1, 1, 1, 2, 1, 1, 0.5, 0.5, 0.5, 1, 0,5, 1, 2, 1, 1}, // Steel
				{1, 1, 1, 1, 1 , 0.5, 2, 1, 2, 0.5, 0.5, 2, 1, 1, 2, 0.5, 1}, // Fire
				{1, 1, 1, 1 , 2, 2, 1 ,1 ,1 , 2, 0.5, 0.5, 1 ,1 ,1 , 0.5, 1}, // Water
				{1, 1, 0.5, 0.5, 2, 2, 0.5, 1, 0.5, 0.5, 2, 0.5, 1, 1, 1,  0.5, 1}, //Grass
				{1, 1, 2 ,1 ,0, 1, 1, 1, 1, 1, 2, 0.5, 0.5, 1, 1, 0.5, 1}, // Electric
				{1, 2, 1, 2, 1, 1, 1, 1, 0.5, 1, 1, 1, 1,  0.5, 1, 1, 0}, // Psychic
				{1, 1, 2, 1, 2, 1, 1, 1, 0.5, 0.5, 0.5, 2, 1, 1, 0.5, 2, 1}, // Ice
				{1, 1, 1, 1, 1, 1, 1, 1, 0.5, 1, 1, 1, 1, 1, 1, 2, 1}, // Dragon
				{1, 0.5, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 0.5} // Dark
				}; 
		
		
		dmgMultiplier = dmgMatrix[atacante.ordinal()][defensor.ordinal()];
		
		return dmgMultiplier;
	}
	
}
