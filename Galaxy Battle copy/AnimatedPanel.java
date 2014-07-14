import javax.swing.*;
import java.awt.*;
class AnimatedPanel extends JPanel implements Runnable
{
	private Thread thread;
	private Image image;
	
	private int initWidth;
	private int initHeight;
	
	private final int WIDTH;
	private final int HEIGHT;
	
	
	AnimatedPanel(Image i,int w, int h,int x, int y)
	{
		thread = null;
		image = i;
		WIDTH = w;
		HEIGHT = h;
		
		initWidth = 0;
		initHeight = 0;
		setLayout(null);	
		setSize(initWidth,initHeight);
		setLocation(x,y);
		setVisible(false);
		setOpaque(false);
			
	}
	public void animate()
	{
			thread = new Thread(this);
			thread.start();
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.drawImage(image,0,0,this);
	}
	public void run()
	{
	
		while(WIDTH > initWidth || HEIGHT > initHeight)
		{
		try
		{
		thread.sleep(5);
		}catch(InterruptedException ie){}
			if(WIDTH > initWidth)
			{
				initWidth += 5;
			}
			if(HEIGHT > initHeight)
			{
				initHeight +=5;
			}
			setSize(initWidth,initHeight);
			repaint();
		}
		
	}
}