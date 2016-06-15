
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

class Fenetre_Question_Definition extends JFrame implements ActionListener {
	
	
	private JFormattedTextField champs[] = new JFormattedTextField[50];
	private JButton bot_repondre = new JButton("Repondre");
	
	private String[] tab_questions = new String[100];
	private String[] tab_reponses = new String[100];
	 
    private JOptionPane dialog = new JOptionPane();
	
	private JLabel question = new JLabel("");
	private String reponse="";
	
	private Fenetre_Jeu fen_jeu; 
	
	public Fenetre_Question_Definition (Fenetre_Jeu fenetre_jeu) throws ParseException
	{
		setTitle("Question");
		setSize(600,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fen_jeu=fenetre_jeu;
     		
		//Chargement du fichier
		int cpt=0;
		try
		   {
				BufferedReader buff = new BufferedReader(new FileReader("questions.txt"));
				try 
				{
					String line;
					while ((line = buff.readLine()) != null) 
					{
				    	tab_questions[cpt]=line;
				    	cpt++;
			        }
				}
				finally 
				{buff.close();}
			
			} 
		 catch (IOException ioe) 
		    {
			System.out.println("Erreur --" + ioe.toString());
		    }
		cpt=0;
		try
		   {
				BufferedReader buff = new BufferedReader(new FileReader("reponses.txt"));
				try 
				{
					String line;
					while ((line = buff.readLine()) != null) 
					{
						tab_reponses[cpt]=line;
						cpt++;
			        }
				}
				finally 
				{buff.close();}
			
			} 
		 catch (IOException ioe) 
		    {
			System.out.println("Erreur --" + ioe.toString());
		    }		
		//Fin Chargement du fichier
		
		//Random  
		   System.out.print(cpt);
		   int r = (int) ((cpt-1)*Math.random());
		   question.setText(tab_questions[r]);
		   reponse=tab_reponses[r];		
		//Fin Random
		
		JPanel pan_champs = new JPanel();
	    pan_champs.setLayout(new FlowLayout());
	    MaskFormatter formatter = new MaskFormatter("?");
	    Font f = new Font("Times New Roman", Font.PLAIN, 28);
	    for (int i=0;i<reponse.length();i++)
	    {
	    	champs[i] = new JFormattedTextField(formatter); 
	    	champs[i].setPreferredSize(new Dimension(40,40));
	    	champs[i].setFont(f);
	    	champs[i].setHorizontalAlignment(SwingConstants.CENTER);
			pan_champs.add(champs[i]);
	    }
	    

	    JPanel pan = new JPanel();
	    pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
	    question.setAlignmentX(Component.CENTER_ALIGNMENT);
	    pan.add(new JLabel(" "));
	    pan.add(question);
	    pan.add(new JLabel(" "));
	    pan.add(pan_champs);
	    bot_repondre.setAlignmentX(Component.CENTER_ALIGNMENT);
	    JPanel pan_repondre = new JPanel();
	    pan_repondre.setLayout(new FlowLayout());
	    pan_repondre.add(bot_repondre);
	    pan.add(pan_repondre);
	    bot_repondre.addActionListener(this);
	    setContentPane(pan);		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ev) {
		
		String reponse_joueur="";
		for (int i=0;i<reponse.length();i++) reponse_joueur=reponse_joueur+champs[i].getText();
		boolean correct=true;
		for (int i=0;i<reponse.length();i++) if ( !( (reponse_joueur.charAt(i)>=97 && reponse_joueur.charAt(i)<=122) || (reponse_joueur.charAt(i)>=65 && reponse_joueur.charAt(i)<=90) ) ) correct=false;
		if (correct)
		{	
			if (reponse_joueur.toUpperCase().equals(reponse)==true)
			{
				dialog.showMessageDialog(null, "Bonne Reponse! Vous gagnez 20 points, Veuillez avancez de 4 cases", "Bonne Reponse",JOptionPane.INFORMATION_MESSAGE);
				fen_jeu.miseAjourDefinition(true);
				dispose();
			}	
			else
			{
				dialog.showMessageDialog(null, "Mauvaise Reponse! Vous perdez 10 points", "Mauvaise Reponse",JOptionPane.INFORMATION_MESSAGE);
				fen_jeu.miseAjourDefinition(false);
				dispose();
			}
		}
		else 	dialog.showMessageDialog(null, "Veuillez saisir un caractére alphabétique dans chaque champs", "Erreur de saisie",JOptionPane.ERROR_MESSAGE);
	}
	

}
