import java.io.Serializable;

public class Joueur implements Serializable{
   private int score;
   private String nom;
   private  int position;
 public Joueur(int score, String nom,int position){
	 this.score=score;
	 this.nom=nom;
	 this.position=position;
 }
public int getScore(){
	return score;
}
public int getPostion(){
	return this.position;
}
public void setPosition(int pos){
	this.position=pos;
}
public String getNom(){
	return nom;
}
public void setNom(String nom){
	this.nom=nom;
}
public void setScore(int score){
	this.score=score;
	if (this.score<0) this.score=0;
}
}
