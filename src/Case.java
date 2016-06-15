import java.awt.Color;
import java.io.Serializable;


public class Case implements Serializable {
protected int numero;
protected Color couleur;
public Case(int d,Color green){
	this.numero=d;
	this.couleur=green;
}
public int getNumero(){
	return this.numero;
}
public Color getCouleur(){
	return this.couleur;
}
public void Action(Joueur joueur){
	joueur.setScore(joueur.getScore()-4);
	System.out.println("sdf");	
}
}
