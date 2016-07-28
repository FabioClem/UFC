package models.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sistema.MotivoEncerramentoDefeitoSide;

@Stateless
public class MotivoEncerramentoDefeitoSideServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastrarMotivoEncerramento(MotivoEncerramentoDefeitoSide motivoEncerramento) throws Exception {
		
		try {
			
			if (motivoEncerramento.getNome().isEmpty()) {
				
				throw new Exception();
				
			}
			
			this.entityManager.persist(motivoEncerramento);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao cadastrar Motivo de Encerramento / Verifique se campo está vazio");

		}
		
	}
	
	public void modificarMotivoEncerramento(MotivoEncerramentoDefeitoSide motivoEncerramento) throws Exception {
		
		try {
			
			this.entityManager.merge(motivoEncerramento);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao modificar Motivo de Encerramento");

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MotivoEncerramentoDefeitoSide> listarMotivoEncerramento() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM MotivoEncerramentoDefeitoSide m");
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<MotivoEncerramentoDefeitoSide>();

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MotivoEncerramentoDefeitoSide> listarMotivoEncerramentoAtivo() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM MotivoEncerramentoDefeitoSide m WHERE m.ativo =:param1");
			query.setParameter("param1", true);
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<MotivoEncerramentoDefeitoSide>();
			
		}
		
	}

}
