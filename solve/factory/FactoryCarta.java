package factory;
import dominio.*;
public class FactoryCarta {

	public static Carta crearCarta(String[] partes) {
		switch (partes[2]) {
		case "Pokemon":
			return new Pokemon(partes[0], Integer.parseInt(partes[1]), partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]));
		case "Item":
			return new Item(partes[0], Integer.parseInt(partes[1]), partes[2], Integer.parseInt(partes[3]));
		case "Supporter":
			return new Supporter(partes[0], Integer.parseInt(partes[1]), partes[2], Integer.parseInt(partes[3]));
		case "Energy":
			return new Energy(partes[0], Integer.parseInt(partes[1]), partes[2], partes[3]);
		default:
			throw new IllegalArgumentException("Unexpected value: " + partes[2]);
		}
	}
}
