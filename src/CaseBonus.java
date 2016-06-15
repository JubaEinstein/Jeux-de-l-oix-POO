import java.awt.Color;
import java.io.Serializable;


public class CaseBonus extends Case implements Serializable {
	final int bonus =10;
    final int nbCase=2;
    public CaseBonus(int d, Color green) {
		super(d, green);
	}   
public void Action2(Joueur joueur){
	System.out.println("sdf");	
	joueur.setScore(joueur.getScore()+this.bonus);
	System.out.println("sdf");	
}    
}
