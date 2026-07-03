package dominio;

import visitor.IVisitor;

public class Energy extends Carta {

	private String elemento;
	
	public Energy(String nombreCarta, int rareza, String tipo, String elemento) {
		super(nombreCarta, rareza, tipo);
		this.elemento = elemento;
	}

	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	@Override
	public double aceptar(IVisitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String guardar() {
	    return NombreCarta+";"+Rareza+";Energy;"+elemento;
	}

	
}
