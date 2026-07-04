package gui;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {

	private PanelColeccion panelColeccion;

	public VentanaPrincipal() {
		setTitle("Pokemon ");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane pestanas = new JTabbedPane();

		panelColeccion = new PanelColeccion();

		pestanas.add("Administrador", new PanelAdmin());
		pestanas.add("Coleccion", panelColeccion);

		pestanas.addChangeListener(e -> {
			if (pestanas.getSelectedComponent() == panelColeccion) {
				panelColeccion.refrescar();
			}
		});

		add(pestanas);
	}
}