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

class Fenetre_ConfirmerQuiter extends JFrame implements ActionListener
{
	private JButton bot_oui = new JButton("Oui");
	private JButton bot_non = new JButton("Non");
	private JLabel lab_question= new JLabel("Voulez vous vraiment quitter?");
	public Fenetre_ConfirmerQuiter()
	{
		setTitle("Confirmation");
		setSize(310,150);
		setLocationRelativeTo(null);
		Panneau pan = new Panneau(); 
	    pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
	    lab_question.setAlignmentX(Component.CENTER_ALIGNMENT);
	    pan.add(new JLabel(" "));
	    pan.add(lab_question);
		Panneau pan_bot = new Panneau(); 
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
		}
		else if (ev.getSource() == this.bot_non)
		{
			dispose();
			Fenetre_Acceuil fen = new Fenetre_Acceuil();
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
}