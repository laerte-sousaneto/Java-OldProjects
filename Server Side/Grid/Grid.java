import java.awt.*;
import javax.swing.*;

class Grid extends JFrame 
{

	private final int WIDTH;
	private final int HEIGHT;
	private JLabel label;
	private int x, y;
	
	Grid()
	{
		WIDTH = 500;
		HEIGHT = 500;
		
		label = new JLabel("Test");
		x = 100;
		y = 100;
		label.setSize(40,20);
		label.setBackground(Color.red);
		label.setOpaque(true);
		label.setLocation(x,y);
		
		this.add(label);
		
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void moveR()
	{
		label.setLocation(label.getX()+10,label.getY());
	}
	public void moveL()
	{
		label.setLocation(label.getX()-10,label.getY());
	}
	public static void main(String[]args)
	{
		Grid grid = new Grid();
		Server server = new Server(grid);
		server.run();
	}
}