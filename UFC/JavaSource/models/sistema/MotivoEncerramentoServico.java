package models.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sistema.MotivoEncerramento;

@Stateless
public class MotivoEncerramentoServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastrarMotivoEncerramento(MotivoEncerramento motivoEncerramento) throws Exception {
		
		try {
			
			if (motivoEncerramento.getNome().isEmpty()) {
				
				throw new Exception();
				
			}
			
			this.entityManager.persist(motivoEncerramento);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao cadastrar Motivo de Encerramento / Verifique se campo está vazio");

		}
		
	}
	
	public void modificarMotivoEncerramento(MotivoEncerramento motivoEncerramento) throws Exception {
		
		try {
			
			this.entityManager.merge(motivoEncerramento);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao modificar Motivo de Encerramento");

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MotivoEncerramento> listarMotivoEncerramento() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM MotivoEncerramento m");
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<MotivoEncerramento>();

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MotivoEncerramento> listarMotivoEncerramentoAtivo() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM MotivoEncerramento m WHERE m.ativo =:param1");
			query.setParameter("param1", true);
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<MotivoEncerramento>();
			
		}
		
	}

}
