package logica;

import java.util.Scanner;
import java.io.*;
import gui.VentanaPrincipal;
public class App {
	
	public static ISistema sistema = SistemaImpl.getInstance();
	public static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		cargarSobres();
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.setVisible(true);
		}


	private static void cargarSobres() throws FileNotFoundException {
		Scanner sarch = new Scanner(new File("sobres.txt"));
		
		while (sarch.hasNextLine()) {
			String linea = sarch.nextLine();
			String[] partes = linea.split(";");
			
			sistema.crearCarta(partes);
			
		}
		sarch.close();
		
	}
	

}
