package modelo;

public class Provincia {
	private int codProvincia;
	private String provincia;
	private int codCA; 

	

	public Provincia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Provincia(int codProvincia, String provincia, int codCA) {
		super();
		this.codProvincia = codProvincia;
		this.provincia = provincia;
		this.codCA = codCA;
	}

	public int getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(int codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getCodCA() {
		return codCA;
	}

	public void setCodCA(int codCA) {
		this.codCA = codCA;
	}
}
