package ar.edu.unlam.razas;

public class Sel extends Raza {
	
	private int furia;
	
	public Sel(int pos) {
		this.saludMaxima = 513;
		this.saludActual = saludMaxima;
		this.danioBasico = 46;
		this.danioActual = this.danioBasico;
		this.rangoDeAtaque = 0.44;
		this.furia = 0;
		this.pos = pos;
	}

	@Override
	public void atacar(Raza r) {		
		if (puedeAtacar(r)) {
			this.danioActual += 5 * this.danioBasico * this.furia;
			r.serAtacado(this);
		}	
	}

	@Override
	public boolean puedeAtacar(Raza r) {
		return (this.pos - r.pos) < this.rangoDeAtaque?true:false;
	}

	@Override
	public void serAtacado(Raza r) {
		this.furia++;
		this.saludActual -= (r.danioActual*2);
		if (this.saludActual < 0 ) {
			this.saludActual = 0;
		}
	}

	@Override
	public void descansar() {
		this.saludActual = saludMaxima;
		this.furia = 0;
		this.danioActual = this.danioBasico;
	}

}
