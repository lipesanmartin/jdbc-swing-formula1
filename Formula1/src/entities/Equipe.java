package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;

public class Equipe {

	private Integer idEquipe;
	private String nome;
	private String nacionalidade;
	private String chefe;

	public Equipe() {

	}

	public Equipe(Integer idEquipe) {
		this.idEquipe = idEquipe;
		this.nome = "";
		this.nacionalidade = "";
		this.chefe = "";
	}

	public Equipe(Integer idEquipe, String nome, String nacionalidade, String chefe) {
		this.idEquipe = idEquipe;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.chefe = chefe;
	}

	public Integer getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(Integer idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getChefe() {
		return chefe;
	}

	public void setChefe(String chefe) {
		this.chefe = chefe;
	}

	public boolean cadastrarEquipe(Integer idEquipe, String nome, String nacionalidade, String chefe) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "insert into equipe set ID=?, Nome=?, Nacionalidade=?, Chefe=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setInt(1, idEquipe);
			ps.setString(2, nome);
			ps.setString(3, nacionalidade);
			ps.setString(4, chefe);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar a equipe: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public Integer getIdByName(String nome) {
		Connection conexao = null;
		int id = -1;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select ID from equipe where nome=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, nome);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Não há equipes cadastradas!");
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
	
	public List<Integer> getEquipeIdList() {
		Connection conexao = null;
		List<Integer> lista = new ArrayList<>();
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select ID from equipe order by ID;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Não há equipes cadastradas!");
				return lista; // Equipe não cadastrada
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					lista.add(rs.getInt("ID"));

				}
				return lista;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a equipe: " + erro.toString());
			return lista;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	
	public List<String> getEquipeNameList() {
		Connection conexao = null;
		List<String> lista = new ArrayList<>();
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select nome from equipe order by ID;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Não há equipes cadastradas!");
				return lista; // Equipe não cadastrada
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					lista.add(rs.getString("nome"));

				}
				return lista;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a equipe: " + erro.toString());
			return lista;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public List<String> getAllNamesById() {
		Connection conexao = null;
		List<String> lista = new ArrayList<>();
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select nome from equipe order by ID;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Não há equipes cadastradas!");
				return lista; // Equipe não cadastrada
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					lista.add(rs.getString("nome"));

				}
				return lista;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a equipe: " + erro.toString());
			return lista;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public Object[][] getListaPilotos(Integer equipeId) {
		Connection conexao = null;
		List<Object[]> lista = new ArrayList<>();

		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select Nome, Nacionalidade, NumeroCarro from piloto where EquipeID=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, equipeId);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();

			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Não há pilotos cadastrados!");
				return new Object[0][0]; // Nenhum piloto cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					Object[] pilotoInfo = new Object[3];
					pilotoInfo[0] = rs.getString("Nome");
					pilotoInfo[1] = rs.getString("Nacionalidade");
					pilotoInfo[2] = rs.getInt("NumeroCarro");
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

	public boolean consultarEquipe(Integer idEquipe) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from equipe where ID=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setInt(1, idEquipe);
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Equipe não cadastrada!");
				return false; // Equipe não cadastrada
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.idEquipe = rs.getInt("ID");
					this.nome = rs.getString("Nome");
					this.nacionalidade = rs.getString("Nacionalidade");
					this.chefe = rs.getString("Chefe");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a equipe: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarEquipe(Integer idEquipe, String nome, String nacionalidade, String chefe) {
		if (!consultarEquipe(idEquipe))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update equipe set Nome=?, Nacionalidade=?, Chefe=? where ID=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setString(1, nome);
				ps.setString(2, nacionalidade);
				ps.setString(3, chefe);
				ps.setInt(4, idEquipe);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar equipe: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	public boolean apagarEquipe(Integer idEquipe) {
		if (!consultarEquipe(idEquipe))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "delete from equipe where ID=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setInt(1, idEquipe);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao apagar equipe: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	public Object[][] getDataFromTable() {
		Connection conexao = null;
		List<Object[]> lista = new ArrayList<>();

		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from equipe order by ID;";
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
					pilotoInfo[3] = rs.getString("Chefe");
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
