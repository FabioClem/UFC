package models.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sistema.DefeitoIntegracao;

@Stateless
public class DefeitoIntegracaoServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastrarDefeitoIntegracao(DefeitoIntegracao defeitoIntegracao) throws Exception {
		
		try {
			
			this.entityManager.persist(defeitoIntegracao);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao integrar defeito");

		}		
		
	}
	
	public void modificarDefeitoIntegracao(DefeitoIntegracao defeitoIntegracao) throws Exception {
		
		try {
			
			this.entityManager.merge(defeitoIntegracao);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao modificar Defeito Integracao");
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<DefeitoIntegracao> listarDefeitoIntegracao() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM DefeitoIntegracao d");
			return query.getResultList();					
			
		} catch (Exception e) {
			
			return new ArrayList<DefeitoIntegracao>();

		}
		
	}
	
	public DefeitoIntegracao listarDefeitoIntegracaoEspecifico(DefeitoIntegracao defeitoIntegracao) throws Exception {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM DefeitoIntegracao d WHERE d.ss =:param1");			
			query.setParameter("param1", defeitoIntegracao.getSs());
			return (DefeitoIntegracao) query.getSingleResult();			
			
		} catch (Exception e) {
			
			throw new Exception("Defeito não existe");

		}
		
	}

}
