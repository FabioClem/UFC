package models.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sistema.TipoDefeito;

@Stateless
public class TipoDefeitoServico {
	
	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastrarTipoDefeito(TipoDefeito tipoDefeito) throws Exception {
		
		try {
			
			this.entityManager.persist(tipoDefeito);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao cadastrar tipo");
			
		}
		
	}
	
	public void modificarTipoDefeito(TipoDefeito tipoDefeito) throws Exception {
		
		try {
			
			this.entityManager.merge(tipoDefeito);		
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao modificar tipo");

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoDefeito> listarTipoDefeito() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM TipoDefeito t");
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<TipoDefeito>();

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoDefeito> listarTipoDefeitoAtivo() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM TipoDefeito t WHERE t.ativo =:param1");
			query.setParameter("param1", true);
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<TipoDefeito>();

		}
		
	}
	
	public TipoDefeito listarTipoDefeitoEspecifico(String nome) throws Exception {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM TipoDefeito t WHERE t.nome =:param1");
			query.setParameter("param1", nome);
			return (TipoDefeito) query.getSingleResult();
			
		} catch (Exception e) {
			
			throw new Exception("Tipo defeito nao existe");

		}
		
	}

}
