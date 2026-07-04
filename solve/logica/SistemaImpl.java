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
		// asd
		
	}

	@Override
	public void eliminarCarta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarCarta() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Carta> getCartas() {
        return this.cartas;
    }
	
	public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void ejecutarOrdenamiento() {
        if (this.strategy != null) {
            this.strategy.ordenar(cartas);
        }
    }

    public void agregarCartaDirecto(Carta nueva) {
    	cartas.add(nueva);
    	guardarEnArchivo();
    }

    public void eliminarCartaDirecto(Carta c) {
    	cartas.remove(c);
    	guardarEnArchivo();
    }

    public void guardarEnArchivo() {
    	try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter("sobres.txt"))) {
    		for (Carta c : cartas) {
    			pw.println(c.guardar());
    		}
    	} catch (java.io.IOException e) {
    		System.err.println("Error al guardar archivo: " + e.getMessage());
    	}
    }
	
}
