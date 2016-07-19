package models;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.ControleUsuario;
import entidades.UsuarioEfika;

@Stateless
public class ControleUsuarioServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;

	public void cadastrarControleUsuario(ControleUsuario controleUsuario, List<String> listaDoTipoDefeito) throws Exception {

		try {

			if (controleUsuario.getUsuarioEfika().getLogin().isEmpty()) {

				throw new Exception("Preencha o login para realizar o cadastro.");

			}

			String tipo = "";

			for (String string : listaDoTipoDefeito) {

				if (!string.isEmpty()) {

					tipo = string + ";" + tipo;

				}				

			}			

			controleUsuario.setTipoDefeito(tipo);

			controleUsuario.setAtivo(false);

			this.entityManager.persist(controleUsuario);

		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar Controle para Usuario");

		}

	}

	public void modificarControleUsuario(ControleUsuario controleUsuario, List<String> listaDoTipoDefeito) throws Exception {

		try {

			String tipo = "";

			for (String string : listaDoTipoDefeito) {

				if (!string.isEmpty()) {

					tipo = string + ";" + tipo;

				}				

			}

			controleUsuario.setTipoDefeito(tipo);

			this.entityManager.merge(controleUsuario);

		} catch (Exception e) {

			throw new Exception("Erro ao modificar Controle para Usuario");

		}

	}

	@SuppressWarnings("unchecked")
	public List<ControleUsuario> listarControleUsuario() {

		try {

			Query query = this.entityManager.createQuery("FROM ControleUsuario c");
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<ControleUsuario>();

		}

	}

	public ControleUsuario listarControleUsuarioEspecifico(UsuarioEfika usuarioEfika) throws Exception {

		try {

			Query query = this.entityManager.createQuery("FROM ControleUsuario c WHERE c.usuarioEfika =:param1 AND c.ativo =:param2");
			query.setParameter("param1", usuarioEfika);
			query.setParameter("param2", true);
			return (ControleUsuario) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Controle nao encontrado");

		}

	}

	public Boolean validaControleUsuario(UsuarioEfika usuarioEfika, String tipo) {

		ControleUsuario controleUsuario = new ControleUsuario();

		String[] tipoDefeito;

		Boolean esTipo = false;

		try {

			controleUsuario = this.listarControleUsuarioEspecifico(usuarioEfika);

			tipoDefeito = controleUsuario.getTipoDefeito().split(";");

			for (String string : tipoDefeito) {

				if (string.equalsIgnoreCase(tipo)) {

					esTipo = true;

				}

			}

			return esTipo;

		} catch (Exception e) {

			return esTipo;

		}

	}

	@SuppressWarnings("unchecked")
	public List<UsuarioEfika> listarUsuarioEfika() {

		try {

			Query query = this.entityManager.createQuery("FROM UsuarioEfika u");
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<UsuarioEfika>();

		}		

	}
	
	public Boolean usuarioAtivo(UsuarioEfika usuarioEfika) throws Exception {
		
		Boolean isEmpty = false;
		
		try {
			
			Query query = this.entityManager.createQuery("FROM ControleUsuario c WHERE c.usuarioEfika =:param1 AND c.ativo =:param2");
			query.setParameter("param1", usuarioEfika);
			query.setParameter("param2", true);
			
			ControleUsuario controleUsuario = (ControleUsuario) query.getSingleResult();
			
			
			
			if (!controleUsuario.getUsuarioEfika().getLogin().isEmpty()) {
				
				isEmpty = true;
				
			}
			
			return isEmpty;
						
		} catch (Exception e) {
			
			return isEmpty;
			
		}
		
	}

}
