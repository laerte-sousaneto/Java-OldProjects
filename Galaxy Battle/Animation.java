import java.awt.*;
import javax.swing.*;

class Animation extends JPanel implements Runnable
{
	private Image[] image;
	private int index;
	private int delay;
	private boolean paint;
	
	private Thread thread;
	
	private final int WIDTH = 40;
	private final int HEIGHT = 30;
	
	Animation(Image[] img, int d)
	{
		image = img;
		index = 0;
		delay = d;
		paint = false;
		
		setSize(WIDTH,HEIGHT);
		setLayout(null);
		
		setOpaque(false);
		setVisible(true);

	}
	public void startAnimation()
	{
		if(thread == null)
		{
			thread = new Thread(this);
			paint = true;
			thread.start();
		}
	}
	public void stopThread()
	{
		thread = null;
		paint = false;
	}
	public void run()
	{
		while(index < image.length-1){
			try
			{
				thread.sleep(delay);
				index++;
				repaint();
			}catch(InterruptedException ie){}
		}
		index = 0;
		paint = false;
	}
	public void paintComponent(Graphics g)
	{
		
		g = (Graphics2D)g;
		super.paintComponent(g);
		if(paint)
		{
			try{
				g.drawImage(image[index],0,0,this);
			}catch(NullPointerException ex){
				System.out.println(ex.getCause());
			}
		}
	}
}