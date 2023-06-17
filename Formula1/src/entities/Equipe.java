package entities;

public class Equipe {

	private Integer idEquipe;
	private String nome;
	private String nacionalidade;
	private String chefe;

	public Equipe() {

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

	
}
