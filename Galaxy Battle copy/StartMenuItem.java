import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.IllegalThreadStateException;

class StartMenuItem extends JPanel {

	// DATAFIELDS
						
	// StartMenuImage image to be drawn on THIS panel
	private Image image;						
	// Width of StartMenuItem panel
	private int width;
	// Height of StartMenuItem panel				
	private int height;
	// StartMenuItem X-LOCATION on MAIN panel
	private final int X;	
	// StartMenuItem Y-LOCATION on MAIN panel
	private final int Y;	
	
	// CONSTRUCTOR
	StartMenuItem( Image image, int width, int height, int X, int Y) {
	
		this.image=image;
		this.width=width;
		this.height=height;
		this.X=X;
		this.Y=Y;
				
		// Panel Configuration
		setLayout(null);
		setOpaque(false);
		setSize(width, height);
		setLocation(X, Y);
		setVisible(true);
	}
	
	public void movex(int n)
	{
		setLocation(getX()+n,getY());
	}
	public void movey(int n)
	{
		setLocation(getX(),getY()+n);
	}
	
	
	// @Override paintComponent
	public void paintComponent(Graphics g) 
	{
		g = (Graphics2D)g;
		super.paintComponent(g);
		try{
			g.drawImage(image, 0, 0, this);
		}catch(NullPointerException ex){
			System.out.println(ex.getCause());
		}
	}
}