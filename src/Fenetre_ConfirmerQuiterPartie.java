import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Fenetre_ConfirmerQuiterPartie extends JFrame implements ActionListener
{
	private JButton bot_oui = new JButton("Oui");
	private JButton bot_non = new JButton("Non");
	private JLabel lab_question1= new JLabel("Voulez vous vraiment quitter la partie et retourner au menu principale?");
	private JLabel lab_question2= new JLabel("Toute progression non sauvegardée sera perdue.");
	private Fenetre_Jeu fenetre_jeu;
	
	public Fenetre_ConfirmerQuiterPartie(Fenetre_Jeu fen_jeu)
	{
		setTitle("Confirmation");
		setSize(440,180);
		setLocationRelativeTo(null);
		JPanel pan = new JPanel(); 
	    pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
	    lab_question1.setAlignmentX(Component.CENTER_ALIGNMENT);
	    pan.add(new JLabel(" "));
	    pan.add(lab_question1);
	    fenetre_jeu=fen_jeu;
	    lab_question2.setAlignmentX(Component.CENTER_ALIGNMENT);
	    pan.add(new JLabel(" "));
	    pan.add(lab_question2);
		JPanel pan_bot = new JPanel(); 
	    pan_bot.setLayout(new BoxLayout(pan_bot,BoxLayout.LINE_AXIS));
		bot_oui.addActionListener(this);
		bot_non.addActionListener(this);
		for (int i=1;i<=2;i++) pan.add(new JLabel(" "));
		pan_bot.add(bot_oui);
		pan_bot.add(new JLabel("         "));
		pan_bot.add(bot_non);
		pan_bot.setAlignmentX(Component.CENTER_ALIGNMENT);
		pan.add(pan_bot);
		setContentPane(pan);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ev) 
	{
		if (ev.getSource() == this.bot_oui)
		{
		    dispose();
		    fenetre_jeu.dispose();
		    Fenetre_Acceuil fen = new Fenetre_Acceuil();
		}
		else if (ev.getSource() == this.bot_non)
		{
			dispose();
			
		}	
	}
}