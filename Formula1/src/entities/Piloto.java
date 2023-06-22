package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;

public class Piloto {

	private Integer idPiloto;
	private String nome;
	private String nacionalidade;
	private Integer numCarro;
	private Integer idEquipe;

	public Piloto() {
	}

	public Piloto(Integer idPiloto) {
		this.idPiloto = idPiloto;
		this.nome = "";
		this.nacionalidade = "";
		this.numCarro = 0;
		this.idEquipe = 0;
	}

	public Piloto(Integer idPiloto, String nome, String nacionalidade, Integer numCarro, Integer idEquipe) {
		this.idPiloto = idPiloto;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.numCarro = numCarro;
		this.idEquipe = idEquipe;
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

	public void setNumCarro(Integer numCarro) {
		this.numCarro = numCarro;
	}

	public Integer getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(Integer idEquipe) {
		this.idEquipe = idEquipe;
	}

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
				// Define os par�metros da atualiza��o
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
				System.out.println("Erro ao atualizar numero do carro: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	// gera uma lista com as IDs de todos os pilotos cadastrados
	public List<Integer> getPilotoIdList() {
		Connection conexao = null;
		List<Integer> lista = new ArrayList<>();
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select ID from piloto order by ID;";
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
}
