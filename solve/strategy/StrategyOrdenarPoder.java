package strategy;

import java.util.ArrayList;

import dominio.*;
import visitor.*;
public class StrategyOrdenarPoder implements IStrategy {

	@Override
	public void ordenar(ArrayList<Carta> cartas) {
		// TODO Auto-generated method stub
		VisitorPoder visitor = new VisitorPoder();

		for (int i = 0; i < cartas.size() - 1; i++) {

			for (int j = 0; j < cartas.size() - 1 - i; j++) {

				double poder1 = cartas.get(j).aceptar(visitor);
				double poder2 = cartas.get(j + 1).aceptar(visitor);

				if (poder1 < poder2) {

					Carta aux = cartas.get(j);
					cartas.set(j, cartas.get(j + 1));
					cartas.set(j + 1, aux);

				}

			}

		}

	}

}
