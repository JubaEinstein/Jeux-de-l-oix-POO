
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Fenetre_NouvellePartie extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	private JButton bot_charger = new JButton("        Charger Partie        ");
	private JButton bot_nouvelle = new JButton("        Nouvelle Partie        ");
	private JButton bot_retour = new JButton("               Retour                ");
	public Fenetre_NouvellePartie()
	{
		setTitle("Nouvelle Partie");
		setSize(280,215);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel pan = new JPanel(); // Creer un panel pour la fenetre
        pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS)); // Choisir l'organisation du panel
		bot_nouvelle.addActionListener(this); // ecouteur 
       	bot_nouvelle.setAlignmentX(Component.CENTER_ALIGNMENT);
       	pan.add(new JLabel(" "));pan.add(new JLabel(" "));
       	pan.add(bot_nouvelle);
		bot_charger.addActionListener(this); // ecouteur 
		bot_charger.setAlignmentX(Component.CENTER_ALIGNMENT);
		pan.add(new JLabel(" "));
		pan.add(bot_charger);
		bot_retour.addActionListener(this); // ecouteur 
		bot_retour.setAlignmentX(Component.CENTER_ALIGNMENT);	
		pan.add(new JLabel(" "));
		pan.add(bot_retour);
		setContentPane(pan); // mettre le panel dans la fenetre		
		setVisible(true);
	}


	public void actionPerformed(ActionEvent ev) 
	{
		if (ev.getSource() == this.bot_nouvelle)
		{
		    dispose();
		    Fenetre_Inscription  fen = new Fenetre_Inscription();
		}
		else if (ev.getSource() == this.bot_retour)
		{
			dispose();
			Fenetre_Acceuil fen = new Fenetre_Acceuil();
		}
		else if (ev.getSource() == this.bot_charger)
		{
			JFileChooser choix = new JFileChooser();
			Component parent = null;
			int retour=choix.showOpenDialog(parent);
			if(retour==JFileChooser.APPROVE_OPTION){
			// un fichier a été choisi (sortie par OK)
			// nom du fichier  choisi 
				choix.getSelectedFile().getName();
		    // chemin absolu du fichier choisi
			choix.getSelectedFile().getAbsolutePath();
			Fenetre_Jeu fen = new Fenetre_Jeu(choix.getSelectedFile().getAbsolutePath(),true);
			dispose();
			}else;
		}	
	}
}	