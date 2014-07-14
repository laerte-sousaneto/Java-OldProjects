import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class Selector extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	
	Selector(int width, int height)
	{
		setSize(width,height);
		setLocation(10,10);
		setVisible(true);
	}
	public void setImage(Image i)
	{
		img = i;
		repaint();
	}
	public void paintComponent(Graphics g)
	{
		if(img != null)
		{
			g.drawImage(img,0,0,this);
		}
		else
		{
			System.out.println("Image is null.");
		}
	}
}
