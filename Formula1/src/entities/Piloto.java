package entities;

public class Piloto {

	private Integer idPiloto;
	private String nome;
	private String nacionalidade;
	private Integer idEquipe;

	public Piloto() {
	}

	public Piloto(Integer idPiloto, String nome, String nacionalidade, Integer idEquipe) {
		this.idPiloto = idPiloto;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
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

	public Integer getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(Integer idEquipe) {
		this.idEquipe = idEquipe;
	}

}
