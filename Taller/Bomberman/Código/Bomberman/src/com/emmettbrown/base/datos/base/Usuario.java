package com.emmettbrown.base.datos.base;

public class Usuario {
	private String usuario;
	private String contraseña;
	private int puntaje;
	
//	<!--
//    <property name="hbm2ddl.auto">create</property>
//-->

	public Usuario() {
		
	}
	
	public Usuario( String usuario, String contraseña) {
		
		this.usuario=usuario;
		this.contraseña=contraseña;
		this.puntaje=0;
		
	}
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}	
	
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contraseña=" + contraseña + ", puntaje=" + puntaje + "]";
	}

	
	
}
