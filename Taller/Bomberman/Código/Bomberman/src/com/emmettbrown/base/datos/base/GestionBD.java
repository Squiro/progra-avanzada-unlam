package com.emmettbrown.base.datos.base;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GestionBD {
	private final boolean DUPLICADO=false;
	private final boolean EXITO=true;
	private SessionFactory factory;
	
	
	public GestionBD(SessionFactory factory) {
		this.factory=factory;
	}
	
	public SessionFactory getFactory() {
		return factory;
	}

	public List<Object[]> obtenerRegistro(Usuario usuario,String consultaString) {
		Session session = factory.openSession();
		Query q = session.createQuery(consultaString);
		@SuppressWarnings("unchecked")
		List<Object[]> listaUsuarios = q.getResultList();
		
		for(int i=0; i< listaUsuarios.size() ; i++ ) {
			System.out.println(listaUsuarios.get(i));
		}
		session.close();	
		return listaUsuarios;
	}

	@SuppressWarnings("unchecked")
	public boolean insertarRegistro(Usuario usuario) {
		Session session = factory.openSession();
		List<Object[]> listaUsuarios = null;
        String consulta = "select pp.usuario from Usuario pp ";
		consulta += "where pp.usuario=" + "'"+usuario.getUsuario()+"'";
		Query q = session.createQuery(consulta);
		listaUsuarios=q.getResultList();
		System.out.println(listaUsuarios.size());
		
		if (listaUsuarios.size()==1 ) {
			return DUPLICADO; 	
		}
		else {
			Transaction tx = session.beginTransaction();			
			try {
				session.save(usuario);
				tx.commit();
			}
			catch (Exception e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
				System.out.println("Error");
				return false;
			}
			session.close();
			return EXITO;
		}
	}
	
	public void actualizarRegistro(Usuario usuario) {
		Session session = factory.openSession();
		List<Object[]> listaUsuarios = null;
        String consulta = "select pp.usuario from Usuario pp ";
		consulta += "where pp.usuario=" + "'"+usuario.getUsuario()+"'";
		Query q = session.createQuery(consulta);
		listaUsuarios=q.getResultList();
		if(listaUsuarios!=null && listaUsuarios.size()==1) {
			Transaction tx = session.beginTransaction();
			
			try {
				session.update(usuario);
				tx.commit();
			}
			catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			}
			session.close();
	 }
	}
	
	
}
