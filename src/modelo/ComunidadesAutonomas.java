package modelo;

public class ComunidadesAutonomas {
	private int codCA;
	private String CA;

	public ComunidadesAutonomas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComunidadesAutonomas(int codCA, String cA) {
		super();
		this.codCA = codCA;
		CA = cA;
	}

	public int getCodCA() {
		return codCA;
	}

	public void setCodCA(int codCA) {
		this.codCA = codCA;
	}

	public String getCA() {
		return CA;
	}

	public void setCA(String cA) {
		CA = cA;
	}

}
