package dominio;

import visitor.IVisitor;

public class Item extends Carta {

	private int bonificacion;
	
	public Item(String nombreCarta, int rareza, String tipo, int bonificacion) {
		super(nombreCarta, rareza, tipo);
		this.bonificacion = bonificacion;
	}

	public int getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}
	
	@Override
	public double aceptar(IVisitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String guardar() {
	    return NombreCarta+";"+Rareza+";Item;"+bonificacion;
	}

}
