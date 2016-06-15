
public class Scores implements Comparable<Scores> {
	private int score;
	private String nom;
	public int getScore(){
		return this.score;
	}
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom ){
		this.nom=nom;
	}
	public void setScore(int score ){
		this.score=score;
	}
	


	@Override
	public int compareTo(Scores o) {
		return o.getScore()-score ;			
			}
	
}

