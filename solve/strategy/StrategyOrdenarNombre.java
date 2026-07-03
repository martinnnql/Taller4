package strategy;

import java.util.ArrayList;

import dominio.Carta;

public class StrategyOrdenarNombre implements IStrategy {

	@Override
	public void ordenar(ArrayList<Carta> cartas) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cartas.size() - 1; i++) {

			for (int j = 0; j < cartas.size() - 1 - i; j++) {

				if (cartas.get(j).getNombreCarta().compareToIgnoreCase(cartas.get(j + 1).getNombreCarta()) > 0) {

					Carta aux = cartas.get(j);
					cartas.set(j, cartas.get(j + 1));
					cartas.set(j + 1, aux);

				}

			}

		}

	}

}
