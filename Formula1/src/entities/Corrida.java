package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;

/**
 * A classe Corrida representa uma corrida de Fórmula 1.
 */
public class Corrida {

	private Integer idCorrida;
	private String nome;
	private String circuito;
	private Integer vencedor;
	private Integer voltas;

	/**
	 * Construtor padrão da classe Corrida.
	 */
	public Corrida() {

	}

	/**
	 * Construtor explícito da classe Corrida.
	 *
	 * @param idCorrida O ID da corrida.
	 * @param nome      O nome da corrida.
	 * @param circuito  O circuito da corrida.
	 * @param voltas    O número de voltas da corrida.
	 * @param vencedor  O ID do piloto vencedor da corrida.
	 */
	public Corrida(Integer idCorrida, String nome, String circuito, Integer voltas, Integer vencedor) {
		this.idCorrida = idCorrida;
		this.nome = nome;
		this.circuito = circuito;
		this.vencedor = vencedor;
		this.voltas = voltas;
	}

	/**
	 * Obtém o ID da corrida.
	 *
	 * @return O ID da corrida.
	 */
	public Integer getIdCorrida() {
		return idCorrida;
	}

	/**
	 * Define/modifica o ID da corrida.
	 *
	 * @param idCorrida O ID da corrida a ser definido.
	 */
	public void setIdCorrida(Integer idCorrida) {
		this.idCorrida = idCorrida;
	}

	public String getNome() {
		return nome;
	}

	/**
	 * Obtém o nome da corrida.
	 *
	 * @return O nome da corrida.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtém o nome do circuito.
	 *
	 * @return O nome do circuito.
	 */
	public String getCircuito() {
		return circuito;
	}

	/**
	 * Define/modifica o nome do circuito.
	 *
	 * @param circuito O nome do circuito a ser definido.
	 */
	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}

	/**
	 * Obtém o número de voltas do circuito da corrida.
	 *
	 * @return O circuito da corrida.
	 */
	public Integer getVoltas() {
		return voltas;
	}

	/**
	 * Define/modifica o número de voltas do circuito da corrida.
	 *
	 * @param O circuito da corrida a ser definido.
	 */
	public void setVoltas(Integer voltas) {
		this.voltas = voltas;
	}

	/**
	 * Obtém o ID do piloto vencedor da corrida.
	 *
	 * @return O ID do piloto vencedor da corrida.
	 */
	public Integer getVencedor() {
		return vencedor;
	}

	/**
	 * Define/modifica o ID do piloto vencedor da corrida.
	 *
	 * @param vencedor O ID do piloto vencedor da corrida a ser definido.
	 */
	public void setVencedor(Integer voltas) {
		this.vencedor = voltas;
	}

	/**
	 * Realiza o cadastro de uma corrida no banco de dados MySQL.
	 *
	 * @param idCorrida O ID da corrida.
	 * @param nome      O nome da corrida.
	 * @param circuito  O circuito da corrida.
	 * @param voltas    O número de voltas da corrida.
	 * @param vencedor  O ID do piloto vencedor da corrida.
	 * @return true se o cadastro for realizado com sucesso e false caso contrário.
	 */
	public boolean cadastrarCorrida(Integer idCorrida, String nome, String circuito, Integer voltas, Integer vencedor) {
		// Define a conexão
		Connection conexao = null;
		try {

			conexao = Conexao.conectaBanco();
			Piloto piloto = new Piloto();
			if (!piloto.consultarPiloto(vencedor)) {
				System.out.println("Não foi encontrado um piloto com o ID fornecido!");
				return false;
			}
			// Define a consulta
			String sql = "insert into corrida set ID=?, Nome=?, circuito=?, voltas=?, VencedorID=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setInt(1, idCorrida);
			ps.setString(2, nome);
			ps.setString(3, circuito);
			ps.setInt(4, voltas);
			ps.setInt(5, vencedor);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar a corrida: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	/**
	 * Consulta uma corrida no banco de dados MySQL através do seu ID.
	 *
	 * @param idCorrida O ID da corrida a ser consultada.
	 * @return true se a corrida for encontrada, false caso contrário.
	 */
	public boolean consultarCorrida(Integer idCorrida) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from corrida where ID=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setInt(1, idCorrida);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Corrida não cadastrada!");
				return false; // Corrida não cadastrada
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.idCorrida = rs.getInt("ID");
					this.nome = rs.getString("Nome");
					this.circuito = rs.getString("Circuito");
					this.vencedor = rs.getInt("VencedorID");
					this.voltas = rs.getInt("voltas");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a corrida: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	/**
	 * Atualiza as informações de uma corrida no banco de dados MySQL.
	 *
	 * @param idCorrida O ID da corrida a ser atualizada.
	 * @param voltas    O número de voltas da corrida.
	 * @param vencedor  O ID do piloto vencedor da corrida.
	 * @return true se a atualização for realizada com sucesso e false caso
	 *         contrário.
	 */
	public boolean atualizarCorrida(Integer idCorrida, Integer voltas, Integer vencedor) {
		if (!consultarCorrida(idCorrida))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conexão
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update corrida set VencedorID=?, voltas=? where ID=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os parâmetros da atualização
				ps.setInt(1, vencedor);
				ps.setInt(2, voltas);
				ps.setInt(3, idCorrida);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar id de vencedor: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	/**
	 * Apaga uma corrida do banco de dados.
	 *
	 * @param idCorrida O ID da corrida a ser apagada.
	 * @return true se a corrida for apagada com sucesso e false caso contrário.
	 */
	public boolean apagarCorrida(Integer idCorrida) {
		if (!consultarCorrida(idCorrida))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "delete from corrida where ID=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualização
				ps.setInt(1, idCorrida);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao apagar corrida: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	/**
	 * Retorna os dados das corridas cadastradas no banco de dados MySQL.
	 *
	 * @return uma matriz de objetos contendo os dados das corridas.
	 */
	public Object[][] getDataFromTable() {
		Connection conexao = null;
		List<Object[]> lista = new ArrayList<>();

		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select c.id, c.nome, c.circuito, p.nome, e.Nome \n" + "from corrida c \n"
					+ "join piloto p on c.VencedorID = p.ID\n" + "join equipe e on e.ID = p.EquipeID order by id;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();

			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Não há corridas cadastradas!");
				return new Object[0][0]; // Nenhum piloto cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					Object[] pilotoInfo = new Object[6];
					pilotoInfo[0] = rs.getInt("c.ID");
					pilotoInfo[1] = rs.getString("c.Nome");
					pilotoInfo[2] = rs.getString("c.circuito");
					pilotoInfo[3] = rs.getString("p.Nome");
					pilotoInfo[4] = rs.getString("e.Nome");
					lista.add(pilotoInfo);
				}
				return lista.toArray(new Object[0][0]);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a equipe: " + erro.toString());
			return new Object[0][0];
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
}
