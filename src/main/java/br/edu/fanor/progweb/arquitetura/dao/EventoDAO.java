package br.edu.fanor.progweb.arquitetura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fanor.progweb.arquitetura.entity.Eventos;
import br.edu.fanor.progweb.arquitetura.exceptions.DAOException;

@Repository // especifico para DAO
@Transactional(propagation=Propagation.REQUIRED)
public class EventoDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Eventos evento) {
		entityManager.persist(evento);
	}
	
	public void atualizar(Eventos evento){
		entityManager.merge(evento);
	}
	
	public void excluir(Eventos evento) {
		entityManager.remove(evento);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Eventos> listarPorNome(String nome, String diaSemana, String turno) {		
		String hql = "select e from Eventos e"
				+ " where e.nome like :nome"
				+ " and e.diaSemana like :diaSemana"
				+ " and e.turno like :turno"
				+ " and dataFinal >= CURRENT_TIMESTAMP order by nome";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nome","%"+nome+"%");
		query.setParameter("diaSemana","%"+diaSemana+"%");
		query.setParameter("turno","%"+turno+"%");
		return query.getResultList();
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<Eventos> listarPorNome(String nome, String diaSemana) {		
		String hql = "select e from Eventos e"
				+ " where e.nome like :nome and"
				+ " e.diaSemana like :diaSemana";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nome","%"+nome+"%");
		query.setParameter("diaSemana","%"+diaSemana+"%");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Eventos> listarPorNome(String nome) {
		String hql = "select e from Eventos e where e.nome like :nome";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nome","%"+nome+"%");
		
		return query.getResultList();
	}
	
	public Eventos buscaPorId(Integer id) throws DAOException {
		String jpql = "select e from Eventos e where e.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		
		try {
			return (Eventos) query.getSingleResult();
		} catch(NoResultException e){
			return null;
		} 
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Eventos> listarTodos(){
		
		return entityManager.createQuery("from Eventos order by nome").getResultList();
	}
	
	public Eventos buscaRepetido(String turno,String sala,String diaSemana){
		
		String jpql= "select e from Eventos e"
				+ " where e.turno = :turno and"
				+ " e.espaco = :sala and"
				+ " e.diaSemana = :diaSemana";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter("turno", turno);
		query.setParameter("sala", sala);
		query.setParameter("diaSemana", diaSemana);
		
		try{
			return (Eventos) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
}
