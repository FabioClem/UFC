package models.sistema;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.model.UploadedFile;

import com.opencsv.CSVReader;

import entidades.UsuarioEfika;
import entidades.sistema.Defeito;
import entidades.sistema.DefeitoIntegracao;
import entidades.sistema.DefeitoSide;
import entidades.sistema.Lote;
import entidades.sistema.StatusDefeito;
import entidades.sistema.Tipificacao;
import entidades.sistema.TipoDefeito;

@Stateless
public class LoteServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;

	public void cadastrarLote(UploadedFile file, UsuarioEfika usuarioEfika, StatusDefeito statusDefeito) throws Exception {

		try {

			byte[] conteudo = file.getContents();

			Date date = new Date();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");

			String nome = usuarioEfika.getLogin() + "-" + dateFormat.format(date);

			String fullname = "C:\\UploadedFiles\\" + nome + ".csv";

			FileOutputStream fos = new FileOutputStream(fullname);

			fos.write(conteudo);
			fos.close();

			Lote lote = new Lote();

			lote.setNome(nome);
			lote.setDataIntegracao(date);

			this.cadastrarLote(lote);

			//Chamar cadastrarCSV
			this.cadastrarCSV(nome, statusDefeito, lote);

		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar Lote");

		}

	}

	public void cadastrarLote(Lote lote) throws Exception {

		try {

			this.entityManager.persist(lote);

		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar lote na base");

		}

	}

	@SuppressWarnings("rawtypes")
	public void cadastrarCSV(String nomeArquivo, StatusDefeito statusDefeito, Lote lote) throws Exception {

		String[] row = null;
		String csvFilename = "C:\\UploadedFiles\\" + nomeArquivo + ".csv";

		CSVReader csvReader = new CSVReader(new FileReader(csvFilename), ';');
		List content = csvReader.readAll();

		for (Object object : content) {

			row = (String[]) object;

			/**
			 * PADROES
			 * SS/INSTANCIA/TIPIFICACAO/DATAABERTURA/DATAVENCIMENTO/FULLTEST/TIPODEFEITO 
			 **/

			if (!row[1].isEmpty() && !row[2].isEmpty() && row[6].equalsIgnoreCase("PROATIVO")) {

				/**
				 *  INSTANCIA/TIPIFICACAO/TIPODEFEITO
				 **/

				DefeitoSide defeitoSide = new DefeitoSide();
				Date date = new Date();

				defeitoSide.setInstancia(row[1]);
				defeitoSide.setTipificacao(this.acaoTipificacao(row[2].trim()));
				defeitoSide.setDataIntegrado(date);				
				defeitoSide.setStatusDefeito(statusDefeito);
				defeitoSide.setLote(lote);
				defeitoSide.setSs("");

				this.entityManager.persist(defeitoSide);

			}			

			if (!row[6].isEmpty() && row[0].contains("8-")) {

				DefeitoIntegracao defeitoIntegracao = new DefeitoIntegracao();

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

				defeitoIntegracao.setSs(row[0]);
				defeitoIntegracao.setTipoDefeito(this.acaoTipoDefeito(row[6]));
				defeitoIntegracao.setTipificacao(this.acaoTipificacao(row[2].trim()));
				defeitoIntegracao.setLote(lote);
				defeitoIntegracao.setInstancia(row[1]);

				Date dataAbertura;
				Date dataVencimento;

				if (row[3].isEmpty()) {					

					dataAbertura = new Date();
					dataVencimento = new Date();

				}else{

					dataAbertura = formatter.parse(row[3]);			
					dataVencimento = formatter.parse(row[4]);

				}				

				defeitoIntegracao.setDataAbertura(dataAbertura);
				defeitoIntegracao.setDataVencimento(dataVencimento);				

				Boolean acaoFulltest = false;

				if (row[5].equalsIgnoreCase("SIM")) {

					acaoFulltest = true;

				}

				defeitoIntegracao.setFulltest(acaoFulltest);

				this.entityManager.persist(defeitoIntegracao);				

			}

		}

		csvReader.close();

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

	public Tipificacao acaoTipificacao(String nomeTipificacao) {

		Query query = this.entityManager.createQuery("FROM Tipificacao t WHERE t.nome =:param1");
		query.setParameter("param1", nomeTipificacao);

		Tipificacao tipificacao = new Tipificacao();

		try {

			tipificacao = (Tipificacao) query.getSingleResult();

		} catch (Exception e) {

			tipificacao.setNome(nomeTipificacao);
			this.entityManager.persist(tipificacao);			

		}

		return tipificacao;

	}

	public TipoDefeito acaoTipoDefeito(String nomeTipoDefeito) throws Exception {

		try {

			Query query = this.entityManager.createQuery("FROM TipoDefeito t WHERE t.nome =:param1");
			query.setParameter("param1", nomeTipoDefeito);
			return (TipoDefeito) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Tipo de defeito nao encontrado.");

		}



	}

	public void loteDefeitoSide(UploadedFile file, UsuarioEfika usuarioEfika) throws Exception {

		try {
			
			byte[] conteudo = file.getContents();

			Date date = new Date();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");

			String nome = usuarioEfika.getLogin() + "-" + dateFormat.format(date);

			String fullname = "C:\\UploadedFiles\\" + nome + ".csv";

			FileOutputStream fos = new FileOutputStream(fullname);

			fos.write(conteudo);
			fos.close();

			Lote lote = new Lote();

			lote.setNome(nome);
			lote.setDataIntegracao(date);
					
			this.cadastrarLote(lote);

			//chnar para cadastrarCSV
			
			this.importDefeitoSide(nome);

		} catch (Exception e) {

			throw new Exception("Erro ao realizar upload do arquivo");

		}		

	}

	@SuppressWarnings("rawtypes")
	public void importDefeitoSide(String nomeArquivo) throws Exception {
		
		String[] row = null;
		String csvFilename = "C:\\UploadedFiles\\" + nomeArquivo + ".csv";

		CSVReader csvReader = new CSVReader(new FileReader(csvFilename), ';');
		List content = csvReader.readAll();

		Date date = new Date();

		for (Object object : content) {

			row = (String[]) object;			

			if (!row[0].isEmpty() && row[0].contains("8-")) {
			
				Integer id = Integer.parseInt(row[1]);				

				DefeitoSide defeitoSide = this.listarDefeitoSideEspecifico(id);

				if (!defeitoSide.getInstancia().isEmpty()) {

					defeitoSide.setSs(row[0].trim());
					defeitoSide.setDataAcao(date);
					this.entityManager.merge(defeitoSide);

					this.modificarDefeitoSide(defeitoSide);
					
					this.cadastrarDefeitoSideEmDefeito(defeitoSide);

				}			

			}			

		}
		
		csvReader.close();

	}

	public DefeitoSide listarDefeitoSideEspecifico(Integer id) throws Exception {

		try {

			Query query = this.entityManager.createQuery("FROM DefeitoSide d WHERE d.id =:param1");
			query.setParameter("param1", id);
			return (DefeitoSide) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Defeito Side não existe ou nao está associado a sua matricula.");

		}

	}

	public void modificarDefeitoSide(DefeitoSide defeitoSide) throws Exception {

		try {

			this.entityManager.merge(defeitoSide);

		} catch (Exception e) {

			throw new Exception("Erro ao modificar Defeito Side");

		}

	}

	public void cadastrarDefeitoSideEmDefeito(DefeitoSide defeitoSide) throws Exception {

		try {

			Defeito defeito = new Defeito();
			Date date = new Date();
			
			TipoDefeito tipoDefeito = this.listarTipoDefeitoEspecifico("Pro-Ativo");

			defeito.setSs(defeitoSide.getSs());
			defeito.setInstancia(defeitoSide.getInstancia());
			defeito.setDataAbertura(defeitoSide.getDataIntegrado());
			defeito.setDataVencimento(date);
			defeito.setDataIntegrado(defeitoSide.getDataIntegrado());
			defeito.setDataEncerramento(date);
			defeito.setTipificacao(defeitoSide.getTipificacao());
			defeito.setTipoDefeito(tipoDefeito);			
			defeito.setUsuarioEfika(defeitoSide.getUsuarioEfika());
			defeito.setStatusDefeito(defeitoSide.getStatusDefeito());
			defeito.setMotivoEncerramento(defeitoSide.getMotivoEncerramento());

			this.entityManager.persist(defeito);			

		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar defeito [3]");

		}

	}

	public TipoDefeito listarTipoDefeitoEspecifico(String nome) throws Exception {

		try {

			Query query = this.entityManager.createQuery("FROM TipoDefeito t WHERE t.nome =:param1");
			query.setParameter("param1", nome);
			return (TipoDefeito) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Tipo defeito nao existe");

		}

	}
	
}
