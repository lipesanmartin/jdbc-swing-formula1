package entities;

public class Corrida {

	private Integer idCorrida;
	private String nome;
	private String local;
	private Integer vencedorId;
	private Integer equipeVencedoraId;

	public Corrida() {

	}

	public Corrida(Integer idCorrida, String nome, String local, Integer vencedorId, Integer equipeVencedoraId) {
		this.idCorrida = idCorrida;
		this.nome = nome;
		this.local = local;
		this.vencedorId = vencedorId;
		this.equipeVencedoraId = equipeVencedoraId;
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

	public Integer getVencedorId() {
		return vencedorId;
	}

	public void setVencedorId(Integer vencedorId) {
		this.vencedorId = vencedorId;
	}

	public Integer getEquipeVencedoraId() {
		return equipeVencedoraId;
	}

	public void setEquipeVencedoraId(Integer equipeVencedoraId) {
		this.equipeVencedoraId = equipeVencedoraId;
	}

}
