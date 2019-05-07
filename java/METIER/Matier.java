package METIER;

public class Matier {

	private int idMatier;
	private String nomMatier;
	private float coefMatier;
	
	public Matier(int idMatier, String nomMatier, float coefMatier){
		this.idMatier = idMatier;
		this.nomMatier = nomMatier;
		this.coefMatier = coefMatier;
	}

	public Matier(String nomM, float v) {

		this.idMatier =0;
		this.nomMatier = nomM;
		this.coefMatier = v;
	}


	public int getIdMetier(){
		return this.idMatier;
	}
	
	public String getNomMetier(){
		return this.nomMatier;
	}
	
	public void setNomMetier(String nomMatier){
		this.nomMatier = nomMatier;
	}
	
	public float getCoefMetier(){
		return this.coefMatier;
	}
	
	public void setCoefMetier(float coefMatier){
		this.coefMatier = coefMatier;
	}
	
	public String ToString(){
		return "[id = "+this.idMatier+"][Nom du Metier = "+this.nomMatier+"][Coeficient du Metier = "+this.coefMatier+"]";
				
	}
}
