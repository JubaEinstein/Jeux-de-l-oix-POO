import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
//import java.util.TreeMap;


public class Grille implements Serializable{
	public Case parcours[]=new Case[100];
	public void nouvellePartie(){
		
		for (int i=0;i<100;i++){
			parcours[i]=new Case(i+1,Color.WHITE);//
		}
		///////////case Bonus////////////////////
		for (int i=0;i<5;i++){
			int ind=(int) (100*Math.random());
			while ((parcours[ind]  instanceof CaseBonus)||ind==0||ind==99){
				 ind=(int) (100*Math.random());
			}
		this.parcours[ind] =new CaseBonus(ind+1,Color.green);
		}
		///////////Case  Malus///////////////:
		for (int i=0;i<5;i++){
			int ind=(int) (100*Math.random());
			while ((parcours[ind]  instanceof CaseBonus)||(parcours[ind]  instanceof CaseMalus)||ind==0||ind==99){
				 ind=(int) (100*Math.random());
			}
			this.parcours[ind] =new CaseMalus(ind+1,Color.red);
		}
		////////////////Case saut////////////
		for (int i=0;i<5;i++){
			int ind=(int) (100*Math.random());
			while ((parcours[ind]  instanceof CaseSaut)||(parcours[ind]  instanceof CaseBonus)||(parcours[ind]  instanceof CaseMalus)||ind==0||ind==99){
				 ind=(int) (100*Math.random());
			}
			this.parcours[ind] =new CaseSaut(ind+1,Color.orange);
		}
		/////////////////case Question//////
		/////////////////case def////////
		for (int i=0;i<5;i++){
			int ind=(int) (100*Math.random());
			while ((parcours[ind]  instanceof CaseDef)||(parcours[ind]  instanceof CaseSaut)||(parcours[ind]  instanceof CaseBonus)||(parcours[ind]  instanceof CaseMalus)||ind==0||ind==99){
				 ind=(int) (100*Math.random());
			}
			this.parcours[ind] =new CaseDef(ind+1,Color.blue,"fraise");
		}
		//////////////case image//////////////
		for (int i=0;i<5;i++){
			int ind=(int) (100*Math.random());
			while ((parcours[ind]  instanceof CaseImage)||(parcours[ind]  instanceof CaseDef)||(parcours[ind]  instanceof CaseSaut)||(parcours[ind]  instanceof CaseBonus)||(parcours[ind]  instanceof CaseMalus)||ind==1||ind==99){
				 ind=(int) (100*Math.random());
			}
			this.parcours[ind] =new CaseImage(ind+1,Color.pink,"fraise");
		}
		parcours[0]=new Case(1,Color.yellow);
		parcours[99]=new Case(100,Color.black);
	}
	public void enregistrerPartie(){}
	public void ouvrirPartie(){}
	public void quitterPartie(){}
}
