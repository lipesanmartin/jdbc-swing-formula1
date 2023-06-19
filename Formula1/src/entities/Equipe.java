package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public boolean cadastrarEquipe(Integer idEquipe) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			if (!consultarEquipe(idEquipe)) {
				// Define a consulta
				String sql = "insert into equipe set ID=?, Nome=?, País=?, Chefe=?;";
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
			} else {
				System.out.println("ID de Equipe já cadastrada");
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar a equipe: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean cadastrarEquipe(Integer idEquipe, String nome, String nacionalidade, String chefe) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "insert into equipe set ID=?, Nome=?, País=?, Chefe=?;";
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
					this.nacionalidade = rs.getString("País");
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

	public boolean atualizarEquipe(Integer idEquipe, String nome, String pais, String chefe) {
		if (!consultarEquipe(idEquipe))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update equipe set Nome=?, País=?, Chefe=? where ID=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setString(1, nome);
				ps.setString(2, pais);
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

//	public boolean atualizarNome(Integer idEquipe, String novoNome) {
//		if (!consultarEquipe(idEquipe))
//			return false;
//		else {
//			// Define a conexão
//			Connection conexao = null;
//			try {
//				// Define a conex�o
//				conexao = Conexao.conectaBanco();
//				// Define a consulta
//				String sql = "update equipe set Nome=? where ID=?";
//				// Prepara a consulta
//				PreparedStatement ps = conexao.prepareStatement(sql);
//				// Define os par�metros da atualiza��o
//				ps.setString(1, novoNome);
//				ps.setInt(2, idEquipe);
//				int totalRegistrosAfetados = ps.executeUpdate();
//				if (totalRegistrosAfetados == 0)
//					System.out.println("Não foi feita a atualização!");
//				else
//					System.out.println("Atualização realizada!");
//				return true;
//			} catch (SQLException erro) {
//				System.out.println("Erro ao atualizar nome da equipe: " + erro.toString());
//				return false;
//			} finally {
//				Conexao.fechaConexao(conexao);
//			}
//		}
//	}
//	
//	public boolean atualizarPais(Integer idEquipe, String novoPais) {
//		if (!consultarEquipe(idEquipe))
//			return false;
//		else {
//			// Define a conexão
//			Connection conexao = null;
//			try {
//				// Define a conex�o
//				conexao = Conexao.conectaBanco();
//				// Define a consulta
//				String sql = "update equipe set País=? where ID=?";
//				// Prepara a consulta
//				PreparedStatement ps = conexao.prepareStatement(sql);
//				// Define os par�metros da atualiza��o
//				ps.setString(1, novoPais);
//				ps.setInt(2, idEquipe);
//				int totalRegistrosAfetados = ps.executeUpdate();
//				if (totalRegistrosAfetados == 0)
//					System.out.println("Não foi feita a atualização!");
//				else
//					System.out.println("Atualização realizada!");
//				return true;
//			} catch (SQLException erro) {
//				System.out.println("Erro ao atualizar país da equipe: " + erro.toString());
//				return false;
//			} finally {
//				Conexao.fechaConexao(conexao);
//			}
//		}
//	}
//
//	public boolean atualizarChefe(Integer idEquipe, String novoChefe) {
//		if (!consultarEquipe(idEquipe))
//			return false;
//		else {
//			// Define a conexão
//			Connection conexao = null;
//			try {
//				// Define a conex�o
//				conexao = Conexao.conectaBanco();
//				// Define a consulta
//				String sql = "update equipe set Chefe=? where ID=?";
//				// Prepara a consulta
//				PreparedStatement ps = conexao.prepareStatement(sql);
//				// Define os par�metros da atualiza��o
//				ps.setString(1, novoChefe);
//				ps.setInt(2, idEquipe);
//				int totalRegistrosAfetados = ps.executeUpdate();
//				if (totalRegistrosAfetados == 0)
//					System.out.println("Não foi feita a atualização!");
//				else
//					System.out.println("Atualização realizada!");
//				return true;
//			} catch (SQLException erro) {
//				System.out.println("Erro ao atualizar nome do chefe de equipe: " + erro.toString());
//				return false;
//			} finally {
//				Conexao.fechaConexao(conexao);
//			}
//		}
//	}

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
}
