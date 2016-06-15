import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;


public class Jeu implements Serializable{
	public TreeMap<String ,Integer> MesMeilleurScore= new TreeMap<String,Integer>();
	
	public TreeMap<String, Integer> getMap(){
		return this.MesMeilleurScore;
	}
	public void setMap(String joueur,int score){
		this.MesMeilleurScore.put(joueur,score);
	}
	public Joueur getKeyMap(){
		return (Joueur) this.MesMeilleurScore.keySet();
	}
	
}