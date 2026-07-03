package visitor;
import dominio.*;
public interface IVisitor {

	double visit(Pokemon p);

	double visit(Item i);

	double visit(Supporter s);

	double visit(Energy e);

}
