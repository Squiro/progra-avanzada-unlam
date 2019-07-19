package com.emmettbrown.base.datos.base;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class GestionBD {
	private SessionFactory factory;
	
	
	public GestionBD(SessionFactory factory) {
		this.factory=factory;
	}
	
	public SessionFactory getFactory() {
		return factory;
	}

	public static void main(String[] args) {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Configuracion conf = new Configuracion();
		GestionBD g = new GestionBD(conf.getFactory());
		Session session = g.getFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Object[]> listaUsuarios = null;
		Query q = session.createQuery("select pp.usuario,pp.contraseña,pp.puntaje from Usuario pp");
		listaUsuarios=q.getResultList();
		t.commit();
		session.close();
		for(int i=0; i< listaUsuarios.size() ; i++ ) {
			Object fila[] = listaUsuarios.get(i);
			System.out.println("Usuario: "+(String)fila[0]+ " Contrasenia: "+(String)fila[1] + " Puntaje: "+ (Integer)fila[2]);
		}
		
	}
}
