/* ------------------------------------------------------------------|
|Designer: 			  Laerte Sousa													|
|Basic Information: This class extends  from JPanel and uses an array|
|						  of Weapon objects.					                  |
|Purpose: 			  The purpose of this class is to create and panel |
|						  with an image of a space ship drew in it, and to	|
|						  give it the ability to handle Weapon objects.		|
|--------------------------------------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.IllegalThreadStateException;
import java.applet.*;

class ShipFrame extends JPanel{
	
	public final static String[] AMMO_SLOTS = new String[]{"center","left","right"}; //Holds options of weapon locations.
	private Point[] slotLocations;																	//Holds the actual locations in sync with
																												//AMMO_SLOTS.
	private AxisLimit xbound;					//Limits x-axis moves
	private AxisLimit ybound;					//Limits y-axis moves
	
	private Image shipImg;						//Holds ship image to be drawn in this panel
	private int life;								//Amount of hits it can take
	private int points;
	private boolean dead = false;				//if false means it is still alive
	private boolean direction = true;
	private final int WIDTH = 40;				//This panel width
	private final int HEIGHT = 30;			//This panel height
	
	private final int INIT_SHIPLOCATION_X;	//Ship initial X location on the main panel
	private final int INIT_SHIPLOCATION_Y;	//Ship initial Y location on the main panel
	
	private Image beamImg;					//Holds weapon image to be passed to the weapon constructor
	public static final int MAX_AMMO = 3;		//Defines the maximum amount of ammo
	private Weapon[] weapon;				//Array of weapons to be added to the ship
	private int ammoIndex;					//Which weapon to be used
	private int ammo;							//current ammo limit
	
	private final int MAIN_PANEL_WIDTH;
	private final int MAIN_PANEL_HEIGHT;
	
	private AudioClip shootingClip;			//clip to be played when shooting occurs
	private AudioClip	explosionClip;			//clip to be played when ship explodes
	
	private Animation explosion;

	/*--------------------------------------------------|
	|	CONSTRUCTOR													 |
	|--------------------------------------------------*/
	/* The parameters nessesary to initialize this		|
	|	constructor are Image,Image,int,int. 				|
	|  the images passed will be drawn to the ship and	|
	|	weapon's panel, and the integers will define the|
	|	main panel's boundaries.								|
	|																	|
	|		It will inialize the array of weapon, and set|
	|	its initial ammoIndex and ammo limit.				|
	|	The constructor will also set  the  panel			| 
	|	layout to null, opaque value to false, and 		|
	|	visibility to true.   									|
	|--------------------------------------------------*/
	ShipFrame(Image sI,int shipLife,int p, int shipAmmo, boolean d, Image bI
		,AudioClip exploClip,AudioClip weaponClip
		,int initialX,int initialY, AxisLimit x, AxisLimit y
		,int mainPanelWidth, int mainPanelHeight, Image[] explo)
   {
		shipImg = sI;
		beamImg = bI;
		
		life = shipLife;
		points = p;
		ammo = shipAmmo;
		direction = d;
		
		explosionClip = exploClip;
		shootingClip = weaponClip;
		explosion = new Animation(explo,20);

		xbound = x;
		ybound = y;
		
		MAIN_PANEL_WIDTH = mainPanelWidth;
		MAIN_PANEL_HEIGHT = mainPanelHeight;
		
		INIT_SHIPLOCATION_X = initialX;
		INIT_SHIPLOCATION_Y = initialY;
		
		weapon = new Weapon[MAX_AMMO];
		
		ammoIndex = 0;
		ammo = 3;
		
		setSize(WIDTH,HEIGHT);
		setLocation(INIT_SHIPLOCATION_X,INIT_SHIPLOCATION_Y);
		
		slotLocations = new Point[3];
		this.loadWeapon();
		
		setLayout(null);
		setOpaque(false);
		setVisible(true);
	}
	/*--------------------------------------------------|
	|	SpaceShip's Image Modifier								 |
	|--------------------------------------------------*/
	/*	This method replace the current image to the one|
	|	being passed.												|
	---------------------------------------------------*/
	public void setImage(Image i)
	{
		shipImg = i;
	}
	public void increaseLife()
	{
		life++;
	}
	public void decreaseLife()
	{
		if(life > 0)
		{
			life--;
		}
		if(life == 0)
		{
			dead = true;
			explosionClip.play();
			this.repaint();
			this.add(explosion);
			explosion.startAnimation();
		}
	}
	/*--------------------------------------------------|
	|	SpaceShip's weapon Modifier							 |
	|--------------------------------------------------*/
	/*	Prerequisite: index must be less than MAX_AMMO. |
	|	When called it replaces a weapon at a certain 	|
	|	index with the one being passed.						|
	---------------------------------------------------*/
	public void replaceWeaponAt(Weapon w,int index)
	{
		if(index < MAX_AMMO)
		{
			weapon[index] = w;
		}
		else
		{
			System.out.println("ERROR: weapon index out of boundaries.");
		}
	}
	/*--------------------------------------------------|
	|	SpaceShip's weapons Modifier							 |
	|--------------------------------------------------*/
	/*	Prerequisite: The length of the array of weapons |
	|	being passed must be less than MAX_AMMO.			 |
	|	When called and requirement met, method will		 |
	|	replace current array of weapon for the one being|
	|	passend							  							 |
	---------------------------------------------------*/
	public void replaceWeaponArray(Weapon[]w)
	{
		if(w.length < 	MAX_AMMO)
		{
			weapon = w;
		}
		else
		{
			System.out.println("ERROR: weapon being passed has to many indexes.");
		}
	}
	/*--------------------------------------------------|
	|	SpaceShip's ammoIndex Modifier						 |
	|--------------------------------------------------*/
	/*	Prerequisite: index being passed must be less	|
	|	than MAX_AMMO.												|
	|	When called ammoIndex value will be reset to		|
	|	the value being passed.									|
	---------------------------------------------------*/
	public void setAmmoIndex(int index)
	{
		if(index < MAX_AMMO)
		{
			ammoIndex = index;
		}
		else
		{
			System.out.println("ERROR: index being passed is out of boundaries.");
		}
	}
	/*--------------------------------------------------|
	|	SpaceShip's ammo Modifier								 |
	|--------------------------------------------------*/
	/*	Prerequisite: index being passed must be less	|
	|	than MAX_AMMO.												|
	|	When called ammoIndex value will be reset to		|
	|	the value being passed.									|
	---------------------------------------------------*/
	public void setAmmo(int newAmmo)
	{
		if(newAmmo < MAX_AMMO)
		{
			ammo = newAmmo;
		}
		else
		{
			System.out.println("ERROR: newAmmo is above the limit");
		}
	}
	/*--------------------------------------------------|
	|	SpaceShip's Image Assessor           					 |
	|--------------------------------------------------*/
	/* returns weapon current image.							|
	---------------------------------------------------*/
	public Image getImage()
	{
		return shipImg;
	}
	public int getLife()
	{
		return life;
	}
	public boolean isDead()
	{
		return dead;
	}
	/*--------------------------------------------------|
	|	SpaceShip's INIT_SHIPLOCATION_X Assessor           					 |
	|--------------------------------------------------*/
	/* returns ship x initial location						|
	---------------------------------------------------*/
	public int getInit_ShipLocation_X()
	{
		return INIT_SHIPLOCATION_X;
	}
	/*--------------------------------------------------|
	|	SpaceShip's INIT_SHIPLOCATION_Y Assessor           					 |
	|--------------------------------------------------*/
	/* returns ship y initial location						|
	---------------------------------------------------*/
	public int getInit_ShipLocation_Y()
	{
		return INIT_SHIPLOCATION_Y;
	}
	/*--------------------------------------------------|
	|	MAX_AMMO Assessor           				 			 |
	|--------------------------------------------------*/
	/* returns maximum ammo value allowed					|
	---------------------------------------------------*/
	public int getMax_Ammo()
	{
		return MAX_AMMO;
	}
	/*--------------------------------------------------|
	|	Weapon Assessor           				 				 |
	|--------------------------------------------------*/
	/* Prerequisite: index passed must be less than 	 |
	|	MAX_AMMO.													 |
	|	returns weapon corresponding to the index passed |
	---------------------------------------------------*/
	public Weapon getWeapon(int index)
	{
		if(index < MAX_AMMO)
		{
			return weapon[index];
		}
		else
		{
			System.out.println("ERROR: weapon index out of boundaries.");
			return null;
		}
	}
	/*--------------------------------------------------|
	|	Weapon[] Assessor           				 			|
	|--------------------------------------------------*/
	/* returns array of weapons      						|
	---------------------------------------------------*/
	public Weapon[] getWeapon()
	{
		return weapon;
	}
	/*--------------------------------------------------|
	|	ammoIndex Assessor           				 			|
	|--------------------------------------------------*/
	/* returns ammoIndex			      						|
	---------------------------------------------------*/
	public int getAmmoIndex()
	{
		return ammoIndex;
	}
	/*--------------------------------------------------|
	|	ammo Assessor           				 			|
	|--------------------------------------------------*/
	/* returns ammo			      						|
	---------------------------------------------------*/
	public int getAmmo()
	{
		return ammo;
	}
	public int getPoints()
	{
		return points;
	}
	public void setPoints(int p)
	{
		points = p;
	}
	public void carryWeapon(int space, String direction)
	{
		if(direction.equals("left"))
		{
			for(int i =0;i < weapon.length;i++)
			{
				if(!weapon[i].isMoving() && !weapon[i].isEndPosition())
				{
					weapon[i].setLocation(weapon[i].getX()-space,weapon[i].getY());
				}
				else if(weapon[i].isEndPosition() && !weapon[i].isMoving())
				{
					loadSlot(i);
				}
			}
		}
		else if(direction.equals("right"))
		{
			for(int i =0;i < weapon.length;i++)
			{
				if(!weapon[i].isMoving() && !weapon[i].isEndPosition())
				{
					weapon[i].setLocation(weapon[i].getX()+space,weapon[i].getY());
				}
				else if(weapon[i].isEndPosition() && !weapon[i].isMoving())
				{
					loadSlot(i);
				}
			}
		}
		else if(direction.equals("up"))
		{
			for(int i =0;i < weapon.length;i++)
			{
				if(!weapon[i].isMoving() && !weapon[i].isEndPosition())
				{
					weapon[i].setLocation(weapon[i].getX(),weapon[i].getY()-space);
				}
				else if(weapon[i].isEndPosition() && !weapon[i].isMoving())
				{
					loadSlot(i);
				}
			}
		}
		else if(direction.equals("down"))
		{
			for(int i =0;i < weapon.length;i++)
			{
				if(!weapon[i].isMoving() && !weapon[i].isEndPosition())
				{
					weapon[i].setLocation(weapon[i].getX(),weapon[i].getY()+space);
				}
				else if(weapon[i].isEndPosition() && !weapon[i].isMoving())
				{
					loadSlot(i);
				}
			}
		}
		
	}
	/*--------------------------------------------------|
	|	moveLeft  Location Method	           		     	|
	|--------------------------------------------------*/
	/* Change the ship's X location based on the space |
	|	valeu being passed. Thus moving ship to the     |
	|	left.				      									|
	---------------------------------------------------*/
	public void moveLeft(int space)
	{
		if(getX() >= xbound.getStart())
		{
			setLocation(getX()-space,getY());
			carryWeapon(space,"left");
		}
	}
	/*--------------------------------------------------|
	|	moveRight  Location Method	           		     	|
	|--------------------------------------------------*/
	/* Change the ship's X location based on the space |
	|	valeu being passed. Thus moving ship to the     |
	|	right.				      									|
	---------------------------------------------------*/
	public void moveRight(int space)
	{
		if(getX() < xbound.getEnd())
		{
			setLocation(getX()+space,getY());
			carryWeapon(space,"right");
		}
	}
	/*--------------------------------------------------|
	|	moveUp  Location Method	           		     	|
	|--------------------------------------------------*/
	/* Change the ship's Y location based on the space |
	|	valeu being passed. Thus moving ship to the     |
	|	up.				      									|
	---------------------------------------------------*/
	public void moveUp(int space)
	{
		if(getY() > ybound.getStart())
		{
			setLocation(getX(),getY()-space);
			carryWeapon(space,"up");
		}
	}
	/*--------------------------------------------------|
	|	moveDown  Location Method	           		     	|
	|--------------------------------------------------*/
	/* Change the ship's X location based on the space |
	|	valeu being passed. Thus moving ship to the     |
	|	down.				      									|
	---------------------------------------------------*/
	public void moveDown(int space)
	{
		if(getY() < ybound.getEnd())
		{
			setLocation(getX(),getY()+space);
			carryWeapon(space,"down");
		}
		
	}
	/*--------------------------------------------------|
	|	loadWeapon Method	           		     				|
	|--------------------------------------------------*/
	/* when called initialize this class's array of 	|
	|	weapons.	      											|
	---------------------------------------------------*/
	private void loadWeapon() {
		for(int i =0; i<weapon.length;i++)
		{
			weapon[i] = new Weapon(beamImg,shootingClip,direction,AMMO_SLOTS[i%AMMO_SLOTS.length],MAIN_PANEL_HEIGHT);
		}
	}
	/*--------------------------------------------------|
	|	increaseAmmo Method	           		     			 |
	|--------------------------------------------------*/
	/*	Prerequisite: ammo must be less than MAX_AMMO	| 
	|	when called ammo will increase by 1 if limite	|
	|	has not been met yet.									|	      											|
	---------------------------------------------------*/
	public void increaseAmmo()
	{
		if(ammo < MAX_AMMO)
		{
			 ammo++;
		}
	}
	/*--------------------------------------------------|
	|	decreaseAmmo Method	           		     			 |
	|--------------------------------------------------*/
	/*	Prerequisite: ammo must be less than MAX_AMMO	| 
	|	when called ammo will increase by 1 if limite	|
	|	has not been met yet.									|	
	---------------------------------------------------*/
	public void decreaseAmmo()
	{
		if(ammo > 0)
		{
			ammo--;
		}
	}
	/*--------------------------------------------------|
	|	loadSlot Method	           		     			 |
	|--------------------------------------------------*/
	/*	  This function will move the weapon panel north |
	|	or south depending on the direction value.       |            									|	
	---------------------------------------------------*/
	public void loadSlot(int weaponIndex)
	{
		slotLocations = new Point[]{new Point(getX()+18,getY()-1),
		     new Point(getX()+4,getY()-1),new Point(getX()+30,getY()-1)};
			  
		weapon[weaponIndex].setLocation(slotLocations[findSlot(slotLocations,weaponIndex)]);
			  
	}
	/*--------------------------------------------------|
	|	shootWeapon Method	           		     			 |
	|--------------------------------------------------*/
	/*	  This function will move the weapon panel north |
	|	or south depending on the direction value.       |            									|	
	---------------------------------------------------*/
	public int findSlot(Point[] slotL,int wI)
	{
		int slotIndex = 0;
		
		for(int i =0; i<slotL.length;i++)
		{
				if((weapon[wI].getSlot()).equals(AMMO_SLOTS[i]))
				{
					slotIndex = i;
				}
		}
		return slotIndex;
	}
	public void shootWeapon()
	{	  
		if(ammoIndex == ammo)
		{
			ammoIndex = 0;
		}
		
		ammoIndex = ammoIndex%ammo;
		if(!weapon[ammoIndex].isAlive() && !dead)
		{
			loadSlot(ammoIndex);
			weapon[ammoIndex].shoot();
			ammoIndex++;
		}
	}
	/*--------------------------------------------------|
	|	Weapon's Paint Method            					 |
	|--------------------------------------------------*/
	/* This  method  will paint img to the panel.		 |
	---------------------------------------------------*/
	public void paintComponent(Graphics g) 
	{
		g = (Graphics2D)g;
		super.paintComponent(g);
		if(!dead)
		{
			try{
				g.drawImage(shipImg,0,0,this);
			}catch(NullPointerException ex){
				System.out.println(ex.getCause());
			}
		}
	}
}