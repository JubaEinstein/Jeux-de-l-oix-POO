import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class Fenetre_MeilleursScores extends JFrame implements ActionListener
{
	private JLabel score = new JLabel("");
	ObjectInputStream inscore;
	private Jeu jeu;
	private java.util.List<Scores> trie= new ArrayList<Scores>(); 
	public Fenetre_MeilleursScores()
	{
		try{
			inscore = new ObjectInputStream(
					new BufferedInputStream(
					new FileInputStream(
					new File("MeilleurScores.dat"))));
					try {				
						jeu=((Jeu)inscore.readObject());
					} catch (ClassNotFoundException exep) {
						
						exep.printStackTrace();
					
				    }
					inscore.close();
			}
		    catch (FileNotFoundException e) {
		    	
			             e.printStackTrace();
			             }
			catch (IOException e) {
				
			          e.printStackTrace();
			 }
		
		setTitle("Meilleurs Scores");
		setSize(500,400);
		setLocationRelativeTo(null);
		JPanel pan = new JPanel();
		pan.setLayout(new FlowLayout());
		JButton bot_retour = new JButton("Retour");
		bot_retour.addActionListener(this);
		pan.add(bot_retour);
		add(pan,BorderLayout.SOUTH);
		setVisible(true);
		//Les données du tableau
		Object[][] data = new Object[100][3] ;
		
		Scores sco ;
		for(String key: jeu.MesMeilleurScore.keySet()){
			sco = new Scores();
			sco.setNom(key);
			sco.setScore(jeu.MesMeilleurScore.get(key));
			//System.out.println(sco.getNom());
			trie.add(sco);
			}
			Collections.sort(trie);
		
		//Les titres des colonnes
		String title[] = {"N°","Nom Du Joueur", "Score"};
		JTable tableau = new JTable(data, title);
		//Nous ajoutons notre tableau à notre contentPane dans un
		//scroll
          
		for(int i=0;i<trie.size();i++){
			
	               data[i][0]=String.valueOf(i+1);
		           data[i][1]=trie.get(i).getNom();
		           data[i][2]=String.valueOf(trie.get(i).getScore());
		           
		           }
	        
		/*Set <Joueur> key= jeu.MesMeilleurScore.keySet();
		Iterator<Joueur> it= key.iterator();
		System.out.println(String.valueOf(i+1));
		while (it.hasNext()){
			
	           System.out.println(String.valueOf(i+1));
			   data[i][0]=String.valueOf(i+1);
	           data[i][1]=it.next().getNom();
	           data[i][2]=String.valueOf(jeu.MesMeilleurScore.get(it.next()));
	           i++;
	        }*/
		//Sinon les titres des colonnes ne s'afficheront pas !
		this.getContentPane().add(new JScrollPane(tableau));
		}
	
	
	public void actionPerformed(ActionEvent ev) 
	{
		dispose();
		Fenetre_Acceuil fen = new Fenetre_Acceuil();
	}
}