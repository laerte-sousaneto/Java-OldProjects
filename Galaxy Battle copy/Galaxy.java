import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.Thread;
import javax.sound.sampled.*;
import java.applet.*;
import javax.swing.SwingUtilities.*;

public class Galaxy extends JApplet
{
   private Background mainPanel;
	private StartMenuPanel menuPanel;
	private ShipFrame userShip;
	private ShipFrame[] alienShip;

   private Collision collision;
	private final int WIDTH = 1000;
   private final int HEIGHT = 500;
	
	private Image galaxyImg,shipImg,weaponImg;
	private Image[]alienImg;
	private Image scoreImg;
	private Image  start_btn, container_img, banner_img, quit_img, scores_img;
	private AlienSimulation alienSimulation;
	
	private Animation explosion;
	private Image[] explosionImgs;
	
	private AudioClip backgroundClip;
	private AudioClip weaponClip;
	private AudioClip explosionClip;
	private AudioClip terminatedClip;
	private AudioClip[] levelClip;
	
	private Player userInfo;
	private Level level;
	private GameStatus statusPanel;
	
	private AnimatedPanel scorePanel;
	
   public void init()
	{
		loadMainObjects();
		runMainPanel();
		runMenuPanel();							
   }
	public void runGame(int lev)
	{		 
		if(lev==-1)
		{loadAllienShips(33);}
		else
		{loadAllienShips(8+level.getDifficulty());}
		addAliens();
		addUserShip();
		if(statusPanel == null)
		{
			System.out.println("null");
		}
		statusPanel.refreshAll();
		runSimulation(lev);
		runCollisionCheck();	
		userShip.getWeapon(0).setTime(10);
		userShip.getWeapon(1).setTime(10);
		userShip.getWeapon(2).setTime(10);
	}
	public void loadMainObjects()
	{
		loadImages();
		loadSongs();
		loadMainPanel();
		loadMenuPanel();
		loadUser();
	}
	public void statusBar()
	{
		statusPanel = new GameStatus(userInfo,10,10);
		if(statusPanel == null)
		{
			System.out.println("null");
		}
		mainPanel.add(statusPanel);
	}
	public void loadScoresPanel()
	{
		if(scorePanel == null)
		{
			scorePanel = new AnimatedPanel(scoreImg,400,400,10,10);
		}
	}
	public void runScoresPanel()
	{
		menuPanel.add(scorePanel);
	}
	public void openScores(boolean clicked)
	{
		if(!clicked)
		{
			loadScoresPanel();
			runScoresPanel();
			scorePanel.animate();
			scorePanel.setVisible(true);
		}
		else
		{
			closeScores();
		}
	}
	public void closeScores()
	{
		scorePanel.setVisible(false);
		scorePanel = null;
	}
	public void runSimulation(int lev)
	{
		alienSimulation = null;
		alienSimulation = new AlienSimulation(userShip, alienShip, WIDTH,HEIGHT,lev,level);
		alienSimulation.start();
	}
	public void runCollisionCheck()
	{
		collision = null;
		collision = new Collision(userInfo,this,statusPanel,userShip,alienShip,level,WIDTH,HEIGHT);
		collision.start();
	}
	public void loadExplosionImages()
	{
		int nimgs = 16;
		explosionImgs = new Image[nimgs];
		for(int i = 0; i< nimgs; i++)
		{
			String path = "Images/Explosion/exp"+(i+1)+".png";
			explosionImgs[i] = loadImage(path);
		}
	}
	public void loadImages()
	{
		scoreImg = loadImage("Images/scoreback.png");
		galaxyImg = loadImage("Images/Galaxy.png");
		shipImg = loadImage("Images/spaceship.png");
		weaponImg = loadImage("Images/beam.png");
		alienImg = new Image[]{loadImage("Images/alien.png"),loadImage("Images/alien2.png"),loadImage("Images/alien3.png")};
		
		start_btn = loadImage("Images/start_btn.png");
		quit_img = loadImage("Images/quit_img.png");
		container_img = loadImage("Images/btn_container.png");
		banner_img = loadImage("Images/game_banner.png");
		scores_img = loadImage("Images/scores.png");
		loadExplosionImages();
	}
	public void loadSongs()
	{
		backgroundClip = loadSong("songs/lvl.wav");
		weaponClip = loadSong("songs/laser.wav");
		explosionClip = loadSong("songs/explosion.wav");
		terminatedClip = loadSong("songs/terminated.wav");
	}
	public void loadMainPanel()
	{
		mainPanel = new Background(galaxyImg,backgroundClip,WIDTH,HEIGHT);
	}
	public void loadMenuPanel()
	{
		menuPanel = new StartMenuPanel(start_btn, quit_img, scores_img, container_img, banner_img, WIDTH,HEIGHT,this);
	}
	public void loadUser()
	{
		int life = 5;
		int ammo = 1;
		int points = 30;
		AxisLimit x = new AxisLimit(20,WIDTH-50);
		AxisLimit y = new AxisLimit(HEIGHT-100,HEIGHT-50);
		userShip = new ShipFrame(shipImg,life,points,ammo,true,weaponImg,explosionClip,weaponClip
						,(WIDTH/2),y.getEnd(),x,y,WIDTH,HEIGHT,explosionImgs);
								
		level = null;
		level = new Level(alienShip,levelClip);
		level.setLevel(0);
		
		userInfo = new Player("Java",userShip,level);
	}
	public void loadAllienShips(int length)
	{
		alienShip = null;
		alienShip = new ShipFrame[length];
		int life = 1;
		int points = 30;
		int ammo = 1;
		int init_x = 100;
		int init_y = 100;
		AxisLimit x = new AxisLimit(0,1200);
		AxisLimit y = new AxisLimit(0,1200);
		
		for(int i = 0; i< alienShip.length;i++)
		{
			alienShip[i] = new ShipFrame(alienImg[i%3],life,points,ammo,false,weaponImg,explosionClip,weaponClip
						,init_x,init_y,x,y,WIDTH,HEIGHT,explosionImgs);
		}
	}
	public void runMainPanel()
	{
		mainPanel.addKeyListener(new MyKeyListener(userShip));
      setSize(WIDTH,HEIGHT);
      setLayout(new BorderLayout());
		getContentPane().add(mainPanel,BorderLayout.CENTER);
		mainPanel.playAudio();
	}
	public void runMenuPanel()
	{
		mainPanel.add(menuPanel);
		menuPanel.animate();
	}
	public void addUserShip()
	{
		mainPanel.add(userShip);
		userShip.moveLeft(1);
		for(int i = 0; i<(userShip.getWeapon()).length;i++)
		{
			mainPanel.add(userShip.getWeapon()[i]);
		}
	}
	public void addAliens()
	{ 			
		for(int i = 0; i<alienShip.length;i++)
		{
			for(int b = 0;b<alienShip[i].getWeapon().length;b++)
			{
				mainPanel.add(alienShip[i]);
				mainPanel.add(alienShip[i].getWeapon()[b]);
			}
		}         
	}
   public Image loadImage(String p) 
	{
       Image i = null;
       try{
	       i = ImageIO.read(new URL(getCodeBase(),p));
       }
       catch(MalformedURLException ex){
 	      System.out.print(ex.getCause());
       }
       catch(IOException ex){
               System.out.print(ex.getCause());
       }
       finally {
           System.out.println("Image Path: "+p+" - Loaded.");
       }
       return i;
  }
  public AudioClip loadSong(String p) 
  {
       AudioClip i = null;
       try{
	       i = getAudioClip(getCodeBase(),p);
       }
       finally {
           System.out.println("Song Path: "+p+" - Loaded.");
       }
		 
       return i;
  }  
}