package ar.edu.unlam.complejos;

import java.util.Comparator;

public class ComplejosPorParteReal implements Comparator<Complejo>{

	@Override
	public int compare(Complejo o1, Complejo o2) {
		return o1.getParteReal().compareTo(o2.getParteReal());
	}

}
