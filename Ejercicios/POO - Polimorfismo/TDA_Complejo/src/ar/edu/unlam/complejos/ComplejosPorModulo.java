package ar.edu.unlam.complejos;

import java.util.Comparator;

public class ComplejosPorModulo implements Comparator<Complejo> {

	@Override
	public int compare(Complejo o1, Complejo o2) {
		return o1.modulo().compareTo(o2.modulo());
	}

}
