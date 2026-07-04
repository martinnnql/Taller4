package gui;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import logica.*;
import dominio.*;

public class PanelAdmin extends JPanel {

	private JTextField txtNombre, txtRareza, txtAtributo1, txtAtributo2;
	private JComboBox<String> comboTipo;
	private JButton btnAgregar, btnEliminar, btnModificar;
	private JLabel lblAtributo1, lblAtributo2, lblImagenPreview;
	private SistemaImpl sistema;

	public PanelAdmin() {
		sistema = (SistemaImpl) SistemaImpl.getInstance();
		setLayout(new BorderLayout(20, 20));
		setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		
		JPanel panelIzquierdo = new JPanel(new GridLayout(5, 2, 10, 15));
		
		panelIzquierdo.add(new JLabel("Nombre de la Carta:"));
		txtNombre = new JTextField();
		panelIzquierdo.add(txtNombre);

		panelIzquierdo.add(new JLabel("Nivel de Rareza:"));
		txtRareza = new JTextField();
		panelIzquierdo.add(txtRareza);

		panelIzquierdo.add(new JLabel("Categoría / Tipo:"));
		comboTipo = new JComboBox<>(new String[]{"Pokemon", "Item", "Supporter", "Energy"});
		panelIzquierdo.add(comboTipo);

		lblAtributo1 = new JLabel("Daño:");
		panelIzquierdo.add(lblAtributo1);
		txtAtributo1 = new JTextField();
		panelIzquierdo.add(txtAtributo1);

		lblAtributo2 = new JLabel("Energías Requeridas:");
		panelIzquierdo.add(lblAtributo2);
		txtAtributo2 = new JTextField();
		panelIzquierdo.add(txtAtributo2);

		
		JPanel panelDerecho = new JPanel(new BorderLayout());
		panelDerecho.setBorder(BorderFactory.createTitledBorder(" Ficha de Inspección / Imagen "));
		
		lblImagenPreview = new JLabel("Sin miniatura", SwingConstants.CENTER);
		panelDerecho.add(lblImagenPreview, BorderLayout.CENTER);

		
		JPanel panelCentral = new JPanel(new GridLayout(1, 2, 20, 0));
		panelCentral.add(panelIzquierdo);
		panelCentral.add(panelDerecho);
		add(panelCentral, BorderLayout.CENTER);

		
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
		btnAgregar = new JButton("Registrar Carta");
		btnModificar = new JButton("Modificar Existente");
		btnEliminar = new JButton("Remover Colección");

		panelBotones.add(btnModificar);
		panelBotones.add(btnEliminar);
		panelBotones.add(btnAgregar); 
		add(panelBotones, BorderLayout.SOUTH);

		
		comboTipo.addItemListener(e -> adaptarCamposPorTipo());
		
		txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				cargarMiniatura();
			}
		});

		btnAgregar.addActionListener(e -> registrarNuevaCarta());
		btnModificar.addActionListener(e -> actualizarDatosCarta());
		btnEliminar.addActionListener(e -> removerCarta());

		adaptarCamposPorTipo();
	}

	private void adaptarCamposPorTipo() {
		String seleccion = (String) comboTipo.getSelectedItem();
		if (seleccion == null) return;

		switch (seleccion) {
			case "Pokemon":
				lblAtributo1.setText("Daño:");
				lblAtributo2.setText("Energías Requeridas:");
				txtAtributo2.setEnabled(true);
				break;
			case "Item":
				lblAtributo1.setText("Bonificación:");
				lblAtributo2.setText("[No Aplica]");
				txtAtributo2.setText("");
				txtAtributo2.setEnabled(false);
				break;
			case "Supporter":
				lblAtributo1.setText("Efectos por Turno:");
				lblAtributo2.setText("[No Aplica]");
				txtAtributo2.setText("");
				txtAtributo2.setEnabled(false);
				break;
			case "Energy":
				lblAtributo1.setText("Elemento:");
				lblAtributo2.setText("[No Aplica]");
				txtAtributo2.setText("");
				txtAtributo2.setEnabled(false);
				break;
		}
	}

	private void cargarMiniatura() {
		String nombre = txtNombre.getText().trim();
		if (nombre.isEmpty()) {
			lblImagenPreview.setIcon(null);
			lblImagenPreview.setText("Sin miniatura");
			return;
		}
		ImageIcon img = Imagen.cargarImagen(nombre, 140, 140);
		if (img != null) {
			lblImagenPreview.setIcon(img);
			lblImagenPreview.setText("");
		} else {
			lblImagenPreview.setIcon(null);
			lblImagenPreview.setText("Sin miniatura");
		}
	}

	private void registrarNuevaCarta() {
		try {
			String nom = txtNombre.getText().trim();
			if (nom.isEmpty()) {
				JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
				return;
			}
			int rar = Integer.parseInt(txtRareza.getText().trim());
			String tipo = (String) comboTipo.getSelectedItem();

			Carta nueva = null;
			switch (tipo) {
				case "Pokemon":
					int d = Integer.parseInt(txtAtributo1.getText().trim());
					int e = Integer.parseInt(txtAtributo2.getText().trim());
					nueva = new Pokemon(nom, rar, tipo, d, e);
					break;
				case "Item":
					int b = Integer.parseInt(txtAtributo1.getText().trim());
					nueva = new Item(nom, rar, tipo, b);
					break;
				case "Supporter":
					int ef = Integer.parseInt(txtAtributo1.getText().trim());
					nueva = new Supporter(nom, rar, tipo, ef);
					break;
				case "Energy":
					String el = txtAtributo1.getText().trim();
					nueva = new Energy(nom, rar, tipo, el);
					break;
			}

			if (nueva != null) {
				sistema.agregarCartaDirecto(nueva); 
				JOptionPane.showMessageDialog(this, "Carta registrada exitosamente.");
				resetearFormulario();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Por favor, revise que los valores numéricos sean correctos.");
		}
	}

	private void actualizarDatosCarta() {
		String nom = txtNombre.getText().trim();
		if (nom.isEmpty()) return;

		Carta buscada = null;
		for (Carta c : sistema.getCartas()) {
			if (c.getNombreCarta().equalsIgnoreCase(nom)) {
				buscada = c;
				break;
			}
		}

		if (buscada == null) {
			JOptionPane.showMessageDialog(this, "No se encontró el elemento a actualizar.");
			return;
		}

		try {
			if (buscada instanceof Pokemon) {
				((Pokemon) buscada).setDaño(Integer.parseInt(txtAtributo1.getText().trim()));
				((Pokemon) buscada).setCantEnergias(Integer.parseInt(txtAtributo2.getText().trim()));
			} else if (buscada instanceof Item) {
				((Item) buscada).setBonificacion(Integer.parseInt(txtAtributo1.getText().trim()));
			} else if (buscada instanceof Supporter) {
				((Supporter) buscada).setEfectosPorTurno(Integer.parseInt(txtAtributo1.getText().trim()));
			} else if (buscada instanceof Energy) {
				((Energy) buscada).setElemento(txtAtributo1.getText().trim());
			}
			sistema.guardarEnArchivo();
			JOptionPane.showMessageDialog(this, "Carta actualizada correctamente.");
			resetearFormulario();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error en el formato de los atributos.");
		}
	}

	private void removerCarta() {
		String nom = txtNombre.getText().trim();
		Carta buscada = null;
		for (Carta c : sistema.getCartas()) {
			if (c.getNombreCarta().equalsIgnoreCase(nom)) {
				buscada = c;
				break;
			}
		}

		if (buscada != null) {
			sistema.eliminarCartaDirecto(buscada); 
			JOptionPane.showMessageDialog(this, "La carta fue removida del registro.");
			resetearFormulario();
		} else {
			JOptionPane.showMessageDialog(this, "La carta especificada no existe.");
		}
	}

	private void resetearFormulario() {
		txtNombre.setText("");
		txtRareza.setText("");
		txtAtributo1.setText("");
		txtAtributo2.setText("");
		lblImagenPreview.setIcon(null);
		lblImagenPreview.setText("Sin miniatura");
	}
}