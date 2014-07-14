import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;

class StatusItem extends JPanel
{
	private String description;
	private String data;
	
	private final int WIDTH,HEIGHT;	
	private int x,y;
	
	StatusItem(String de, String da, int w, int h,int xP, int yP)
	{
		description = de;
		data = da;
		
		WIDTH = w;
		HEIGHT = h;
		x = xP;
		y = yP;
		
		setLayout(null);
		setOpaque(false);
		setSize(WIDTH,HEIGHT);
		setLocation(x,y);
		setVisible(true);		
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		super.paintComponent(g);
		String str = description+":      "+data;
		g2D.setPaint(Color.orange);
		g2D.drawString(str,10,10);
	}
	public void setDescription(String d)
	{
		description = d;
		repaint();
	}
	public void setData(String d)
	{
		data = d;
		repaint();
	}
}