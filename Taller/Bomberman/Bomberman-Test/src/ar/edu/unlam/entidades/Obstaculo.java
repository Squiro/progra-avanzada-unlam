package ar.edu.unlam.entidades;

public class Obstaculo extends Entidad {

	public Obstaculo(int x, int y) {
		this.simbolo = 'O';
		this.x = x;
		this.y = y;
		this.esVisible = true;		
	}
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
