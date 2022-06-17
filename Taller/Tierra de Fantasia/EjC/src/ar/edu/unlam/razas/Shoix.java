package ar.edu.unlam.razas;

public class Shoix extends Raza{
	
	public Shoix (int pos) {
		saludActual = 295;
		danioBasico = 43;
		danioActual = danioBasico;
		rangoDeAtaque=2;
		saludMaxima=295;
		this.pos = pos;	
	}
	
	@Override
	public void atacar(Raza r) {
		if (puedeAtacar(r)) {
			r.serAtacado(this);
		}
	}

	@Override
	public void serAtacado(Raza r) {
		this.danioActual -= 5;
		
		if (danioActual <= 0) {
			danioActual = 0;			
		}
		
		this.saludActual -= r.danioActual;
		if (this.saludActual < 0 ) {
			this.saludActual = 0;
		}
		
	}

	@Override
	public boolean puedeAtacar(Raza r) {
		return (this.pos - r.pos) < this.rangoDeAtaque?true:false;
	}
	@Override
	public void descansar() {
		this.saludActual += (this.saludMaxima - this.saludActual)*0.5; 
	}
}
