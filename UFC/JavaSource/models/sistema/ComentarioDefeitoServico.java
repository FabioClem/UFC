package models.sistema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.sistema.ComentarioDefeito;
import entidades.sistema.Defeito;
import entidades.sistema.DefeitoSide;

@Stateless
public class ComentarioDefeitoServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;

	public void cadastrarComentarioDefeito(ComentarioDefeito comentarioDefeito, Defeito defeito) throws Exception {

		try {

			comentarioDefeito.setDefeito(defeito);

			this.entityManager.persist(comentarioDefeito);

		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar comentario para o defeito.");

		}

	}

	public void cadastrarComentarioDefeito(ComentarioDefeito comentarioDefeito, DefeitoSide defeitoSide) throws Exception {

		try {

			Date date = new Date();
			
			comentarioDefeito.setDefeitoSide(defeitoSide);
			comentarioDefeito.setDataComentario(date);

			this.entityManager.persist(comentarioDefeito);

		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar comentario para o defeito.");

		}

	}

	public void modificarComentarioDefeito(ComentarioDefeito comentarioDefeito) throws Exception {

		try {

			this.entityManager.merge(comentarioDefeito);

		} catch (Exception e) {

			throw new Exception("Erro ao modificar comentario.");

		}

	}

	@SuppressWarnings("unchecked")
	public List<ComentarioDefeito> listarComentarioDefeito(Defeito defeito) {

		try {

			Query query = this.entityManager.createQuery("FROM ComentarioDefeito c WHERE c.defeito =:param1");
			query.setParameter("param1", defeito);
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<ComentarioDefeito>();

		}

	}

	@SuppressWarnings("unchecked")
	public List<ComentarioDefeito> listarComentarioDefeito(DefeitoSide defeitoSide) {

		try {

			Query query = this.entityManager.createQuery("FROM ComentarioDefeito c WHERE c.defeitoSide =:param1");
			query.setParameter("param1", defeitoSide);
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<ComentarioDefeito>();

		}

	}

}
