import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Partie implements Serializable {
	private Joueur joueur;
	private Grille grille;
    public Partie (Joueur joueur,Grille grille){
    	this.joueur=joueur;
    	this.grille=grille;
    }
    public void setJoueur(Joueur joueur){
    	this.joueur=joueur;
    }
    public void setGrille(Grille grille){
    	this.grille=grille;
    }
    public Grille getGrille(){
    	return this.grille;
    }
    public Joueur getJoueur(){
    	return this.joueur;
    }
  
}
