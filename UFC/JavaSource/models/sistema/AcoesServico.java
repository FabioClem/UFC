package models.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.UsuarioEfika;
import entidades.sistema.Acoes;
import entidades.sistema.Defeito;

@Stateless
public class AcoesServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastarAcao(Acoes acoes, Defeito defeito) throws Exception {
		
		try {
			
			acoes.setDefeito(defeito);
			acoes.setStatusDefeito(defeito.getStatusDefeito());
			acoes.setUsuarioEfika(defeito.getUsuarioEfika());
			
			this.entityManager.persist(acoes);
			
		} catch (Exception e) {
						
			throw new Exception("Erro ao cadastrar ação");

		}
		
	}
	
	public void modificarAcao(Acoes acoes) throws Exception {
		
		try {
			
			this.entityManager.merge(acoes);
			
		} catch (Exception e) {
			
			throw new Exception("Erro ao modificar Acão");
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Acoes> listarAcoesEspecifico(Defeito defeito, UsuarioEfika usuarioEfika) {
		
		try {	
			
			Query query = this.entityManager.createQuery("FROM Acoes a WHERE a.defeito =:param1 OR a.usuarioEfika =:param2 ORDER BY a.dataAcao DESC");
			query.setParameter("param1", defeito);
			query.setParameter("param2", usuarioEfika);
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<Acoes>();	

		}
		
	}
	
	
}
