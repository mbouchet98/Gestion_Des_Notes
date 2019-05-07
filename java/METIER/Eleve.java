package METIER;

public class Eleve {

	private int idEleve;
	private String nomEleve;
	private String prenomEleve;
	private String dateNaissEleve;
	private Classe laClasse;
	

	public Eleve(int idEleve, String nomEleve, String prenomEleve, String dateNaissEleve, Classe laClasse){
		this.idEleve = idEleve;
		this.nomEleve = nomEleve;
		this.prenomEleve = prenomEleve;
		this.dateNaissEleve = dateNaissEleve;
		this.laClasse = laClasse;
	}

	public Eleve(String nomE, String prenomE, String dateNaissE, Classe classeE) {
		this.idEleve = 0;
		this.nomEleve = nomE;
		this.prenomEleve = prenomE;
		this.dateNaissEleve = dateNaissE;
		this.laClasse = classeE;
	}


	public int getIdEleve(){
		return this.idEleve;
	}
	
	public String getNomEleve(){
		return this.nomEleve;
	}
	public void setNomEleve(String nomEleve){
		this.nomEleve = nomEleve;
	}
	
	public String getPrenomEleve(){
		return this.prenomEleve;
	}
	public void setPrenomEleve(String prenomEleve){
		this.prenomEleve = prenomEleve;
	}
	
	public String getDateNaissEleve(){
		return this.dateNaissEleve;
	}
	public void setDateNaissEleve(String dateNaissEleve){
		this.dateNaissEleve = dateNaissEleve;
	}
	
	public Classe getLaClasse(){
		return this.laClasse;
	}
	
	public void setLaClasse(Classe laClasse){
		this.laClasse = laClasse;
	}

	public String toString() {
		return "Eleve [idEleve=" + idEleve + ", nomEleve=" + nomEleve
				+ ", prenomEleve=" + prenomEleve + ", dateNaissEleve="
				+ dateNaissEleve + ", laClasse=" + laClasse + "]";
	}
}
