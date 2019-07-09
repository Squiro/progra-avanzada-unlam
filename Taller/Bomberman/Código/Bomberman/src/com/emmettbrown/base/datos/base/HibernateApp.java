package com.emmettbrown.base.datos.base;


import java.util.logging.Level;
import java.util.logging.Logger;



//import javax.persistence.criteria.Root;


public class HibernateApp {
	
	//@SuppressWarnings("unchecked")
	 
	 
	public static void main(String[] args) {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);//solo para que no muestre mucho
		Usuario usuario=new Usuario();
		
		Configuracion configuracion=new Configuracion();
		GestionBD gestion=new GestionBD(configuracion.getFactory());
		try {
			Loguearse frame = new Loguearse(usuario,gestion);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
