package br.com.lojinha.lojinhaDAO;

import br.com.lojinha.model.Produto;
import br.com.lojinha.util.HibernateUtil;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProdutoDAO {

	public ProdutoDAO() {
		HibernateUtil.getSessionFactory();
	}
	
	public ArrayList<Produto> listarProdutos(){
		ArrayList<Produto> produtos = new ArrayList<>();
		try {
			try (Session session = HibernateUtil.openSession();){
				Transaction transaction = session.beginTransaction();
			    //produtos = (ArrayList<Produto>) session.createQuery("FROM produto").list();
				TypedQuery<Produto> query = session.createQuery("FROM Produto");
				produtos = (ArrayList<Produto>) query.getResultList();
				transaction.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produtos;
	}

	public void adicionarNovoProduto(Produto p) {
		try {
			try (Session session = HibernateUtil.openSession();){
				Transaction transaction = session.beginTransaction();
				session.save(p);
				transaction.commit();
			}
			System.out.println("Produto nome: " + p.getNome() + " Salvo com sucesso!" );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Produto buscarProduto(Produto p){
		Produto produto = new Produto();
		try {
			try (Session session = HibernateUtil.openSession();){
				Transaction transaction = session.beginTransaction();
				TypedQuery<Produto> query = session.createQuery("FROM Produto WHERE id = :id").setParameter("id", p.getId());
				produto = query.getSingleResult();
				//produto = (Produto) session.createQuery("FROM Produto p WHERE p.id = :id").setParameter("id", p.getId()).uniqueResult();
				transaction.commit();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produto;
	}

	public boolean removerProduto(Produto p) {
		boolean flagProdutoExcluido = false;
		Transaction transaction = null;
		try {
			try (Session session = HibernateUtil.openSession();){
				transaction = session.beginTransaction();
				session.delete(p);
				transaction.commit();
				flagProdutoExcluido = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return flagProdutoExcluido;
	}

	public boolean updateProduto(Produto p) {
		boolean flagProdutoAlterado = false;
		Transaction transaction = null;
		try {
			try (Session session = HibernateUtil.openSession();){
				transaction = session.beginTransaction();
				session.update(p);
				transaction.commit();
				flagProdutoAlterado = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return flagProdutoAlterado;
	}

}
