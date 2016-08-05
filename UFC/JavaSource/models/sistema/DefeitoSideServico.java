package models.sistema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.UsuarioEfika;
import entidades.sistema.DefeitoSide;
import entidades.sistema.Lote;
import entidades.sistema.MotivoEncerramentoDefeitoSide;
import entidades.sistema.StatusDefeito;
import entidades.sistema.Tipificacao;

@Stateless
public class DefeitoSideServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;

	public void cadastrarDefeitoSide(DefeitoSide defeitoSide, UsuarioEfika usuarioEfika) throws Exception {

		try {

			defeitoSide.setUsuarioEfika(usuarioEfika);

			this.entityManager.persist(defeitoSide);

		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar Defeito Side");

		}

	}

	public void modificarDefeitoSide(DefeitoSide defeitoSide, StatusDefeito statusDefeito) throws Exception {

		try {
			
			Date date = new Date();
			
			defeitoSide.setDataAcao(date);
			
			defeitoSide.setStatusDefeito(statusDefeito);

			this.entityManager.merge(defeitoSide);

		} catch (Exception e) {

			throw new Exception("Erro ao modificar Defeito Side");

		}

	}

	@SuppressWarnings("unchecked")
	public List<DefeitoSide> listarDefeitoSide(StatusDefeito statusDefeito) {

		try {

			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.statusDefeito =:param1 ORDER BY d.dataIntegrado DESC");
			query.setParameter("param1", statusDefeito);
			return query.getResultList();			

		} catch (Exception e) {

			return new ArrayList<DefeitoSide>();

		}

	}

	public DefeitoSide listarDefeitoSideEspecifico(DefeitoSide defeitoSide, UsuarioEfika usuarioEfika, StatusDefeito statusDefeito) throws Exception {

		try {

			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.id =:param1 AND d.usuarioEfika =:param2 AND d.statusDefeito =:param3");
			query.setParameter("param1", defeitoSide.getId());
			query.setParameter("param2", usuarioEfika);
			query.setParameter("param3", statusDefeito);
			return (DefeitoSide) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Defeito Side não existe ou nao está associado a sua matricula.");

		}

	}

	public DefeitoSide listarDefeitoSideEspecifico(DefeitoSide defeitoSide) throws Exception {

		try {

			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.id =:param1");
			query.setParameter("param1", defeitoSide.getId());
			return (DefeitoSide) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Defeito Side não existe ou nao está associado a sua matricula.");

		}

	}

	
	public void assumirDefeitoSide(DefeitoSide defeitoSide, StatusDefeito statusDefeito, UsuarioEfika usuarioEfika) throws Exception {

		try {

			defeitoSide.setStatusDefeito(statusDefeito);
			defeitoSide.setUsuarioEfika(usuarioEfika);

			this.entityManager.merge(defeitoSide);

		} catch (Exception e) {

			throw new Exception("Erro ao assumir Defeito");

		}		

	}

	@SuppressWarnings("unchecked")
	public List<DefeitoSide> listarMeusDefeitos(UsuarioEfika usuarioEfika, StatusDefeito statusDefeito) {

		try {

			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.usuarioEfika =:param1 AND d.statusDefeito =:param2");
			query.setParameter("param1", usuarioEfika);
			query.setParameter("param2", statusDefeito);
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<DefeitoSide>();

		}

	}
	
	@SuppressWarnings("unchecked")
	public List<DefeitoSide> listarDefeitoSideEncerradoSemSS(StatusDefeito statusDefeito) {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.statusDefeito =:param1 AND d.ss = ''");
			query.setParameter("param1", statusDefeito);
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<DefeitoSide>();
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<DefeitoSide> listarDefeitoSideEncerrado(StatusDefeito statusDefeito, Date dataInicio, Date dataFim) {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.statusDefeito =:param1 AND d.dataIntegrado BETWEEN :param2 AND :param3");
			query.setParameter("param1", statusDefeito);
			query.setParameter("param2", dataInicio);
			query.setParameter("param3", dataFim);
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<DefeitoSide>();
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<DefeitoSide> listarDefeitoSideMotivoEncerramento(MotivoEncerramentoDefeitoSide motivoEncerramento) {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.motivoEncerramento =:param1 AND d.dataIntegrado > CURRENT_DATE");
			query.setParameter("param1", motivoEncerramento);
			return query.getResultList();			
			
		} catch (Exception e) {
			
			return new ArrayList<DefeitoSide>();			

		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<DefeitoSide> listarDefeitoSideStatus(StatusDefeito statusDefeito) {

		try {
			
			String queryC = "";
			
			if (statusDefeito.getNome().equalsIgnoreCase("Encerrado") || statusDefeito.getNome().equalsIgnoreCase("Removido Fila")) {				
				
				queryC = " AND d.dataAcao > CURRENT_DATE";
				
			}else{
				
				queryC = "AND d.dataIntegrado > CURRENT_DATE";
				
			}
			
			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.statusDefeito =:param1" + queryC);			
			query.setParameter("param1", statusDefeito);
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<DefeitoSide>();

		}

	}
	
	@SuppressWarnings("unchecked")
	public List<DefeitoSide> listarDefeitoSideTipificacao(Tipificacao tipificacao) {
		
		try {
						
			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.tipificacao =:param1 AND d.dataIntegrado > CURRENT_DATE");
			query.setParameter("param1", tipificacao);
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<DefeitoSide>();
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<DefeitoSide> listarDefeitoSideDeLoteEspecifico(Lote lote, StatusDefeito statusDefeito) {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.lote =:param1 AND d.statusDefeito =:param2");
			query.setParameter("param1", lote);
			query.setParameter("param2", statusDefeito);
			return query.getResultList();
			
		} catch (Exception e) {
			
			return new ArrayList<DefeitoSide>();
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listarDefeitoSideDistinc(StatusDefeito statusDefeito) {

		try {

			Query query = this.entityManager.createQuery("SELECT DISTINCT d.lote.nome FROM DefeitoSide d WHERE d.statusDefeito =:param1");
			query.setParameter("param1", statusDefeito);
			return query.getResultList();			

		} catch (Exception e) {

			return new ArrayList<String>();

		}

	}
	
}
