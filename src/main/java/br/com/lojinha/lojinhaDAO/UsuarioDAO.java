package br.com.lojinha.lojinhaDAO;

import br.com.lojinha.model.Usuario;
import br.com.lojinha.util.HibernateUtil;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDAO {

	public UsuarioDAO() {
		HibernateUtil.getSessionFactory();
	}
	
	public ArrayList<Usuario> listarUsuarios(){
		ArrayList<Usuario> usuarios = new ArrayList<>();
		try {
			try (Session session = HibernateUtil.openSession();){
				Transaction transaction = session.beginTransaction();
				TypedQuery<Usuario> query = session.createQuery("FROM Usuario");
				usuarios = (ArrayList<Usuario>) query.getResultList();
				transaction.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public void adicionarNovoUsuario(Usuario u) {
		try {
			try (Session session = HibernateUtil.openSession();){
				Transaction transaction = session.beginTransaction();
				session.save(u);
				transaction.commit();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Usuario buscarUsuario(Usuario u){
		Usuario usuario = new Usuario();
		try {
			try (Session session = HibernateUtil.openSession();){
				Transaction transaction = session.beginTransaction();
				TypedQuery<Usuario> query = session.createQuery("FROM Usuario WHERE id = :id").setParameter("id", u.getId());
				usuario = query.getSingleResult();
				transaction.commit();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public boolean removerUsuario(Usuario u) {
		boolean flagUsuarioExcluido = false;
		Transaction transaction = null;
		try {
			try (Session session = HibernateUtil.openSession();){
				transaction = session.beginTransaction();
				session.delete(u);
				transaction.commit();
				flagUsuarioExcluido = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return flagUsuarioExcluido;
	}

	public boolean updateUsuario(Usuario u) {
		boolean flagUsuarioAlterado = false;
		Transaction transaction = null;
		try {
			try (Session session = HibernateUtil.openSession();){
				transaction = session.beginTransaction();
				session.update(u);
				transaction.commit();
				flagUsuarioAlterado = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return flagUsuarioAlterado;
	}

}
