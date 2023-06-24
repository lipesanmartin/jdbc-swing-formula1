package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
/**
 * A classe Piloto representa um piloto de corrida.
 */
public class Piloto {

	private Integer idPiloto;
	private String nome;
	private String nacionalidade;
	private Integer numCarro;
	private Integer idEquipe;
	/**
	 * Construtor padrão da classe Piloto.
	 */
	public Piloto() {
	}
	/**
	 * Construtor explícito da classe Piloto que recebe o ID do piloto.
	 *
	 * @param idPiloto o ID do piloto
	 */
	public Piloto(Integer idPiloto) {
		this.idPiloto = idPiloto;
		this.nome = "";
		this.nacionalidade = "";
		this.numCarro = 0;
		this.idEquipe = 0;
	}
	/**
	 * Construtor explícito da classe Piloto que recebe todas as informações do piloto.
	 *
	 * @param idPiloto       o ID do piloto
	 * @param nome           o nome do piloto
	 * @param nacionalidade  a nacionalidade do piloto
	 * @param numCarro       o número do carro do piloto
	 * @param idEquipe       o ID da equipe do piloto
	 */
	public Piloto(Integer idPiloto, String nome, String nacionalidade, Integer numCarro, Integer idEquipe) {
		this.idPiloto = idPiloto;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.numCarro = numCarro;
		this.idEquipe = idEquipe;
	}
	/**
	 * Obtém o ID do piloto.
	 *
	 * @return o ID do piloto
	 */
	public Integer getIdPiloto() {
		return idPiloto;
	}
	/**
	 * Define/modifica o ID do piloto.
	 *
	 * @param idPiloto o ID do piloto
	 */
	public void setIdPiloto(Integer idPiloto) {
		this.idPiloto = idPiloto;
	}
	/**
	 * Obtém o nome do piloto.
	 *
	 * @return o nome do piloto
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Define/modifica o nome do piloto.
	 *
	 * @param nome o nome do piloto
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Obtém a nacionalidade do piloto.
	 *
	 * @return a nacionalidade do piloto
	 */
	public String getNacionalidade() {
		return nacionalidade;
	}
	/**
	 * Define/modifica a nacionalidade do piloto.
	 *
	 * @param nacionalidade a nacionalidade do piloto
	 */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	/**
	 * Obtém o número do carro do piloto.
	 *
	 * @return o número do carro do piloto
	 */
	public Integer getNumCarro() {
		return numCarro;
	}
	/**
	 * Obtém o número do carro do piloto com base no ID do piloto.
	 *
	 * @param idPiloto o ID do piloto
	 * @return o número do carro do piloto
	 */
	public Integer getNumCarroById(Integer idPiloto) {
		// Define a conexão
				Connection conexao = null;
				try {
					conexao = Conexao.conectaBanco();
					// Define a consulta
					String sql = "select NumeroCarro from piloto where ID=?";
					// Prepara a consulta
					PreparedStatement ps = conexao.prepareStatement(sql);
					// Define os parâmetros da consulta
					ps.setInt(1, idPiloto);
					// Executa a consulta, resultando em um objeto da classe ResultSet
					ResultSet rs = ps.executeQuery();
					if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
						System.out.println("Piloto não cadastrado!");
						return -1; // Piloto não cadastrado
					} else {
						// Efetua a leitura do registro da tabela
						while (rs.next()) {
							this.numCarro = rs.getInt("NumeroCarro");
						}
						return numCarro;
					}
				} catch (SQLException erro) {
					System.out.println("Erro ao consultar o piloto: " + erro.toString());
					return -1;
				} finally {
					Conexao.fechaConexao(conexao);
				}
			}
	
	/**
	 * Define/modifica o número do carro.
	 *
	 * @param numCarro O número do carro a ser definido.
	 */
	public void setNumCarro(Integer numCarro) {
		this.numCarro = numCarro;
	}
	/**
	 * Obtém o ID da equipe.
	 *
	 * @return O ID da equipe.
	 */
	public Integer getIdEquipe() {
		return idEquipe;
	}
	/**
	 * Define/modifica o ID da equipe.
	 *
	 * @param idEquipe O ID da equipe a ser definido.
	 */
	public void setIdEquipe(Integer idEquipe) {
		this.idEquipe = idEquipe;
	}
	/**
	 * Cadastra um piloto.
	 *
	 * @param idPiloto       O ID do piloto a ser cadastrado.
	 * @param nome           O nome do piloto.
	 * @param nacionalidade  A nacionalidade do piloto.
	 * @param numCarro       O número do carro do piloto.
	 * @param idEquipe       O ID da equipe do piloto.
	 * @return true se o cadastro foi realizado com sucesso e false caso contrário.
	 */
	public boolean cadastrarPiloto(Integer idPiloto, String nome, String nacionalidade, Integer numCarro,
			Integer idEquipe) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "insert into piloto set ID=?, Nome=?, Nacionalidade=?, NumeroCarro=?, EquipeID=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setInt(1, idPiloto);
			ps.setString(2, nome);
			ps.setString(3, nacionalidade);
			ps.setInt(4, numCarro);
			ps.setInt(5, idEquipe);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar o piloto: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Consulta um piloto pelo ID.
	 *
	 * @param idPiloto O ID do piloto a ser consultado.
	 * @return true se o piloto foi encontrado e false caso contrário.
	 */
	public boolean consultarPiloto(Integer idPiloto) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from piloto where ID=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setInt(1, idPiloto);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Piloto não cadastrado!");
				return false; // Piloto não cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.idPiloto = rs.getInt("ID");
					this.nome = rs.getString("Nome");
					this.nacionalidade = rs.getString("Nacionalidade");
					this.numCarro = rs.getInt("NumeroCarro");
					this.idEquipe = rs.getInt("EquipeID");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o piloto: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Consulta um piloto pelo número do carro.
	 *
	 * @param numCarro O número do carro a ser consultado.
	 * @return true se o piloto foi encontrado e false caso contrário.
	 */
	public boolean consultarNumeroCarro(Integer numCarro) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select ID from piloto where NumeroCarro=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setInt(1, numCarro);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Piloto não cadastrado!");
				return false; // Piloto não cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.numCarro = rs.getInt("ID");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o piloto: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Atualiza o número do carro de um piloto.
	 *
	 * @param idPiloto O ID do piloto a ser atualizado.
	 * @param numCarro O novo número do carro.
	 * @return true se a atualização foi realizada com sucesso e false caso contrário.
	 */
	public boolean atualizarPiloto(Integer idPiloto, Integer numCarro) {
		if (!consultarPiloto(idPiloto))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update piloto set NumeroCarro=?, where ID=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setInt(1, numCarro);
				ps.setInt(2, idPiloto);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar piloto: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	/**
	 * Atualiza o número do carro e o ID da equipe de um piloto.
	 *
	 * @param idPiloto O ID do piloto a ser atualizado.
	 * @param numCarro O novo número do carro.
	 * @param idEquipe O novo ID da equipe.
	 * @return true se a atualização foi realizada com sucesso, false caso contrário.
	 */
	public boolean atualizarPiloto(Integer idPiloto, Integer numCarro, Integer idEquipe) {
		if (!consultarPiloto(idPiloto))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update piloto set NumeroCarro=?, EquipeID=? where ID=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os parâmetros da atualização
				ps.setInt(1, numCarro);
				ps.setInt(2, idEquipe);
				ps.setInt(3, idPiloto);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar piloto: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	/**
	 * Obtém o ID de um piloto pelo nome.
	 *
	 * @param nome O nome do piloto.
	 * @return O ID do piloto.
	 */
	public Integer getIdByName(String nome) {
		Connection conexao = null;
		int id = -1;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select ID from piloto where nome=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, nome);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Não há pilotos cadastrados!");
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					id = rs.getInt("ID");

				}
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a equipe: " + erro.toString());
		} finally {
			Conexao.fechaConexao(conexao);
		}
		return id;
	}
	/**
	 * Apaga um piloto pelo ID.
	 *
	 * @param idPiloto O ID do piloto a ser apagado.
	 * @return true se o piloto foi apagado com sucesso, false caso contrário.
	 */
	public boolean apagarPiloto(Integer idPiloto) {
		if (!consultarPiloto(idPiloto))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "delete from piloto where ID=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setInt(1, idPiloto);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao apagar piloto: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	/**
	 * Obtém os dados da tabela de pilotos.
	 *
	 * @return Uma matriz de objetos contendo os dados dos pilotos.
	 */
	public Object[][] getDataFromTable() {
		Connection conexao = null;
		List<Object[]> lista = new ArrayList<>();

		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select p.ID, p.Nome, p.Nacionalidade, p.NumeroCarro, e.Nome as Equipe, p.EquipeId from Piloto p join Equipe e on p.EquipeID = e.ID order by ID;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();

			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Não há pilotos cadastrados!");
				return new Object[0][0]; // Nenhum piloto cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					Object[] pilotoInfo = new Object[6];
					pilotoInfo[0] = rs.getInt("ID");
					pilotoInfo[1] = rs.getString("Nome");
					pilotoInfo[2] = rs.getString("Nacionalidade");
					pilotoInfo[3] = rs.getInt("NumeroCarro");
					pilotoInfo[4] = rs.getString("Equipe");
					pilotoInfo[5] = rs.getInt("EquipeID");
					lista.add(pilotoInfo);
				}
				return lista.toArray(new Object[0][0]);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar piloto: " + erro.toString());
			return new Object[0][0];
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Obtém a lista de nomes dos pilotos.
	 *
	 * @return Uma lista contendo os nomes dos pilotos.
	 */
	public List<String> getNameList() {
		Connection conexao = null;
		List<String> lista = new ArrayList<>();
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select nome from piloto;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Não há pilotos cadastrados!");
				return lista; // Equipe não cadastrada
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					lista.add(rs.getString("nome"));

				}
				return lista;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar piloto: " + erro.toString());
			return lista;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
}
