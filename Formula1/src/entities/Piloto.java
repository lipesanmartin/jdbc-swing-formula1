package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Piloto {

	private Integer idPiloto;
	private String nome;
	private String nacionalidade;
	private Integer numCarro;

	public Piloto() {
	}

	public Piloto(Integer idPiloto, String nome, String nacionalidade, Integer idEquipe) {
		this.idPiloto = idPiloto;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.numCarro = idEquipe;
	}

	public Integer getIdPiloto() {
		return idPiloto;
	}

	public void setIdPiloto(Integer idPiloto) {
		this.idPiloto = idPiloto;
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

	public Integer getNumCarro() {
		return numCarro;
	}

	public void setNumCarro(Integer idEquipe) {
		this.numCarro = idEquipe;
	}

	public boolean cadastrarPiloto(Integer idPiloto, String nome, String nacionalidade, Integer numCarro) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "insert into piloto set ID=?, Nome=?, Nacionalidade=?, NumeroCarro=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setInt(1, idPiloto);
			ps.setString(2, nome);
			ps.setString(3, nacionalidade);
			ps.setInt(4, numCarro);
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

	public boolean atualizarNumCarro(Integer idPiloto, Integer numCarro) {
		if (!consultarPiloto(idPiloto))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update piloto set NumeroCarro=? where ID=?";
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
				System.out.println("Erro ao atualizar numero do carro: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

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
				System.out.println("Erro ao atualizar a conta: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
}
