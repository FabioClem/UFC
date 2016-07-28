package models.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sistema.MotivoEncerramentoDefeito;

@Stateless
public class MotivoEncerramentoDefeitoServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastrarMotivoEncerramentoDefeito(MotivoEncerramentoDefeito motivoEncerramentoDefeito) throws Exception {
		
		try {
			
			this.entityManager.persist(motivoEncerramentoDefeito);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao cadastrar Motivo de Encerramento");
			
		}	
		
	}
	
	public void modificarMotivoEncerramentoDefeito(MotivoEncerramentoDefeito motivoEncerramentoDefeito) throws Exception {
		
		try {
			
			this.entityManager.merge(motivoEncerramentoDefeito);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao modificar Motivo de Encerramento");
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MotivoEncerramentoDefeito> listarMotivoEncerramentoDefeito() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM MotivoEncerramentoDefeito m");
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<MotivoEncerramentoDefeito>();
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MotivoEncerramentoDefeito> listaMotivoEncerramentoDefeitoAtivo() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM MotivoEncerramentoDefeito m WHERE m.ativo =:param1");
			query.setParameter("param1", true);
			return query.getResultList();
			
		} catch (Exception e) {

			return new ArrayList<MotivoEncerramentoDefeito>();
			
		}
		
	}
	
}
