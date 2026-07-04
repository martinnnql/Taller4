package gui;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

public class Imagen {

	private static final String CARPETA_IMAGENES = "imagenes/";

	public static ImageIcon cargarImagen(String nombreCarta, int ancho, int alto) {
		String nombreLimpio = nombreCarta.trim().toLowerCase() + ".png";
		File archivo = new File(CARPETA_IMAGENES + nombreLimpio);

		// Si la carta no existe en la carpeta, usamos el default
		if (!archivo.exists()) {
			archivo = new File(CARPETA_IMAGENES + "default.png");
		}

		if (!archivo.exists()) {
			return null;
		}

		try {
			URL urlArchivo = archivo.toURI().toURL();
			ImageIcon iconoOriginal = new ImageIcon(urlArchivo);

			if (iconoOriginal.getImage() == null || iconoOriginal.getIconWidth() == -1) {
				return null;
			}

			Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
			return new ImageIcon(imagenEscalada);

		} catch (Exception e) {
			System.out.println("Error procesando imagen: " + e.getMessage());
			return null;
		}
	}
}