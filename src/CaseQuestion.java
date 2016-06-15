import java.awt.Color;


public abstract class CaseQuestion extends Case{	
	protected String mot;
	public CaseQuestion(int numero, Color couleur,String mot) {
		super(numero, couleur);
		this.mot =mot ;
	}
    abstract void action(Joueur joueur);
}
