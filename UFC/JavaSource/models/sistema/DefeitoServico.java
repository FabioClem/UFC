package models.sistema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.UsuarioEfika;
import entidades.sistema.Defeito;
import entidades.sistema.DefeitoSide;
import entidades.sistema.MotivoEncerramento;
import entidades.sistema.StatusDefeito;
import entidades.sistema.TipoDefeito;

@Stateless
public class DefeitoServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastarDefeito(Defeito defeito) throws Exception {
		
		try {
			
			this.entityManager.persist(defeito);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao cadastrar Defeito [1]");
			
		}
		
	}
	
	public void cadastrarDefeito(DefeitoSide defeitoSide, TipoDefeito tipoDefeito) throws Exception {
		
		try {
			
			Defeito defeito = new Defeito();
			Date date = new Date();
			
			defeito.setSs(defeitoSide.getSs());
			defeito.setInstancia(defeitoSide.getInstancia());
			defeito.setDataAbertura(defeitoSide.getDataIntegrado());
			defeito.setDataVencimento(date);
			defeito.setDataIntegrado(defeitoSide.getDataIntegrado());
			defeito.setDataEncerramento(date);
			defeito.setTipificacao(defeitoSide.getTipificacao());
			defeito.setTipoDefeito(tipoDefeito);			
			defeito.setUsuarioEfika(defeitoSide.getUsuarioEfika());
			defeito.setStatusDefeito(defeito.getStatusDefeito());
			defeito.setMotivoEncerramento(defeitoSide.getMotivoEncerramento());
			
			this.entityManager.persist(defeito);			
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao cadastrar defeito [2]");

		}
		
	}
	
	public void modificarDefeito(Defeito defeito) throws Exception {
		
		try {
			
			this.entityManager.merge(defeito);			
			
		} catch (Exception e) {

			throw new Exception("Erro ao modificar Defeito");
			
		}
		
	}
	
	public void assumirDefeito(Defeito defeito, UsuarioEfika usuarioEfika, StatusDefeito statusDefeito) throws Exception {
		
		try {
			
			defeito.setUsuarioEfika(usuarioEfika);
			defeito.setStatusDefeito(statusDefeito);
			
			this.entityManager.merge(defeito);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao assumir Defeito");
			
		}
		
	}
	
	public void encerrarDefeito(Defeito defeito, StatusDefeito statusDefeito, MotivoEncerramento motivoEncerramento) throws Exception {
		
		try {
			
			Date date = new Date();
						
			defeito.setStatusDefeito(statusDefeito);
			defeito.setMotivoEncerramento(motivoEncerramento);
			defeito.setDataEncerramento(date);
			
			this.entityManager.merge(defeito);
			
		} catch (Exception e) {
			
			throw new Exception("Erro o encerrar Defeito");

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Defeito> listarDefeitos() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM Defeito d ORDER BY d.dataIntegrado DESC");
			return query.getResultList();
			
		} catch (Exception e) {

			return new ArrayList<Defeito>();
			
		}
		
	}
	
	public Defeito listarDefeitoEspecifico(Defeito defeito) throws Exception {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM Defeito d WHERE d.ss =:param1");
			query.setParameter("param1", defeito.getSs());
			return (Defeito) query.getSingleResult();
			
		} catch (Exception e) {
			
			throw new Exception("Defeito nao encontrado");

		}
		
	}
	
}
