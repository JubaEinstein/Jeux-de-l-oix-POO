
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Component;

class Fenetre_Acceuil extends JFrame implements ActionListener
{
	private JButton bot_jouer = new JButton("                       Jouer                      "); // creer bouton jouer
	private JButton bot_score = new JButton("           Meilleurs Scores             "); // creer bouton meilleur score
	private JButton bot_quiter = new JButton("                       Quiter                      "); // creer bouton quiter	 
	public Fenetre_Acceuil ()
	{
		setTitle("Acceuil");
		setSize(340,215);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Panneau3 pan = new Panneau3(); // Creer un panel pour la fenetre
        pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS)); // Choisir l'organisation du panel
		bot_jouer.addActionListener(this); // ecouteur 
       	bot_jouer.setAlignmentX(Component.CENTER_ALIGNMENT);
       	pan.add(new JLabel(" "));pan.add(new JLabel(" "));
       	pan.add(bot_jouer);
		bot_score.addActionListener(this); // ecouteur 
		bot_score.setAlignmentX(Component.CENTER_ALIGNMENT);
		pan.add(new JLabel(" "));
		pan.add(bot_score);
		bot_quiter.addActionListener(this); // ecouteur 
		bot_quiter.setAlignmentX(Component.CENTER_ALIGNMENT);	
		pan.add(new JLabel(" "));
		pan.add(bot_quiter);
		setContentPane(pan); // mettre le panel dans la fenetre		
		setVisible(true);	
	}


	public void actionPerformed(ActionEvent ev) 
        {
		if (ev.getSource() == this.bot_quiter) 
			{
			 dispose();
			 Fenetre_ConfirmerQuiter fen = new Fenetre_ConfirmerQuiter();
			}
		   else if (ev.getSource() == this.bot_jouer)
		   {
			   dispose();
			   Fenetre_NouvellePartie fen = new Fenetre_NouvellePartie();
		   }
		   else if (ev.getSource() == this.bot_score)
		   {
			   dispose();
			   Fenetre_MeilleursScores fen = new Fenetre_MeilleursScores();
		   }
        }
}
class Panneau3 extends JPanel {
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