/* ------------------------------------------------------------------|
|Designer: 			  Laerte Sousa													|
|Basic Information: This class extends  from JPanel and implements   |
|						  Runnable.					                           |
|Purpose: 			  The purpose of this class is to create and panel |
|						  with an image of an weapon drew in it. Then it	|
|						  uses the run from Runnable interface to move the	|
|						  location over a certain pause at the programmer	|
|						  choice.					   								|
|--------------------------------------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.applet.*;

class Weapon extends JPanel implements Runnable{
	
   private AudioClip shootingClip;
	private Image img;										//beam  image. 
	private int pause =20;									//pause between beam moves in milliseconds
	private final int PAUSE_MAX = 100;					//maximum pause you can set
	private final int PAUSE_MIN = 5;						//minimum pause you can set
	private int time = 25;									//pause between beam moves in milliseconds
	private final int TIME_MAX = 100;					//maximum pause you can set
	private final int TIME_MIN = 5;						//minimum pause you can set
	private int power = 1;									//how  much  damage  it  does
	private final int POWER_MAX = 5;						//defines the maximum power you can have
	private final int POWER_MIN = 1;						//defines the minimum power you can have
	private boolean moving = false;						//if  it is  moving
	private boolean killThread = false;
	private boolean direction = true;					//true =  moving  up,  false =  moving down
	private String slot = ShipFrame.AMMO_SLOTS[0];	//position  on  the  ship 
	
	private Thread thread;									//handles  the  movement  of  the  beam
	private boolean endPosition = false;
	
	private final int WIDTH = 10;							//width  of  the  beam's panel
	private final int HEIGHT = 10;						//length  of  the  beam's panel
	private final int YBOUND;								//width of the game's panel
	
	/*--------------------------------------------------|
	|	CONSTRUCTOR													 |
	|--------------------------------------------------*/
	/* The parameters nessesary to initialize this		|
	|	constructor are Image,boolean,string,int. 		|
	|  the image passed will be drawn to the panel's	|	
	|	background, the boolean value will set the 		|
	|	moving direction of the weapon, String 			|
	|	provides the slot location of the ship that the	| 
	|	weapon will be placed, int provides the y 		|
	|	boundaries of the game's main panel.				|
	|																	|
	|	The constructor will also set  the  panel layout| 
	|	to null,focusable,opaque value to false, and 	|
	|	visible to false.   										|
	|--------------------------------------------------*/
	Weapon(Image i,AudioClip clip, boolean d,String s,int ybound) 
	{
		img = i;
		shootingClip = clip;
		direction = d;
		slot = s;
		YBOUND = ybound;
		thread = null;		
					
		setSize(WIDTH,HEIGHT);		//sets  the  weapons  panel  size
		setOpaque(false);				//won't  draw  any  background			
	   setLayout(null);				//set layout to none
		setVisible(false);			//visible by default so it will only appear when needed
	}
	/*--------------------------------------------------|
	|	Weapon's Image Modifier									 |
	|--------------------------------------------------*/
	/*	This method replace the current image to the one|
	|	being passed.												|
	---------------------------------------------------*/
	public void setImage(Image i)
	{
		img = i;
	}
	public void setTime(int t)
	{
		time = t;
	}
	/*--------------------------------------------------|
	|	Weapon's pause++ Method									 |
	|--------------------------------------------------*/
	/*	This method increases the current paused time 	|
	|	by 5 as long as it has not reached the maximum	|
	|	limit.														|
	---------------------------------------------------*/
	public void increasePause()
	{
		if(time < TIME_MAX)
		{
			time += 5;
		}
	}
	/*--------------------------------------------------|
	|	Weapon's pause-- Method									 |
	|--------------------------------------------------*/
	/*	This method decreases the current paused time 	|
	|	by 5 as long as it has not reached the minimum	|
	|	limit.														|
	---------------------------------------------------*/
	public void decreasePause()
	{
		if(time > TIME_MIN)
		{
			time -= 5;
		}
	}
	/*--------------------------------------------------|
	|	Weapon's Power++ Method									 |
	|--------------------------------------------------*/
	/*	This method increases the current power value 	|
	|	by 1 as long as it has not reached the maximum	|
	|	limit.														|
	---------------------------------------------------*/
	public void increasePower()
	{
		if(power < POWER_MAX)
		{
			power++;
		}
	}
	/*--------------------------------------------------|
	|	Weapon's Power-- Method									 |
	|--------------------------------------------------*/
	/*	This method decreases the current power value 	|
	|	by 1 as long as it has not reached the minimum	|
	|	limit.														|
	---------------------------------------------------*/
	public void decreasePower()
	{
		if(power < POWER_MIN)
		{
			power--;
		}
	}
	/*--------------------------------------------------|
	|	Weapon's Direction Modifier   						 |
	|--------------------------------------------------*/
	/*	This method defines which direction the panel	|
	|	should be moving. If true moves north, if false	|
	|	moves south.												|
	---------------------------------------------------*/
	public void setDirection(boolean d)
	{	
		direction = d;
	}
	/*--------------------------------------------------|
	|	Weapon's Slot Modifier									 |
	|--------------------------------------------------*/
	/*	This method changes the slot location of the		|
	|	weapon. the passing value has to be of String	|
	|	type with value of "center","left", or "right".	|
	---------------------------------------------------*/
	public void setSlot(String s)
	{
		slot = s;
	}
	/*--------------------------------------------------|
	|	Weapon's Thread Assessor								 |
	|--------------------------------------------------*/
	/*	This method stops the						|	
	---------------------------------------------------*/
	public void killThread() 
	{
		if(isAlive())
		{
			thread = null;
			killThread = true;
		}
	}
	/*--------------------------------------------------|
	|	Weapon's Thread Assessor								 |
	|--------------------------------------------------*/
	/*	This method returns the thread is null or not.	|											|
	---------------------------------------------------*/
	public boolean isAlive() 
	{
		if(thread == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public boolean isEndPosition()
	{
		return endPosition;
	}
	/*--------------------------------------------------|
	|	Weapon's Speed Assessor           					 |
	|--------------------------------------------------*/
	/* Returns current pause value in millisecond.		|
	---------------------------------------------------*/
	public int getTime()
	{
		return time;
	}
	/*--------------------------------------------------|
	|	Weapon's Slot Assessor           					 |
	|--------------------------------------------------*/
	/*	return current slot location(String)				|
	---------------------------------------------------*/
	public String getSlot()
	{
		return slot;
	}
	/*--------------------------------------------------|
	|	Weapon's Direction Assessor         				 |
	|--------------------------------------------------*/
	/*	return moving direction of the weapon. True for	|
	|	north, and false for south.							|
	---------------------------------------------------*/
	public boolean getDirection()
	{
		return direction;
	}
	/*--------------------------------------------------|
	|	Weapon's Moving Check Assessor           			 |
	|--------------------------------------------------*/
	/* return if weapon is moving or not.					|
	---------------------------------------------------*/
	public boolean isMoving()
	{
		return moving;
	}
	/*--------------------------------------------------|
	|	Weapon's Image Assessor           					 |
	|--------------------------------------------------*/
	/* returns weapon current image.							|
	---------------------------------------------------*/
	public Image getImage() {
		return img;
	}
	/*--------------------------------------------------|
	|	Weapon's Move Method             					 |
	|--------------------------------------------------*/
	/*	moves weapon by however many pixels it is being	|
	|	passed. Passing value has to be int.				|
	---------------------------------------------------*/
	public void move(int space)
	{
		setLocation(getX(),getY()-space);
	}
	/*--------------------------------------------------|
	|	Weapon's Shoot Method             					 |
	|--------------------------------------------------*/
	/* Prerequisite: thread has to be null.				|
	|	Function: inialize thread, and call its run 		|
	|	method, set moving and visible to true.			|
	---------------------------------------------------*/
	public void shoot()
	{
		if(thread == null) 
		{
			thread = new Thread(this);
			thread.start();
			moving = true;
			setVisible(true);
			shootingClip.play();
		}
	}
	/*--------------------------------------------------|
	|	Weapon's Run Method              					 |
	|--------------------------------------------------*/
	/*	Presequesite: thread must be initialized, and	|
	|	direction must be set to either false or true.	|
	|																	|
	|	Function: When called it will check if direction|
	|	is either false or true. If true space will be	|
	|	set to 10, else it will be set to -10.				|
	|		While direction = true and the y position of	| 
	|	this	panel is less than this panel width(10), 	|
	|	or	direction = false, and this panel y position	|
	|	is less than this panel width(10)+YBOUND. It 	|
	|	will call move() and its parameter value will	|
	|	be whatever the variable space is holding.		|
	|																	|
	|		After the breaking out of the while loop,		|
	|	moving will be set to false, thread will be		|
	|	set to null, and panel visibility will be set	|
	|	to false.													|
	---------------------------------------------------*/
	public void run() 
	{
		int space;
		
		if(direction)
		{
			space = 5;
		}
		else
		{
			space = -5;
		}
		while(!killThread &&((direction && this.getY() > (this.getWidth()-(getHeight()+50))) || (!direction && this.getY() < (this.getWidth()+YBOUND))))
		{
			try{thread.sleep(time);}catch(InterruptedException ex){}
			this.move(space);
		}
		try{thread.sleep(pause);}catch(InterruptedException ex){}
		moving = false;
		thread = null;
		endPosition = true;
		setVisible(false);
		killThread = false;
	}
	/*--------------------------------------------------|
	|	Weapon's Paint Method            					 |
	|--------------------------------------------------*/
	/* This  method  will paint img to the panel.		|
	---------------------------------------------------*/
	public void paintComponent(Graphics g) 
	{
		g = (Graphics2D) g;
		super.paintComponent(g);
		try{
			g.drawImage(img,0,0,this);
		}catch(NullPointerException ex){
			System.out.println(ex.getCause());
		}
	}
	
}