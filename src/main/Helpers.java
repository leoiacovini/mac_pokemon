package main;

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
	
	
}
