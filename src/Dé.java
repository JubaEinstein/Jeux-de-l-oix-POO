
public class Dé {
	private String[] url;
	private int valeur;
public void setValeur(){
	
	this.valeur=(int)(6*Math.random());
	while(this.valeur==0){
		this.valeur=(int)(7*Math.random());
	}
}
public int getValeur(){
	return this.valeur;
}

}
