package models.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sistema.StatusDefeito;

@Stateless
public class StatusDefeitoServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastrarStatusDefeito(StatusDefeito statusDefeito) throws Exception {
		
		try {
			
			this.entityManager.persist(statusDefeito);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao cadastrar Status Defeito");

		}
		
	}
	
	public void modificarStatusDefeito(StatusDefeito statusDefeito) throws Exception {
		
		try {
			
			this.entityManager.merge(statusDefeito);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao modificar Status Defeito");

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<StatusDefeito> listarStatusDefeito() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM StatusDefeito s");
			return query.getResultList();
			
		} catch (Exception e) {
						
			return new ArrayList<StatusDefeito>();

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<StatusDefeito> listarStatusDefeitoAtivo() {
		
		
		try {
			
			Query query = this.entityManager.createQuery("FROM StatusDefeito s WHERE s.ativo =:param1");
			query.setParameter("param1", true);
			return query.getResultList();			
			
		} catch (Exception e) {
			
			return new ArrayList<StatusDefeito>();

		}
		
	}
	
	public StatusDefeito listarStatusDefeitoEspecifico(String nome) throws Exception {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM StatusDefeito s WHERE s.nome =:param1");
			query.setParameter("param1", nome);
			return (StatusDefeito) query.getSingleResult();
			
		} catch (Exception e) {

			throw new Exception("Status para o Defeito não encontrado");
			
		}
		
	}

}
