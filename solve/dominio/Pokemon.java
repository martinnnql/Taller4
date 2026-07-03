package dominio;

import visitor.IVisitor;

public class Pokemon extends Carta {

	private int daño;
	private int CantEnergias;
	
	public Pokemon(String nombreCarta, int rareza, String tipo, int daño, int CantEnergias) {
		super(nombreCarta, rareza, tipo);
		this.daño = daño;
		this.CantEnergias = CantEnergias;
	}

	public int getDaño() {
		return daño;
	}

	public void setDaño(int daño) {
		this.daño = daño;
	}

	public int getCantEnergias() {
		return CantEnergias;
	}

	public void setCantEnergias(int cantEnergias) {
		CantEnergias = cantEnergias;
	}


	@Override
	public double aceptar(IVisitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String guardar() {
	    return NombreCarta+";"+Rareza+";Pokemon;"+daño+";"+CantEnergias;
	}
}
