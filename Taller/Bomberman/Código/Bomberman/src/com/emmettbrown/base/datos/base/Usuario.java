package com.emmettbrown.base.datos.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="usuario")
	private String usuario;
	@Column(name="contraseña")
	private String contraseña;
	@Column(name="puntaje")
	private int puntaje;
	
//	<!--
//    <property name="hbm2ddl.auto">create</property>
//-->
	public Usuario(){}
	public Usuario( String usuario, String contraseña,int puntaje) {
		
		this.usuario=usuario;
		this.contraseña=contraseña;
		this.puntaje= puntaje;
		
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
