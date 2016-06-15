import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Fenetre_Inscription extends JFrame implements ActionListener
{
	private JTextField champ= new JTextField("");
	private JLabel lab_question= new JLabel("Veuillez entrer votre nom:");
	private JButton bot_ok = new JButton("OK");
	private JButton bot_annuler = new JButton("Annuler");
	private JOptionPane dialog = new JOptionPane();
	
	public Fenetre_Inscription()
	{
		setTitle("Inscription");
		setSize(400,170);
		setLocationRelativeTo(null);
		JPanel pan = new JPanel();
		pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
		JPanel pan_champ = new JPanel();
		pan_champ.setLayout(new FlowLayout());
		pan_champ.add(lab_question);
		champ.setPreferredSize(new Dimension(190,20));
		pan_champ.add(champ);
		bot_ok.addActionListener(this);
		bot_annuler.addActionListener(this);
		pan.add(new JLabel(" "));
		pan.add(pan_champ);
		JPanel pan_boutons = new JPanel();
		pan_boutons.setLayout(new FlowLayout());
		pan_boutons.add(bot_ok);
		pan_boutons.add(new JLabel(" "));pan_boutons.add(new JLabel(" "));
		pan_boutons.add(bot_annuler);	
		pan.add(pan_boutons);
		setContentPane(pan);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ev) 
	{
		if (ev.getSource() == this.bot_ok)
		{
			if (champ.getText().length()>0)
			{
				boolean correct=true;
				for (int i=0;i<champ.getText().length();i++)
				{
					if ( !( (champ.getText().charAt(i)>=97 && champ.getText().charAt(i)<=122) || (champ.getText().charAt(i)>=65 && champ.getText().charAt(i)<=90) ) ) correct=false;
				}	
				if (correct && champ.getText().length()<=15)
				{
					Fenetre_Jeu fen = new Fenetre_Jeu(champ.getText(),false);
					dispose();
				}
				else dialog.showMessageDialog(null, "Nom incorrect: Le nom ne doit contenir que des alphabets, et sa taille doit etre compris entre 1 et 15 caractéres", "Erreur de saisie",JOptionPane.ERROR_MESSAGE);
			}
			else dialog.showMessageDialog(null, "Nom incorrect: Le nom ne doit contenir que des alphabets et au moins un caractére", "Erreur de saisie",JOptionPane.ERROR_MESSAGE);
		    
		}
		else if (ev.getSource() == this.bot_annuler)
		{
			dispose();
			Fenetre_NouvellePartie fen = new Fenetre_NouvellePartie();
		}	
	}
}