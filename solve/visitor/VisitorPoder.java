package visitor;

import dominio.Energy;
import dominio.Item;
import dominio.Pokemon;
import dominio.Supporter;

public class VisitorPoder implements IVisitor {

	@Override
	public double visit(Pokemon p) {
		 return (p.getDaño()/(double)p.getCantEnergias())*100;
	}

	@Override
	public double visit(Item i) {
		return i.getBonificacion()*20;
	}

	@Override
	public double visit(Supporter s) {
		 return s.getEfectosPorTurno()*50;
	}

	@Override
	public double visit(Energy e) {
		 return 1;
	}

}
