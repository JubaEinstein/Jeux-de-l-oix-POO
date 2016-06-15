import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre_Question_Image extends JFrame
{
	private JLabel imagev=new JLabel();
	private JLabel definition=new JLabel("");
	private JLabel indication=new JLabel("Cliquez sur l'image correspandant au mot");
	private String[] reponse_images = new String[100];
	private ImageIcon[] imgs = new ImageIcon[3];
	private JLabel[] image =new JLabel[3];
	private JOptionPane dialog = new JOptionPane();
	private Fenetre_Jeu fen_jeu; 
	public Fenetre_Question_Image(Fenetre_Jeu fenetre_jeu)
	{
		setTitle("Question");
		setSize(900,370);
		setLocationRelativeTo(null);
		fen_jeu=fenetre_jeu;
		
		//Chargement du fichier
		int cpt=0;
		try
		   {
				BufferedReader buff = new BufferedReader(new FileReader("images.txt"));
				try 
				{
					String line;
					while ((line = buff.readLine()) != null) 
					{
				    	reponse_images[cpt]=line;
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
		

		JPanel pan_images = new JPanel();
	    pan_images.setLayout(new FlowLayout());
	   
	   int r = (int)(cpt*Math.random());
	   if (r==0) r=1;
	   definition.setText(reponse_images[r-1]);
	   String imgalt=String.valueOf(r); 
	   int r1 = (int)(3*Math.random());
	   if (r1==0)
	   {
		   ImageIcon imgv = new ImageIcon("Images/"+imgalt+"/v.png");
		   imagev.setIcon(imgv);
		   pan_images.add(imagev);
	   }
	   for (int i=0;i<3;i++)
	   {
		   imgs[i] = new ImageIcon("Images/"+imgalt+"/"+String.valueOf(i+1)+".png");
		   image[i] = new JLabel();
		   image[i].setIcon(imgs[i]);
		   pan_images.add(image[i]);
		   if (r1==i+1)
		   {
			   ImageIcon imgv = new ImageIcon("Images/"+imgalt+"/v.png");
			   imagev.setIcon(imgv);
			   pan_images.add(imagev);
		   }
	   }
	   	    
	    JPanel pan = new JPanel();
	    pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
	    definition.setAlignmentX(Component.CENTER_ALIGNMENT);	
	    pan.add(new JLabel(" "));
		   Font f = new Font("Times New Roman", Font.PLAIN, 20);
		   definition.setFont(f);
	    pan.add(definition);
	    pan.add(new JLabel(" "));
	    pan.add(pan_images);
	    JPanel pan_indication = new JPanel();
	    pan_indication.setLayout(new FlowLayout());
	    pan_indication.add(indication);
	    pan_indication.setAlignmentX(Component.CENTER_ALIGNMENT);
	    pan.add(pan_indication);
	    setContentPane(pan);
	    
	    imagev.addMouseListener(new MouseAdapter()   {   

	        public void mouseReleased(MouseEvent e)   
	        {   
	        	dialog.showMessageDialog(null, "Bonne Reponse! Vous gagnez 10 points, Veuillez avancez de 2 cases", "Bonne Reponse",JOptionPane.INFORMATION_MESSAGE);
	        	fen_jeu.miseAjourImage(true);
	        	dispose();
	     }   
	      }); 
	    
	    for (int i=0;i<3;i++)
	    {
	    image[i].addMouseListener(new MouseAdapter()   {   

	        public void mouseReleased(MouseEvent e)   
	        {   
	        	dialog.showMessageDialog(null, "Mauvaise Reponse!","Mauvaise Reponse",JOptionPane.INFORMATION_MESSAGE);
	        	fen_jeu.miseAjourImage(false);
	        	dispose();
	     }   
	      }); 
	    }
	    
	    setVisible(true);
	}



}
