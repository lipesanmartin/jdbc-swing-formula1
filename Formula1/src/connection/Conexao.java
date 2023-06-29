package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * A classe Conexao é responsável por iniciar e encerrar a conexão com o banco de dados.
 */
public class Conexao {
	/**
	 * Estabelece a conexão com o banco de dados MySQL.
	 *
	 * @return A conexão estabelecida com o banco de dados.
	 */
	public static Connection conectaBanco() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/Formula1"; // URL do banco de dados
			String user = "root"; // nome do usuário do banco
			String password = ""; // senha do banco (deve ser mudada de acordo com o banco)
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver não encontrado: " + erro);
		} catch (SQLException erro) {
			System.out.println("Erro de conexão ao banco de dados: " + erro.toString());
		} catch (Exception erro) {
			System.out.println("Erro não identificado: " + erro.toString());
		}
		return conexao;
	}
	/**
	 * Fecha a conexão com o banco de dados.
	 *
	 * @param conexao A conexão a ser fechada.
	 */
	public static void fechaConexao(Connection conexao) {
		try {
			conexao.close();
		} catch (Exception erro) {
			System.out.println("Erro ao fechar a conexão: " + erro.toString());
		}
	}

}