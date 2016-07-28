package models.sistema;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
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
import entidades.sistema.DefeitoFulltest;
import entidades.sistema.DefeitoSide;
import entidades.sistema.Lote;
import entidades.sistema.StatusDefeito;
import entidades.sistema.Tipificacao;
import entidades.sistema.TipoDefeito;

@Stateless
public class ImportaCSVServico {

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

			String tipoDefeitoPlanilha = row[6].trim();

			/**
			 * PADROES
			 * SS/INSTANCIA/TIPIFICACAO/DATAABERTURA/DATAVENCIMENTO/FULLTEST/TIPODEFEITO/INFORMACÕES
			 **/

			if (tipoDefeitoPlanilha.equalsIgnoreCase("PROATIVO")) {

				if (!row[1].isEmpty() && !row[2].isEmpty()) {

					/**
					 * PARA PRO ATIVO.
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
					defeitoSide.setInformacoes(row[7]);

					this.entityManager.persist(defeitoSide);

				}

			}else if (tipoDefeitoPlanilha.equalsIgnoreCase("LINHA") || tipoDefeitoPlanilha.equalsIgnoreCase("BANDA") || tipoDefeitoPlanilha.equalsIgnoreCase("TV")) {
				
				/**
				 * Validação :
				 * Se conter 8- & instancia não for vazio & tipificação não for vazio 
				 **/

				if (row[0].contains("8-") && !row[1].isEmpty() && !row[2].isEmpty()) {
					
					if (row[5].equalsIgnoreCase("SIM")) {

						/**
						 * Ira fazer o FULLTEST
						 * E salva na tabela para aguardar o fulltest.
						 **/

						DefeitoFulltest defeitoFulltest = new DefeitoFulltest();

						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

						Date dataAbertura;
						Date dataVencimento;

						if (row[3].isEmpty()) {

							dataAbertura = new Date();
							dataVencimento = new Date();

						}else{

							dataAbertura = formatter.parse(row[3]);
							dataVencimento = formatter.parse(row[4]);

						}
						
						defeitoFulltest.setSs(row[0].trim());
						defeitoFulltest.setInstancia(row[1].trim());
						defeitoFulltest.setTipificacao(this.acaoTipificacao(row[2].trim()));												
						defeitoFulltest.setDataAbertura(dataAbertura);
						defeitoFulltest.setDataVencimento(dataVencimento);
						defeitoFulltest.setTipoDefeito(this.listarTipoDefeitoEspecifico(row[6].trim()));
						defeitoFulltest.setInformacoes(row[7].trim());
						defeitoFulltest.setFulltest("");
												
						this.entityManager.persist(defeitoFulltest);
												
					}else{
						
						/**
						 * Não ira fazer o FULLTEST
						 * Salva direto na tabela de defeitos.
						 **/
						
						Defeito defeito = new Defeito();
						
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

						Date dataAbertura;
						Date dataVencimento;

						if (row[3].isEmpty()) {

							dataAbertura = new Date();
							dataVencimento = new Date();

						}else{

							dataAbertura = formatter.parse(row[3]);
							dataVencimento = formatter.parse(row[4]);

						}
						
						defeito.setSs(row[0].trim());
						defeito.setInstancia(row[1].trim());
						defeito.setTipificacao(this.acaoTipificacao(row[2].trim()));
						defeito.setDataAbertura(dataAbertura);
						defeito.setDataVencimento(dataVencimento);
						defeito.setTipoDefeito(this.listarTipoDefeitoEspecifico(row[6].trim()));
						defeito.setResultadoFulltest("");
												
						this.entityManager.persist(defeito);
						
					}

				}

			}

		}

		csvReader.close();

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
