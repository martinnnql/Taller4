package logica;
import java.util.ArrayList;

import dominio.*;
import factory.*;
import visitor.*;
import strategy.*;
public class SistemaImpl implements ISistema {

	public static ArrayList<Carta> cartas = new ArrayList<>();
	public static IStrategy strategy;
	public static IVisitor visitor;
	private static ISistema instance;

	private SistemaImpl() {
	}

	public static ISistema getInstance() {
		if (instance == null) {
			instance = new SistemaImpl();
		}
		return instance;
	}

	@Override
	public void crearCarta(String[] partes) {
		cartas.add(FactoryCarta.crearCarta(partes));
		
	}

	@Override
	public void agregarCarta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCarta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarCarta() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
