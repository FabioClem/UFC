package models.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sistema.DefeitoSide;
import entidades.sistema.Tipificacao;

@Stateless
public class TipificacaoServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastrarTipificacao(Tipificacao tipificacao) throws Exception {
		
		try {
			
			this.entityManager.persist(tipificacao);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao cadastrar Tipificacão");
			
		}
		
	}
	
	public void modificarTipificacao(Tipificacao tipificacao) throws Exception {
		
		try {
			
			this.entityManager.merge(tipificacao);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao modificar Tipificacão");
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Tipificacao> listarTipificacao() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM Tipificacao t");
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<Tipificacao>();

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Tipificacao> listarTipificacaoEspecifico(List<DefeitoSide> listaDefeitosSide) {
		
		try {
			
			StringBuffer sequencia = new StringBuffer();
			
			Integer totalLista = listaDefeitosSide.size();
			
			Integer count = 1;
									
			for (DefeitoSide defeitoSide : listaDefeitosSide) {
				
				String or = "";
				
				if (count != totalLista) {
					
					or = " OR ";
					
				}
				
				sequencia.append("t.id = '" + defeitoSide.getTipificacao().getId() + "' " + or + " ");
				
			}
						
			Query query = this.entityManager.createQuery("FROM Tipificacao t WHERE " + sequencia);
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<Tipificacao>();
			
		}
		
	}

}
