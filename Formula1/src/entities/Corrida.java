package entities;

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

}
