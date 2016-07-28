package models.sistema;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import entidades.sistema.Lote;

@Stateless
public class LoteServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;	

	public void cadastrarLote(Lote lote) throws Exception {

		try {

			this.entityManager.persist(lote);

		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar lote na base");

		}

	}	

	@SuppressWarnings("unchecked")
	public List<Lote> listarLote() {

		try {

			Query query = this.entityManager.createQuery("FROM Lote l ORDER BY l.dataIntegracao DESC");
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<Lote>();

		}

	}

	public Lote listarLoteEspecifico(Lote lote) throws Exception {

		try {

			Query query = this.entityManager.createQuery("FROM Lote l WHERE l.id =:param1");
			query.setParameter("param1", lote.getId());
			return (Lote) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Lote não existe");

		}

	}
	
	public Lote listarLoteEspecificoNome(Lote lote) throws Exception {

		try {
			
			Query query = this.entityManager.createQuery("FROM Lote l WHERE l.nome =:param1");
			query.setParameter("param1", lote.getNome());
			return (Lote) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Lote não existe");

		}

	}
	
}
