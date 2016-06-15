import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import java.io.Serializable;
import java.text.ParseException;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

class Fenetre_Jeu extends JFrame implements ActionListener,Serializable
{
	//
	private Fenetre_Jeu fen_jeu_tmp;
	//
	private ObjectInputStream in;
	private ObjectOutputStream out;	
	private String chemin_sauvegarde;
	private Grille grille =new Grille();
	private JButton tabBouttons[] =new JButton[100];
	private JButton boutonDé = new JButton("Lancer Dés");
	private JLabel information= new JLabel("Partie en cours: Veuillez lancez les dés");
	private JLabel de1= new JLabel();
	private JLabel de2= new JLabel();
	private JOptionPane dialog = new JOptionPane();
	private Joueur joueur =new Joueur(0,"",1);
	private Dé d1=new Dé();
	private Dé d2=new Dé();
	private JMenuBar menu_bar = new JMenuBar();
	private JMenu menu_partie = new JMenu("Partie");
	private JMenu menu_interrogation = new JMenu("?");
	private JMenuItem menu_aide = new JMenuItem("Aide");
	private JMenuItem menu_nouvellepartie = new JMenuItem("Nouvelle partie");
	private JMenuItem menu_sauvegarder = new JMenuItem("Sauvegarder");
	private JMenuItem menu_quiter = new JMenuItem("Quiter la partie");
	private int deplacement=0;
	private int position_future=0;
	private boolean peuxlancer=true;
	private boolean peuxjouer=true;
	private JLabel nomjoueurcourant;
	private JLabel position = new JLabel("Position: 1");
	private JLabel score = new JLabel("Score: 0");
    private Jeu jeu = new Jeu();
    private Partie partie;
    public Jeu getJeu()
    {
        return this.jeu;
    }
	public Fenetre_Jeu(String nomjoueur,boolean chargement) 
	{
		//
		fen_jeu_tmp=this;
		//
		if (!chargement)
		{
		grille.nouvellePartie();
		joueur.setNom(nomjoueur);
		}
		else{
			try{

				
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(nomjoueur))));
					try {
					partie=((Partie)in.readObject());
					joueur=partie.getJoueur();
					grille=partie.getGrille();
					} catch (ClassNotFoundException exep) {
					exep.printStackTrace();
				    }
					in.close();
			}
		    catch (FileNotFoundException e) {
			             e.printStackTrace();
			             }
			catch (IOException e) {
			          e.printStackTrace();
			        }
		}
		 
		setTitle("Jeu");
		setSize(1280,750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nomjoueurcourant = new JLabel("Nom du joueur: "+joueur.getNom()+"       ");
		position.setText("Position: "+joueur.getPostion());
		score.setText("Score: "+joueur.getScore());
		ImageIcon imgde1 = new ImageIcon("des/de0.png");
		ImageIcon imgde2 = new ImageIcon("des/de0.png");
		de1.setIcon(imgde1);
		de2.setIcon(imgde2);
		Panneau pan = new Panneau();
		pan.setSize(1280,700);
		boutonDé.setBackground(Color.white);
		//On définit le layout manager
		pan.setLayout(new GridBagLayout());
		//L'objet servant à positionner les composants
		GridBagConstraints gbc = new GridBagConstraints();
		//On positionne la case de départ du composant
		gbc.gridx = 2;
		gbc.gridy = 0;
		//La taille en hauteur et en largeur
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		int i;
		int cpt=0;
		for (i=0;i<19;i++)
		{	
		JButton bot;
		cpt++;
		if (i<9) bot=new JButton("0"+String.valueOf(cpt));
		else bot=new JButton(String.valueOf(cpt));
		bot.setPreferredSize(new Dimension(80,40));
		bot.setBackground(grille.parcours[cpt-1].getCouleur());
		tabBouttons[cpt-1]=bot;
		gbc.gridx = i+3;
		pan.add(bot, gbc);
		}
		for (i=1;i<14;i++)
		{	
		cpt++;
		JButton bot=new JButton(String.valueOf(cpt));
		bot.setPreferredSize(new Dimension(80,40));
		bot.setBackground(grille.parcours[cpt-1].getCouleur());
		tabBouttons[cpt-1]=bot;
		gbc.gridy = i;
		pan.add(bot, gbc);
		}
		for (i=1;i<17;i++)
		{	
		   cpt++;
		   JButton bot=new JButton(String.valueOf(cpt));
		   bot.setPreferredSize(new Dimension(80,40));
		   bot.setBackground(grille.parcours[cpt-1].getCouleur());
		   tabBouttons[cpt-1]=bot;
		   gbc.gridx = 21-i;
		   pan.add(bot, gbc);
		}
		for (i=1;i<11;i++)
		{	
		   cpt++;
		   JButton bot=new JButton(String.valueOf(cpt));
		   bot.setPreferredSize(new Dimension(80,40));
		   bot.setBackground(grille.parcours[cpt-1].getCouleur());
			tabBouttons[cpt-1]=bot;
		   gbc.gridy = 13-i;
		   pan.add(bot, gbc);
		}
		for (i=3;i<16;i++)
		{
			cpt++;
			JButton bot=new JButton(String.valueOf(cpt));
			gbc.gridx=i+3;
			bot.setPreferredSize(new Dimension(80,40));
			bot.setBackground(grille.parcours[cpt-1].getCouleur());
			tabBouttons[cpt-1]=bot;
			pan.add(bot, gbc);
		}
		for (i=4;i<11;i++)
		{
			cpt++;
			JButton bot=new JButton(String.valueOf(cpt));
			gbc.gridy=i;
			bot.setPreferredSize(new Dimension(80,40));
			bot.setBackground(grille.parcours[cpt-1].getCouleur());
			tabBouttons[cpt-1]=bot;
			pan.add(bot, gbc);
		}
		for (i=1;i<=10;i++)
		{
			cpt++;
			JButton bot=new JButton(String.valueOf(cpt));
			gbc.gridx=18-i;
			bot.setPreferredSize(new Dimension(80,40));
			bot.setBackground(grille.parcours[cpt-1].getCouleur());
			tabBouttons[cpt-1]=bot;
			pan.add(bot, gbc);
		}
		for (i=1;i<=4;i++)
		{
			cpt++;
			JButton bot=new JButton(String.valueOf(cpt));
			gbc.gridy=10-i;
			bot.setPreferredSize(new Dimension(80,40));
			bot.setBackground(grille.parcours[cpt-1].getCouleur());
			tabBouttons[cpt-1]=bot;
			pan.add(bot, gbc);
		}
		for (i=6;i<13;i++)
		{
			cpt++;
			JButton bot=new JButton(String.valueOf(cpt));
			gbc.gridx=i+3;
			bot.setPreferredSize(new Dimension(80,40));
			bot.setBackground(grille.parcours[cpt-1].getCouleur());
			tabBouttons[cpt-1]=bot;
			pan.add(bot, gbc);
		}

			cpt++;
			gbc.gridx=14;
			gbc.gridwidth = 3;
			JButton bot=new JButton("100");
			gbc.gridy=7;
			bot.setPreferredSize(new Dimension(80,40));
			bot.setBackground(grille.parcours[cpt-1].getCouleur());
			tabBouttons[cpt-1]=bot;
			pan.add(bot, gbc);
		
		for (i=0;i<100;i++)
		{
			tabBouttons[i].addActionListener(this);
		}	

		JPanel Pan_Des = new JPanel();
        Pan_Des.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=11;
        pan.add(boutonDé,gbc);
        boutonDé.addActionListener(this);
        gbc.gridy=16;
        pan.add(de1,gbc);
        gbc.gridy=17;
        pan.add(de2,gbc);
        menu_partie.add(menu_nouvellepartie);
        menu_partie.add(menu_sauvegarder);
        menu_partie.add(menu_quiter);
        menu_bar.add(menu_partie);
        menu_interrogation.add(menu_aide);
        menu_bar.add(menu_interrogation);
        add(menu_bar,BorderLayout.NORTH);
		add(pan,BorderLayout.CENTER);	
		Panneau2 pan_infos = new Paneau2();
		pan_infos.setLayout(new BoxLayout(pan_infos, BoxLayout.PAGE_AXIS));
		//Espacement
		pan_infos.add(new JLabel(" "));
		pan_infos.add(nomjoueurcourant);
		pan_infos.add(new JLabel(" "));
		pan_infos.add(position);
		pan_infos.add(new JLabel(" "));
		pan_infos.add(score);	
		add(pan_infos,BorderLayout.EAST);
		add(information,BorderLayout.SOUTH);
		
		menu_quiter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				Fenetre_ConfirmerQuiterPartie fen = new Fenetre_ConfirmerQuiterPartie(fen_jeu_tmp);
			}
        });
		menu_nouvellepartie.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				Fenetre_Jeu fen = new Fenetre_Jeu(joueur.getNom(),false);
			}
        });
		menu_sauvegarder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser filechoose = new JFileChooser();
				filechoose.setCurrentDirectory(new File("."));  /* ouvrir la boite de dialogue dans répertoire courant */
				filechoose.setDialogTitle("Sauvegarder la partie"); /* nom de la boite de dialogue */ 
				filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); /* pour afficher seulement les répertoires */		 
				String approve = new String("Enregistrer"); /* Le bouton pour valider l’enregistrement portera la mention Enregistrer */
				int resultatEnregistrer = filechoose.showDialog(filechoose, approve); 
				if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) /* Si l’utilisateur clique sur le bouton Enregistrer */
				{ 
				    chemin_sauvegarde = filechoose.getSelectedFile().getAbsolutePath()+"\\"; /* pour avoir le chemin absolu */
				}
				Partie partie= new Partie(joueur,grille);
				try {
					out = new ObjectOutputStream(
					new BufferedOutputStream(
					new FileOutputStream(
					new File(chemin_sauvegarde+joueur.getNom()+".dat"))));
					out.writeObject(partie);
					out.close();
					}
			    catch (FileNotFoundException exep) {
					exep.printStackTrace();
				  } 
				catch (IOException exep) {
					exep.printStackTrace();
				}
				
			}
        });
		setVisible(true);
	}
	
	public void miseAjourDefinition(boolean gagner)
	{
		if (gagner==true)
		{
			joueur.setScore(joueur.getScore()+20);
			score.setText("Score: "+String.valueOf(joueur.getScore()));
			position_future=position_future+4;
			if (position_future>100) position_future=100-(position_future-100);
			information.setText("Case question: vous gagnez 20 points, Veuillez avancez de 4 cases");
		}
		else
		{
			joueur.setScore(joueur.getScore()-10);
			score.setText("Score: "+String.valueOf(joueur.getScore()));
			peuxlancer=true;
			boutonDé.setEnabled(true);
			information.setText("Case question: vous perdez 10 points, Veuillez lancez les dés");
		}
		peuxjouer=true;
	}
	public void miseAjourImage(boolean gagner)
	{
		if (gagner==true)
		{
			joueur.setScore(joueur.getScore()+10);
			score.setText("Score: "+String.valueOf(joueur.getScore()));
			position_future=position_future+2;
			if (position_future>100) position_future=100-(position_future-100);
			information.setText("Case question: vous gagnez 10 points, Veuillez avancez de 2 cases");
		}
		else
		{
			peuxlancer=true;
			boutonDé.setEnabled(true);
			information.setText("Case question: Vous avez perdu, Veuillez lancez les dés");
		}
		peuxjouer=true;
	}
	public void actionPerformed(ActionEvent ev) 
	{
		JButton bot = new JButton();
		bot = (JButton) ev.getSource();
		ImageIcon imgde1;
		ImageIcon imgde2;
		if (ev.getSource()== boutonDé)
		{
			if (peuxlancer==true)
			{	
			information.setText("Partie en cours: Veuillez vous deplacez en fonction du resultat obtenue par les dés");
			d1.setValeur();
			d2.setValeur();	

			///////////a verifier//////////////
			for (int i=1;i<=6;i++){
				 imgde1 = new ImageIcon("des/de"+String.valueOf(i)+".png");
			     imgde2 = new ImageIcon("des/de"+String.valueOf((7-i))+".png");												
			}
			///////////a verifier//////////////
			imgde1 = new ImageIcon("des/de"+d1.getValeur()+".png");
			imgde2 = new ImageIcon("des/de"+d2.getValeur()+".png");
			de1.setIcon(imgde1);
			de2.setIcon(imgde2);
			deplacement=d1.getValeur()+d2.getValeur();
			position_future = joueur.getPostion()+deplacement;
			if (position_future>100) position_future=100-(position_future-100);
			peuxlancer=false;
			boutonDé.setEnabled(false);
			}
		}
		else
		{	

		 if (!peuxlancer && peuxjouer)
		 { 
			 if ( Integer.parseInt(bot.getText())!=position_future )
			     information.setText("Mauvais deplacement, Vous devez vous deplacez a la case: "+position_future);
		   else
		   {
			imgde1 = new ImageIcon("des/de0.png");
			imgde2 = new ImageIcon("des/de0.png");
			de1.setIcon(imgde1);
			de2.setIcon(imgde2);   
			if ( grille.parcours[position_future-1].getCouleur().equals( Color.WHITE) || grille.parcours[position_future-1].getCouleur().equals(Color.YELLOW))
			{  
				peuxlancer=true;
				boutonDé.setEnabled(true);
				joueur.setPosition(position_future);
				information.setText("Partie en cours: Veuillez lancez les dés");
				position.setText("Position: "+String.valueOf(position_future));
			}
			else if ( grille.parcours[position_future-1].getCouleur().equals(Color.GREEN) )
			{
				System.out.println(grille.parcours[position_future-1] instanceof CaseBonus);
			    ((CaseBonus) grille.parcours[position_future-1]).Action2(joueur);
				joueur.setPosition(position_future);
				score.setText("Score: "+String.valueOf(joueur.getScore()));
				information.setText("Case Bonus: vous gagnez 10 points, Veuillez avancez de deux cases");
				dialog.showMessageDialog(null, "Case Bonus: vous gagnez 10 points, Veuillez avancez de deux cases", "Case Bonus",JOptionPane.INFORMATION_MESSAGE);
				position.setText("Position: "+String.valueOf(position_future));
				position_future=position_future+2;
				if (position_future>100) position_future=100-(position_future-100);
			}
			else if ( grille.parcours[position_future-1].getCouleur().equals(Color.RED) )
			{
				((CaseMalus) grille.parcours[position_future-1]).Action2(joueur);
				joueur.setPosition(position_future);
				score.setText("Score: "+String.valueOf(joueur.getScore()));
				information.setText("Case Malus: vous perdez 10 points, Veuillez reculez de deux cases");
				dialog.showMessageDialog(null, "Case Malus: vous perdez 10 points, Veuillez reculez de deux cases", "Case Malus",JOptionPane.INFORMATION_MESSAGE);
				position.setText("Position: "+String.valueOf(position_future));
				position_future=position_future-2;	
			}
			else if ( grille.parcours[position_future-1].getCouleur().equals(Color.ORANGE) )
			{
				joueur.setPosition(position_future);
				position.setText("Position: "+String.valueOf(position_future));
				int posSaut = (int) (30*Math.random());
				while (posSaut==0)  posSaut = (int) (30*Math.random());
				if (Math.random()<0.5) 
				{
					position_future=posSaut+joueur.getPostion();
					information.setText("Case Saut: Veuillez avancez de "+posSaut+" cases");
					dialog.showMessageDialog(null,"Case Saut: Veuillez avancez de "+posSaut+" cases", "Case Saut",JOptionPane.INFORMATION_MESSAGE);
					if (position_future>100) position_future=100-(position_future-100);
				}
				else{
					position_future=joueur.getPostion()-posSaut;
					information.setText("Case Saut: Veuillez reculez de "+posSaut+" cases");
					dialog.showMessageDialog(null,"Case Saut: Veuillez reculez de "+posSaut+" cases", "Case Saut",JOptionPane.INFORMATION_MESSAGE);
					if (position_future<1) position_future=1;
					
				}
				
			}
			else if ( grille.parcours[position_future-1].getCouleur().equals(Color.BLUE) )
			{
				peuxjouer=false;
				joueur.setPosition(position_future);
				position.setText("Position: "+String.valueOf(position_future));
				information.setText("Case question");
				try {
					Fenetre_Question_Definition fen = new Fenetre_Question_Definition(this);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else if ( grille.parcours[position_future-1].getCouleur().equals(Color.PINK) )
			{
				peuxjouer=false;
				joueur.setPosition(position_future);
				position.setText("Position: "+String.valueOf(position_future));
				information.setText("Case question");
				Fenetre_Question_Image fen = new Fenetre_Question_Image(this);
			}
			else if ( grille.parcours[position_future-1].getCouleur().equals(Color.BLACK) )
			{
				peuxjouer=false;
				joueur.setPosition(position_future);
				position.setText("Position: "+String.valueOf(position_future));
				information.setText("Partie gagnée");
				Fenetre_PartieGagne fen = new Fenetre_PartieGagne(this,joueur);
			}	
		   }
		 }
		
		}
	}
}
class Panneau extends JPanel {
	public void paintComponent(Graphics g){
		try {
				Image img = ImageIO.read(new File("C:/Users/msia/Desktop/Test/TP/DECO_ColorSide_Dark.png"));
	//g.drawImage(img, 0, 0, this);
	//Pour une image de fond
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
}
class Panneau2 extends JPanel {
	public void paintComponent(Graphics g){
		try {
				Image img = ImageIO.read(new File("C:/Users/msia/Pictures/Sample Pictures/4_ianrdjohnson_derelicthouseburrasouthaustralia2.png"));
	//g.drawImage(img, 0, 0, this);
	//Pour une image de fond
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
}