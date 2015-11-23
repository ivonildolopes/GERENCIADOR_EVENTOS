package br.edu.fanor.progweb.arquitetura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.edu.fanor.progweb.arquitetura.entity.Clientes;

@Repository
public class ClienteDAO {

	//dizendo pro spring que controla o contexto do spring
	//ele vai ser responsavel pelo CRUD
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Clientes cliente){
		em.persist(cliente);
	}
	
	public void atualizar(Clientes cliente){
		em.merge(cliente);
	}
	
	public void deletar(Clientes cliente){
		em.remove(cliente);
	}
	
	
	public Clientes buscarPorId(int id){
		return em.find(Clientes.class, id);
	}
	
	
	public Clientes buscarPorCPF(String cpf){
		Query query = em.createQuery("select c from Clientes c where c.cpf = :cpf");
		query.setParameter("cpf",cpf);
		return (Clientes) query.getSingleResult();
	}
	
	public List<Clientes> buscarPorNome(String nome){
		Query query = em.createQuery("select c from Clientes c where c.nome = :cpf");
		query.setParameter("nome",nome);
		return query.getResultList();
	}
}
