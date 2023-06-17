package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Corrida {

	private Integer idCorrida;
	private String nome;
	private String local;
	private Integer voltas;

	public Corrida() {

	}

	public Corrida(Integer idCorrida, String nome, String local, Integer voltas) {
		this.idCorrida = idCorrida;
		this.nome = nome;
		this.local = local;
		this.voltas = voltas;

	}

	public Integer getIdCorrida() {
		return idCorrida;
	}

	public void setIdCorrida(Integer idCorrida) {
		this.idCorrida = idCorrida;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Integer getVoltas() {
		return voltas;
	}

	public void setVoltas(Integer voltas) {
		this.voltas = voltas;
	}

	public boolean cadastrarCorrida(Integer idCorrida, String nome, String local, Integer voltas) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "insert into corrida set ID=?, Nome=?, Local=?, Voltas=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setInt(1, idCorrida);
			ps.setString(2, nome);
			ps.setString(3, local);
			ps.setInt(4, voltas);
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
				System.out.println("Piloto não cadastrado!");
				return false; // Piloto não cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.idCorrida = rs.getInt("ID");
					this.nome = rs.getString("Nome");
					this.local = rs.getString("Local");
					this.voltas = rs.getInt("Voltas");
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

	public boolean atualizarNumeroDeVoltas(Integer idCorrida, int novasVoltas) {
		if (!consultarCorrida(idCorrida))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conexão
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update corrida set Voltas=? where ID=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setInt(1, voltas);
				ps.setInt(2, idCorrida);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar numero de voltas: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

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
				// Define os par�metros da atualiza��o
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
}
