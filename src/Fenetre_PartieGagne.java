import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


class Fenetre_PartieGagne extends JFrame implements ActionListener
{

	private JLabel bravo= new JLabel("");
	private JLabel score= new JLabel("");
	private JLabel meilleur_score= new JLabel("Votre meilleur score: ");
	private JLabel score_a_battre= new JLabel("Le score a battre est de:    Auteur: ");
	private JButton bot_nouvellepartie = new JButton("Nouvelle Partie");
	private JButton bot_quiter = new JButton("Quiter");
	private Fenetre_Jeu fen_jeu;
	private Joueur jr;
	private Jeu jeu=new  Jeu();
	private java.util.List<Scores> trie= new ArrayList<Scores>(); 
	ObjectInputStream inscore;
	ObjectOutputStream outscore;
    public Fenetre_PartieGagne(Fenetre_Jeu fenetre_jeu,Joueur joueur)
    {
    	try{
			inscore = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(new File("MeilleurScores.dat"))));
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
		setTitle("Partie Gagné");
		setSize(500,250);
		setLocationRelativeTo(null);
		fen_jeu=fenetre_jeu;
		jr=joueur;
		bravo.setText("Bravo "+jr.getNom()+", vous avez gagné la partie!");
		score.setText("Votre Score: "+jr.getScore());
		JPanel pan = new JPanel();
		pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
		bravo.setAlignmentX(Component.CENTER_ALIGNMENT);
		pan.add(new JLabel(" "));
		pan.add(bravo);
		pan.add(new JLabel(" "));
		score.setAlignmentX(Component.CENTER_ALIGNMENT);
		pan.add(score);
		pan.add(new JLabel(" "));
		meilleur_score.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//////////////////////////////////////////////////////////////////////////////////////////:::::
		//System.out.println(jeu.MesMeilleurScore.containsKey(jr.getNom()));
		if (jeu.MesMeilleurScore.containsKey(jr.getNom())){
			if (jeu.MesMeilleurScore.get(jr.getNom())>jr.getScore())
			meilleur_score.setText(String.valueOf("Votre meilleur score: "+jeu.MesMeilleurScore.get(jr.getNom())));
			else
				meilleur_score.setText("Votre meilleur score: "+String.valueOf(jr.getScore()));	
		}else{
			meilleur_score.setText("Votre meilleur score: "+String.valueOf(jr.getScore()));
			
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////::::
		//System.out.println(jeu.MesMeilleurScore.containsKey(jr.getNom()));
		if (jeu.MesMeilleurScore.containsKey(jr.getNom())){
			//System.out.println("msdfsdf");
			if ((jeu.MesMeilleurScore.get(jr.getNom())<jr.getScore())){
				jeu.MesMeilleurScore.remove(jr.getNom());
				jeu.MesMeilleurScore.put(jr.getNom(),jr.getScore() );
		}
		}else{
			 jeu.MesMeilleurScore.put(jr.getNom(),jr.getScore() );
		}
		int i=0;
		Scores sco ;
		for(String key: jeu.MesMeilleurScore.keySet()){
			sco=new Scores();
		sco.setNom(key);
		sco.setScore(jeu.MesMeilleurScore.get(key));
		
		trie.add(sco);
			 
		System.out.println(trie.get(i).getNom());
		i++;
		}
		Collections.sort(trie);
		for(i=0;i<trie.size();i++) System.out.println(i+"   "+trie.get(i).getNom());
		
		score_a_battre.setText("Le score a battre est de:"+trie.get(0).getScore()+" Auteur: "+trie.get(0).getNom());
		
		try {
			
			outscore = new ObjectOutputStream(
				new BufferedOutputStream(
				new FileOutputStream(
				new File("MeilleurScores.dat"))));
			outscore.writeObject(jeu);
			outscore.close();
			}
	    catch (FileNotFoundException exep) {
			exep.printStackTrace();
		  } 
		catch (IOException exep) {
			exep.printStackTrace();
		}
		
		pan.add(meilleur_score);
		pan.add(new JLabel(" "));
		score_a_battre.setAlignmentX(Component.CENTER_ALIGNMENT);
		pan.add(score_a_battre);
		pan.add(new JLabel(" "));pan.add(new JLabel(" "));
		JPanel pan_bot = new JPanel();
		pan_bot.setLayout(new BoxLayout(pan_bot,BoxLayout.LINE_AXIS));
		pan_bot.add(bot_nouvellepartie);
		pan_bot.add(new JLabel("          "));
		pan_bot.add(bot_quiter);
		bot_quiter.addActionListener(this);
		bot_nouvellepartie.addActionListener(this);
		pan.add(pan_bot);
		setContentPane(pan);
		setVisible(true);
    }

    public void actionPerformed(ActionEvent ev) 
	{
		if (ev.getSource() == this.bot_nouvellepartie)
		{
		   dispose();
		   fen_jeu.dispose();
		   Fenetre_Jeu fen = new Fenetre_Jeu(jr.getNom(),false);	
		}
		else if (ev.getSource() == this.bot_quiter)
		{
		   dispose();
		   fen_jeu.dispose();
		   Fenetre_Acceuil fen = new Fenetre_Acceuil();	
		}	
   }
}

