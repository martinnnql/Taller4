package logica;

import java.util.Scanner;
import java.io.*;
public class App {
	
	public static ISistema sistema = SistemaImpl.getInstance();
	public static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		cargarSobres();
		menu();
	}

	private static void menu() {
		String opcion;
		
		do {
			System.out.println("1) Administracion");
			System.out.println("2) Ver Coleccion");
			System.out.println("3) Salir");
			
			opcion = s.nextLine();
			
			if (opcion.equals("3")) {
				System.out.println("Saliendo. . .");
				return;
			}
			
			if (opcion.equals("1")) {
				mostrarAdministracion();
			} else if (opcion.equals("2")) {
				mostrarColeccion();
			}
			
			
			
		} while (!opcion.equals("3"));
		
	}

	private static void mostrarColeccion() {
		// TODO Auto-generated method stub
		
	}

	private static void mostrarAdministracion() {
		String opcion;
		
		do {
			System.out.println("1) Agregar Carta");
			System.out.println("2) Eliminar Carta");
			System.out.println("3) Modificar Carta");
			System.out.println("4) Salir");
			
			opcion = s.nextLine();
			
			if (opcion.equals("4")) {
				System.out.println("Saliendo. . .");
				return;
			}
			
			if (opcion.equals("1")) {
				sistema.agregarCarta();
			} else if (opcion.equals("2")) {
				sistema.eliminarCarta();
			} else if (opcion.equals("3")) {
				sistema.modificarCarta();
			}
			
			
			
		} while (!opcion.equals("4"));
		
		
	}

	private static void cargarSobres() throws FileNotFoundException {
		Scanner sarch = new Scanner(new File("sobres.txt"));
		
		while (sarch.hasNextLine()) {
			String linea = sarch.nextLine();
			String[] partes = linea.split(";");
			
			String NombreCarta = partes[0];
			int Rareza = Integer.parseInt(partes[1]);
			String tipo = partes[2];
			
			sistema.crearCarta(partes);
			
		}
		sarch.close();
		
	}

}
