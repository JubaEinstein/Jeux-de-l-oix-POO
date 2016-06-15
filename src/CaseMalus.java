import java.awt.Color;


public class CaseMalus extends Case {
	final int malus=10;
	final int nbCase=3;
	public CaseMalus(int numero, Color blue) {
		super(numero, blue);
	}
public void Action2(Joueur joueur){
	joueur.setScore(joueur.getScore()-this.malus);
	if (joueur.getScore()<0) joueur.setScore(0); 
	System.out.println("sdf");
}
}
