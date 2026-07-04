package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import logica.*;
import dominio.*;
import strategy.*;

public class PanelColeccion extends JPanel {

	private JComboBox<String> selectorCriterio;
	private JPanel panelGaleria; 
	private JScrollPane scrollGaleria;
	private SistemaImpl sistema;

	public PanelColeccion() {
		sistema = (SistemaImpl) SistemaImpl.getInstance();
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		
		selectorCriterio = new JComboBox<>();
		selectorCriterio.addItem("Ordenar por: ALFABÉTICO");
		selectorCriterio.addItem("Ordenar por: RAREZA");
		selectorCriterio.addItem("Ordenar por: RATING PODER");
		add(selectorCriterio, BorderLayout.NORTH);
		
		
		selectorCriterio.addActionListener(e -> repoblarGaleria());

		// Panel con las fotos 
		panelGaleria = new JPanel(new GridLayout(0, 4, 15, 15));
		panelGaleria.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// el scroll
		scrollGaleria = new JScrollPane(panelGaleria);
		scrollGaleria.getVerticalScrollBar().setUnitIncrement(16); // Scroll fluido
		add(scrollGaleria, BorderLayout.CENTER);

		repoblarGaleria();
	}

	public void refrescar() {
		repoblarGaleria();
	}

	private void repoblarGaleria() {
		
		panelGaleria.removeAll();

		ArrayList<Carta> copiaLista = new ArrayList<>(sistema.getCartas());
		int indiceSeleccionado = selectorCriterio.getSelectedIndex();
		IStrategy estrategiaActual;

		if (indiceSeleccionado == 1) {
			estrategiaActual = new StrategyOrdenarRareza();
		} else if (indiceSeleccionado == 2) {
			estrategiaActual = new StrategyOrdenarPoder();
		} else {
			estrategiaActual = new StrategyOrdenarNombre();
		}

		sistema.setStrategy(estrategiaActual);
		estrategiaActual.ordenar(copiaLista);

		for (Carta c : copiaLista) {
			JPanel tarjetaVisual = new JPanel(new BorderLayout());
			tarjetaVisual.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY, 1),
					c.getNombreCarta(),
					TitledBorder.CENTER,
					TitledBorder.BOTTOM
			));
			tarjetaVisual.setPreferredSize(new Dimension(140, 200));

			ImageIcon foto = Imagen.cargarImagen(c.getNombreCarta(), 130, 180);
			JLabel lblFoto = new JLabel();
			lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
			
			if (foto != null) {
				lblFoto.setIcon(foto);
			} else {
				lblFoto.setText("<html><center>[Sin Foto]<br>Falta default.png</center></html>");
			}

			lblFoto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			lblFoto.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					new DetalleCartas(c);
				}
			});

			tarjetaVisual.add(lblFoto, BorderLayout.CENTER);
			panelGaleria.add(tarjetaVisual);
		}

		panelGaleria.revalidate();
		panelGaleria.repaint();
	}
}