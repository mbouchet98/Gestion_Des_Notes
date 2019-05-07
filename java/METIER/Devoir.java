package METIER;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import METIER.Eleve;
import METIER.Matier;


public class Devoir {

	private int idDevoir;
	private Date dateDevoir;
	private Matier laMatier;
	private Map<Eleve, Float> noteDevoirEleve;
	

	public Devoir(int idDevoir, Date dateDevoir, Matier laMatier){
		this.idDevoir = idDevoir;
		this.dateDevoir = dateDevoir;
		this.laMatier = laMatier;
		this.noteDevoirEleve = new HashMap<Eleve, Float>();
	}


	public Map<Eleve, Float> getNoteDevoirEleve() {
		return noteDevoirEleve;
	}


	public void setNoteDevoirEleve(Map<Eleve, Float> noteDevoirEleve) {
		this.noteDevoirEleve = noteDevoirEleve;
	}


	public int getIdDevoir(){
		return this.idDevoir;
	}
	
	public Date getDateDevoir(){
		return this.dateDevoir;
	}
	
	public void setDateDevoir(Date dateDevoir){
		this.dateDevoir = dateDevoir;
	}

	public Matier getLaMatier(){
		return this.laMatier;
	}
	
	public void setLaMatier(Matier laMatier){
		this.laMatier = laMatier;
	}

	@Override
	public String toString() {
		return "Devoir [idDevoir=" + idDevoir + ", dateDevoir=" + dateDevoir
				+ ", laMatier=" + laMatier + ", noteDevoirEleve="
				+ noteDevoirEleve + "]";
	}

	public void ajouterNoteDevoirEleve(float note, Eleve unEleve){
		this.noteDevoirEleve.put(unEleve, note);
	}
	
	public void supprimerNoteDevoirEleve(Eleve unEleve){
		this.noteDevoirEleve.remove(unEleve);
	}
	
	public float rechercheNoteDevoirEleve(Eleve unEleve){
		float note;
		note = noteDevoirEleve.get(unEleve);
		return note;
	}
	
}
