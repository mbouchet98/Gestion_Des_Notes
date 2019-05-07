package METIER;

import java.util.ArrayList;
import java.util.List;


public class Classe {

	private int idCalsse;
	private String libClasse;
	private int nbClasse;
	private List<Eleve> lesEleves;
	
	
	public Classe(int idClasse, String libClasse, int nbClasse){
		
		this.idCalsse = idClasse;
		this.libClasse = libClasse;
		this.nbClasse = nbClasse;
		this.lesEleves = new ArrayList<Eleve>();
		
	}

	public Classe(String libC, int i) {
		this.idCalsse = 0;
		this.libClasse = libC;
		this.nbClasse = i;
		this.lesEleves = new ArrayList<Eleve>();
	}


	public List<Eleve> getLesEleves() {
		return lesEleves;
	}

	public void setLesEleves(List<Eleve> lesEleves) {
		this.lesEleves = lesEleves;
	}

	public int getIdClasse(){
		return this.idCalsse;
	}
	
	public String getLibClasse(){
		return this.libClasse;
	}
	
	public void setLibClasse(String libClasse){
		this.libClasse = libClasse;
	}
	
	public int getNbClasse(){
		return this.nbClasse;
	}
	
	public void setNbClasse(int nbClasse){
		this.nbClasse = nbClasse; 
	}

	public String toString() {
		return libClasse +" "+ nbClasse;
	}
	// ajouter / supprimer / recherche collection et dictionnaire
	
	public void ajouterEleve(int idEleve, String nomEleve, String prenomEleve, String dateNaissEleve,Classe maClasse){
		Eleve unEleve;
		
		unEleve = new Eleve(idEleve, nomEleve, prenomEleve, dateNaissEleve,maClasse);
		lesEleves.add(unEleve);
	}
	
	public void ajouterEleve(Eleve unEleve){
		
		lesEleves.add(unEleve);
	}
	
	
	public void supprimerEleve(int idEleve){
		int nb;
		int i;
		nb = lesEleves.size();
		i = 0;
		while (i<nb && idEleve != lesEleves.get(i).getIdEleve()) {
			i++;
			}
			
			if(i<nb){
				lesEleves.remove(lesEleves.get(i));
			}
		}	
	
	
	public void supprimerEleve(Eleve unEleve){
		lesEleves.remove(unEleve);
	}
	
	public Eleve rechercheEleve(String nomEleve, String prenomEleve){
		
		Eleve monEleve;
		int nb;
		int i;
		nb = lesEleves.size();
		i = 0;
		while (i<nb && nomEleve.compareTo(lesEleves.get(i).getNomEleve()) !=0 && prenomEleve.compareTo(lesEleves.get(i).getPrenomEleve()) !=0) {
			i++;
			}
			
			if(i<nb){
				monEleve = lesEleves.get(i);
			}
			else{
				monEleve = null;
			}
			
			return monEleve;
	}

}
