package models.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sistema.StatusFulltest;

@Stateless
public class StatusFulltestServico {	

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;

	public void cadastrarStatusFulltest(StatusFulltest statusFulltest) throws Exception {

		try {
			
			this.entityManager.persist(statusFulltest);

		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar Status");

		}

	}

	public void modificarStatusFulltest(StatusFulltest statusFulltest) throws Exception {

		try {

			this.entityManager.merge(statusFulltest);

		} catch (Exception e) {

			throw new Exception("Erro ao modificar Status");

		}

	}
	
	@SuppressWarnings("unchecked")
	public List<StatusFulltest> listarStatusFulltest() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM StatusFulltest s");
			return query.getResultList();			
			
		} catch (Exception e) {
			
			return new ArrayList<StatusFulltest>();

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<StatusFulltest> listarStatusFulltestAtivo() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM StatusFulltest s WHERE s.ativo =:param1");
			query.setParameter("param1", true);
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<StatusFulltest>();

		}
		
	}

}
