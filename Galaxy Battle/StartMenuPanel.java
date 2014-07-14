import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class StartMenuPanel extends JPanel implements Runnable
{
	private Thread thread;
	
	private final int WIDTH;
	private final int HEIGHT;
	
	
	private StartMenuItem button_container;
	private StartMenuItem banner;
	
	private StartMenuItem startButton;
	private StartMenuItem quitButton;
	private StartMenuItem scoresButton;
	
	private AxisLimit bannerx, bannery;
	private AxisLimit buttoncontainerx, buttoncontainery;
	
	private int bannerWidth,bannerHeight;
	private int containerWidth,containerHeight;
	
	StartMenuPanel(Image start_btn,Image quit_img, Image scores_img, Image container_img, Image banner_img 
			,int width, int height,Galaxy main)
	{
		WIDTH = width;
		HEIGHT = height;
		bannerWidth = 800;
		bannerHeight = 150;
		containerWidth = 300;
		containerHeight = 140;
		
		bannerx = new AxisLimit(-bannerWidth,225);
		bannery = new AxisLimit(200,100);
		buttoncontainerx = new AxisLimit(450,450);
		buttoncontainery = new AxisLimit(HEIGHT+containerHeight,250);
		// BANNER configuration
		banner = new StartMenuItem( banner_img, bannerWidth, bannerHeight, bannerx.getStart(),bannery.getStart());
		// CONTAINER configuration
		button_container = new StartMenuItem( container_img, containerWidth, containerHeight, buttoncontainerx.getStart(), buttoncontainery.getStart());
		// START button configuration
		startButton = new StartMenuItem( start_btn, 150, 45, 80, 15);	
		// SCORES button configuration
		scoresButton = new StartMenuItem( scores_img, 150, 45, 80, 75);
		
		
		button_container.addMouseListener(new MenuMouseListener(main,this));
		add(banner);
		add(button_container);
		button_container.add(startButton);
		button_container.add(scoresButton);
		
		setLayout(null);
		setOpaque(false);
		setSize(WIDTH,HEIGHT);
		setVisible(true);		
	}
	public StartMenuItem getStartButton()
	{
		return startButton;
	}	
	public StartMenuItem getQuitButton()
	{
		return quitButton;
	}	
	public StartMenuItem getScoresButton()
	{
		return scoresButton;
	}	
	public StartMenuItem getContainer()
	{
		return button_container;
	}
	public void removeAllItems()
	{
		startButton.setVisible(false);
		button_container.setVisible(false);
		banner.setVisible(false);
		scoresButton.setVisible(false);
	}
	public void run()
	{	
	
		int speed = 20;
		while(banner.getX() < bannerx.getEnd())
		{
			try
			{
				thread.sleep(speed);
			}catch(InterruptedException io){}			
			banner.movex(10);
		}
		while(button_container.getY() > buttoncontainery.getEnd())
		{
			try
			{
				thread.sleep(speed);
			}catch(InterruptedException io){}
			if((banner.getY()+banner.getHeight()) < button_container.getY())
			{
				button_container.movey(-10);
			}
			else
			{
				banner.movey(-10);
				button_container.movey(-10);
			}
		}
	}
	public void animate()
	{
		thread = null;
		thread = new Thread(this);
		thread.start();
	}
}