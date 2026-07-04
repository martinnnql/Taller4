package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import dominio.*;
import visitor.*;

public class DetalleCartas extends JFrame {

	public DetalleCartas(Carta carta) {
		setTitle("Ficha Técnica: " + carta.getNombreCarta());
		setSize(420, 530);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));

		// CONTENEDOR DE LA FOTO 
		JPanel panelImagen = new JPanel(new BorderLayout());
		panelImagen.setPreferredSize(new java.awt.Dimension(400, 260));
		panelImagen.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));
		
		ImageIcon icono = Imagen.cargarImagen(carta.getNombreCarta(), 220, 240);
		JLabel lblImagen = new JLabel();
		lblImagen.setHorizontalAlignment(JLabel.CENTER);
		
		if (icono != null) {
			lblImagen.setIcon(icono);
		} else {
			lblImagen.setText("Ilustración no disponible");
		}
		panelImagen.add(lblImagen, BorderLayout.CENTER);
		add(panelImagen, BorderLayout.NORTH);

		// CONTENEDOR DE ATRIBUTOS 
		VisitorPoder visitor = new VisitorPoder();
		double poder = carta.aceptar(visitor);

		JPanel panelDatos = new JPanel(new GridLayout(5, 1, 5, 5));
		panelDatos.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(5, 15, 15, 15),
				BorderFactory.createTitledBorder(" Características de la Carta ")
		));

		
		panelDatos.add(new JLabel(" Nombre Oficial:  " + carta.getNombreCarta()));
		panelDatos.add(new JLabel(" Nivel de Rareza:  " + carta.getRareza()));
		panelDatos.add(new JLabel(" Categoría Base:   " + carta.getTipo()));
		panelDatos.add(new JLabel(" Especificación:   " + obtenerAtributosAdicionales(carta)));
		
		JLabel lblPoder = new JLabel(" Score de Poder:  " + poder);
		lblPoder.setFont(lblPoder.getFont().deriveFont(Font.PLAIN, 12f)); 
		lblPoder.setForeground(Color.BLACK); 
		panelDatos.add(lblPoder);

		add(panelDatos, BorderLayout.CENTER);
		setVisible(true);
	}

	private String obtenerAtributosAdicionales(Carta carta) {
		if (carta instanceof Pokemon) {
			Pokemon p = (Pokemon) carta;
			return "Ataque: " + p.getDaño() + " | Energías: " + p.getCantEnergias();
		} else if (carta instanceof Item) {
			Item i = (Item) carta;
			return "Efecto de Botín: +" + i.getBonificacion();
		} else if (carta instanceof Supporter) {
			Supporter s = (Supporter) carta;
			return "Usos Máximos: " + s.getEfectosPorTurno();
		} else if (carta instanceof Energy) {
			Energy en = (Energy) carta;
			return "Tipo Elemental: " + en.getElemento();
		}
		return "Ninguno";
	}
}