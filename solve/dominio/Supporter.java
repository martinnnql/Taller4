package dominio;

import visitor.IVisitor;

public class Supporter extends Carta {

	private int efectosPorTurno;
	
	public Supporter(String nombreCarta, int rareza, String tipo, int efectosPorTurno) {
		super(nombreCarta, rareza, tipo);
		this.efectosPorTurno = efectosPorTurno;
	}

	public int getEfectosPorTurno() {
		return efectosPorTurno;
	}

	public void setEfectosPorTurno(int efectosPorTurno) {
		this.efectosPorTurno = efectosPorTurno;
	}

	@Override
	public double aceptar(IVisitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String guardar() {
	    return NombreCarta+";"+Rareza+";Supporter;"+efectosPorTurno;
	}
	
}
