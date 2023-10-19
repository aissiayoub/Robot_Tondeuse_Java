package New;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import OLD.Plateau;

public class Pan extends JPanel {

	private Plateau plateau;
	private Image fond1;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(116, 150, 0));
		
		try {
			fond1 = ImageIO.read(new File("gazon.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g.setColor(Color.YELLOW);
		
		for(int i=0; i< plateau.getLon(); i+=20) {
			for(int j=0 ; j< plateau.getLar(); j+=20) {
				g.drawImage(fond1,i,j,this);
			}
			
		}
		
		g.fillOval(plateau.getX(), plateau.getY(), 10, 10);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 25; j++)
				if (plateau.getT()[i][j] == 1)
					g.fillOval(20 * j + 10, 20 * i + 10, 5, 5); // Si une case a �t� visit�e, dessiner un rond
																// jaune

	}
}
