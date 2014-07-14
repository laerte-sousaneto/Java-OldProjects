/* ------------------------------------------------------------------|
|Designer: 			  Juliano Cerri													|
|Basic Information: This class extends  from Thread and uses an array|
|						  of ShipFrame objects.					                  |
|Purpose: 			  The purpose of this class is to animate the      |
|						  aliens in each level and also crate algorimths	|
|						  to change how often each alien should shoot.		|
|--------------------------------------------------------------------*/

//Import all of the necessary classes
import java.awt.*;
import java.util.Random;

class AlienSimulation extends Thread {
	
	//Declare all of the variables of the class
	private ShipFrame ship = null;					// The ship being controlled by the player
	private Weapon []shipWeapon = null;				// The weapon associated with the ship
	private ShipFrame[] aliens = null;				// The array of aliens to be animated
	private Weapon[] aliensWeapon = null;			// The array of alien wapons
	private int randomIndex =0;						
	private int level = 0;								//The level of the animation
	private Random generator = new Random();
	private final int PANEL_WIDTH;					//The width of the entire game frame
	private final int PANEL_HEIGHT;					//The height of the entire game frame
	private Boolean left = true;						//Boolean to check alien position status
	private Boolean right = true;						//Boolean to check alien position status
	private int difficulty = 0;
	
	/*--------------------------------------------------|
	|	CONSTRUCTOR													 |
	|--------------------------------------------------*/
	/* The parameters nessesary to initialize this		|
	|	constructor are ShipFrame,ShipFrame[],int,int, 	|
	|  int.								                     |
	|	It will initalize the class members with what	|
	|	is being passed to it. The user ship and the	   |
	|	aliens are being created on another class and   |
	|	passed to this class. the three int type data	|
	|	are for the width and hight in wich to place    |	
	|	the animation in and also the left which will	|
	|	determine the animation that should be applied	|
	|	to that level.												|
	|--------------------------------------------------*/

	AlienSimulation(ShipFrame s,ShipFrame[] a,int w,int h,int motion, Level dif)
	{
		ship = s;
		shipWeapon = ship.getWeapon();
		aliens = a;
		PANEL_WIDTH = w;
		PANEL_HEIGHT = h;
		level = motion;
		difficulty = dif.getDifficulty();
	}
	
	/*--------------------------------------------------|
	|	simulationOne(int,int)									 |
	|--------------------------------------------------*/
	/* The parameters for this function are int, int.   |
	|									                     	 |
	|	The First int type data determines the speed in  | 
	|	which the alien will move                        |
	|	The seccond int type date will determine the     |
	|	frequency in which the alien will shoot.	   	 |
	|--------------------------------------------------*/
	public void simulationOne(int alienSpeed, int shootFrequency)
	{
		//For all the aliens in the array
		for(int i = 0;i<aliens.length;i++)
		{
			//If the alien furtherst in the right is within 100 pixels from the right end of the panel
			if((aliens[aliens.length-1].getX()<=1200 && aliens[aliens.length-1].getX()>=1100))
			{
				left=true; 																
				right=false;
			}
			else if(aliens[0].getX()>=0 && aliens[0].getX()<=100)
			{
				left=false;
				right=true;
			}
			else
			{/* The bolleans remain the same so aliens will keep moving */}
			
			//If left is true and right is false call the moveLeft() function 
			//for each alien in the array so they will all move together.
			if(left==true && right==false)
			{
				int a=0;
				do{
					aliens[a].moveLeft(alienSpeed); //Move at speed passed to the function
					a++;
				}while(a<=aliens.length-1);
			}
			
			//If left is false and right is true call the moveRight() function 
			//for each alien in the array so they will all move together.
			else if(left==false && right==true)
			{
				int t=0;
				do{
					aliens[t].moveRight(alienSpeed); //Move at speed passed to the function
					t++;
				}while(t<=aliens.length-1);
			}
			
			//When the game starts there are both true so start moving to the right
			else if(left==true && right==true)
			{
				int t=0;
				do{
					aliens[t].moveRight(alienSpeed); //Move at speed passed to the function
					t++;
				}while(t<=aliens.length-1);
			}
			
			/*If the alien is alive and the alien is visible to the player and it is within the
			shootFrequency distace from the ship. The alien at the current index can shoot.*/
			if(aliens[i].getLife()!=0 && aliens[i].getX()-ship.getX() <= shootFrequency
												  && aliens[i].getX()-ship.getX() >= -shootFrequency
												  && aliens[randomIndex].getX()<1200)
			{
				aliens[i].shootWeapon(); //Shoot weapon
			}

			
			try
			{
				sleep(100);
			}catch(InterruptedException ex){}
		}
	}
	
	/*--------------------------------------------------|
	|	simulationTwo(int,int)									 |
	|--------------------------------------------------*/
	/* The parameters for this function are int, int.   |
	|									                     	 |
	|	The First int type data determines the speed in  | 
	|	which the alien will move                        |
	|	The seccond int type date will determine the     |
	|	frequency in which the alien will shoot.	   	 |
	|--------------------------------------------------*/
	public void simulationTwo(int alienSpeed, int shootFrequency)
	{	
		//Declare all the variables that will be used in the function
		boolean left = false;
		boolean right = false;
		boolean up = false;
		boolean down = false;
		
		//For all aliens in the array.
		for(int i = aliens.length-1;i>=0;i--)
		{
			try
			{
				sleep(5);
				/*If the current alien x axis is 60 pixels the right-most limit of the game frame.
				  Set right to false and down to true*/
				if(aliens[i].getX() >= PANEL_WIDTH-(aliens[i].getWidth()+20))
				{
					right = false;
					down = true;
				}
				/*If the current alien x axis is 10 pixels the left-most limit of the game frame.
				  Set left to false and up to true*/
				if(aliens[i].getX() <= 30)
				{
					left=false;
					up = true;
				}
				
				/*If the current alien y axis is greater than the game's frame height devide by 3,
				  Set left to true and down to false*/
				if(aliens[i].getY() >= PANEL_HEIGHT/3)
				{
					down = false;
					left = true;
				}
				
				/*If the current alien y axis is less than 60 pixels from the game's frame y axis.
				  Set up to false and right to true*/
				if(aliens[i].getY() <= 60)
				{
					up = false;
					right = true;
				}
				
				//If left is set to true move the alien to the left.
				if(left)
				{
					aliens[i].moveLeft(alienSpeed);	
				}
				//If right is set to true move the alien to the right.
				if(right)
				{
					aliens[i].moveRight(alienSpeed);
				}
				//If up is set to true move the alien up.
				if(up)
				{	
					aliens[i].moveUp(alienSpeed);
				}
				//If down is set to true, move alien down.
				if(down)
				{
					aliens[i].moveDown(alienSpeed);
				}
				/*If the alien is alive and the alien is visible to the player and it is within the
			     shootFrequency distace from the ship. The alien at the current index can shoot.*/
				if(aliens[i].getLife()!=0 && aliens[i].getX()-ship.getX() <= shootFrequency
												  && aliens[i].getX()-ship.getX() >= -shootFrequency
												  && aliens[randomIndex].getX()<1200)
				{
					aliens[i].shootWeapon();
				}
			}catch(InterruptedException ie){}
		}
	}
	
	public void simulationThree(int alienSpeed, int shootFrequency)
	{	
		//Declare all the variables that will be used in the function
		boolean left = false;
		boolean right = false;
		boolean up = false;
		boolean down = false;
	//For all aliens in the array.
		for(int i = aliens.length-1;i>=0;i--)
		{
			try
			{
				sleep(5);
				/*If the current alien x axis is 60 pixels the right-most limit of the game frame.
				  Set right to false and down to true*/
				if(aliens[i].getX() >= PANEL_WIDTH-(aliens[i].getWidth()+20))
				{
					right = false;
					up = true;
				}
				/*If the current alien x axis is 10 pixels the left-most limit of the game frame.
				  Set left to false and up to true*/
				if(aliens[i].getX() <= 30)
				{
					left=false;
					down = true;
				}
				
				/*If the current alien y axis is greater than the game's frame height devide by 3,
				  Set left to true and down to false*/
				if(aliens[i].getY() >= PANEL_HEIGHT/3)
				{
					down = false;
					right = true;
				}
				
				/*If the current alien y axis is less than 60 pixels from the game's frame y axis.
				  Set up to false and right to true*/
				if(aliens[i].getY() <= 60)
				{
					up = false;
					left = true;
				}
				
				//If left is set to true move the alien to the left.
				if(left)
				{
					aliens[i].moveLeft(alienSpeed);	
				}
				//If right is set to true move the alien to the right.
				if(right)
				{
					aliens[i].moveRight(alienSpeed);
				}
				//If up is set to true move the alien up.
				if(up)
				{	
					aliens[i].moveUp(alienSpeed);
				}
				//If down is set to true, move alien down.
				if(down)
				{
					aliens[i].moveDown(alienSpeed);
				}
				/*If the alien is alive and the alien is visible to the player and it is within the
			     shootFrequency distace from the ship. The alien at the current index can shoot.*/
				if(aliens[i].getLife()!=0 && aliens[i].getX()-ship.getX() <= shootFrequency
												  && aliens[i].getX()-ship.getX() >= -shootFrequency
												  && aliens[randomIndex].getX()<1200)
				{
					aliens[i].shootWeapon();
				}
			}catch(InterruptedException ie){}
		}
	}

	
	public void initialPosition(int option)
	{
		if(option == -1)
		{	System.out.println("Position java loaded");
			for(int i = 0; i< aliens.length;i++)
			{											
				if(i>=0 && i<4)
				{aliens[i].setLocation(aliens[i].getX(),aliens[i].getY()+(i%4*(50)));}
				else if(i==4)
				{aliens[i].setLocation(aliens[i].getX()-80,aliens[i].getY());}
				else if(i==5)
				{aliens[i].setLocation(aliens[i].getX()-40,aliens[i].getY());}
				else if(i==6)
				{aliens[i].setLocation(aliens[i].getX()+40,aliens[i].getY());}
				else if(i==7)
				{aliens[i].setLocation(aliens[i].getX()+80,aliens[i].getY());}
				else if(i==8)
				{aliens[i].setLocation(aliens[3].getX()-30,aliens[3].getY()-20);}
				else if(i==9)
				{aliens[i].setLocation(aliens[3].getX()-60,aliens[3].getY()-40);}
				else if(i==10)
				{aliens[i].setLocation(aliens[3].getX()+150,aliens[3].getY());}
				else if(i>=11 && i<=13)
				{aliens[i].setLocation(aliens[i-1].getX()+20,aliens[i-1].getY()-50);}
				else if(i>=14 && i<=16)
				{aliens[i].setLocation(aliens[i-1].getX()+20,aliens[i-1].getY()+50);}
				else if(i==17)
				{aliens[i].setLocation(aliens[i-2].getX()-40,aliens[i-2].getY());}
				else if(i==18)
				{aliens[i].setLocation(aliens[13].getX()+150,aliens[13].getY());}
				else if(i>=19 && i<=21)
				{aliens[i].setLocation(aliens[i-1].getX()+20,aliens[i-1].getY()+50);}
				else if(i>=22 && i<=24)
				{aliens[i].setLocation(aliens[i-1].getX()+20,aliens[i-1].getY()-50);}
				else if(i==25)
				{aliens[i].setLocation(aliens[21].getX()+150,aliens[21].getY());}
				else if(i>=26 && i<=28)
				{aliens[i].setLocation(aliens[i-1].getX()+20,aliens[i-1].getY()-50);}
				else if(i>=29 && i<=31)
				{aliens[i].setLocation(aliens[i-1].getX()+20,aliens[i-1].getY()+50);}
				else if(i==32)
				{aliens[i].setLocation(aliens[i-2].getX()-40,aliens[i-2].getY());}
			}
		}

		
		if(option == 0)
		{System.out.println("Position block");
			for(int i = 0; i< aliens.length;i++)
			{
				if(i>=0 && i<3)
				{aliens[i].setLocation(aliens[i].getX(),aliens[i].getY()+(i%3*(50)));}
				else if(i>=3 && i<6)
				{aliens[i].setLocation(aliens[i].getX()+50,aliens[i].getY()+(i%3*(50)));}
				else if(i>=6 && i<9)
				{aliens[i].setLocation(aliens[i].getX()+100,aliens[i].getY()+(i%3*(50)));}
				else if(i>=9 && i<12)
				{aliens[i].setLocation(aliens[i].getX()+150,aliens[i].getY()+(i%3*(50)));}
				else if(i>=12 && i<15)
				{aliens[i].setLocation(aliens[i].getX()+200,aliens[i].getY()+(i%3*(50)));}
				else if(i>=15 && i<18)
				{aliens[i].setLocation(aliens[i].getX()+250,aliens[i].getY()+(i%3*(50)));}
				else if(i>=18 && i<21)
				{aliens[i].setLocation(aliens[i].getX()+300,aliens[i].getY()+(i%3*(50)));}
				else if(i>=21 && i<24)
				{aliens[i].setLocation(aliens[i].getX()+350,aliens[i].getY()+(i%3*(50)));}
				else if(i>=24 && i<27)
				{aliens[i].setLocation(aliens[i].getX()+400,aliens[i].getY()+(i%3*(50)));}
				else if(i>=27 && i<30)
				{aliens[i].setLocation(aliens[i].getX()+450,aliens[i].getY()+(i%3*(50)));}
				else if(i>=30 && i<33)
				{aliens[i].setLocation(aliens[i].getX()+500,aliens[i].getY()+(i%3*(50)));}
				else if(i>=33 && i<36)
				{aliens[i].setLocation(aliens[i].getX()+550,aliens[i].getY()+(i%3*(50)));}
				else if(i>=24 && i<27)
				{aliens[i].setLocation(aliens[i].getX()+600,aliens[i].getY()+(i%3*(50)));}
				else if(i>=27 && i<30)
				{aliens[i].setLocation(aliens[i].getX()+650,aliens[i].getY()+(i%3*(50)));}
				else if(i>=30 && i<33)
				{aliens[i].setLocation(aliens[i].getX()+700,aliens[i].getY()+(i%3*(50)));}
				else if(i>=33 && i<36)
				{aliens[i].setLocation(aliens[i].getX()+750,aliens[i].getY()+(i%3*(50)));}

			}
		}
		if(option == 1)
		{System.out.println("Position starting left");
			for(int i = 0; i< aliens.length;i++)
			{
				aliens[i].setLocation(0-(i*60+aliens[i].getX()),aliens[i].getY()); 
			}
		}
		if(option == 2)
		{System.out.println("Position starting right");
			for(int i = 0; i< aliens.length;i++)
			{
				aliens[i].setLocation((i*60+1200)-aliens[i].getX()+190,aliens[i].getY()); 
			}
		}
	
	}

	//The task given to the thread when it is invoked with the start method.
	//Depending of the level passed to the object call the animation that 
	//corresponds to the level with its speed and shootFrequency.
	public void run()
	{
		System.out.println(aliens.length);
		initialPosition(level);
		
		while(true)
		{
				switch(level){
					case -1:simulationOne(5,50);
								break;
					case 0: simulationOne(1+difficulty,50+difficulty*2);
								break;
					case 1: simulationThree(5+difficulty,difficulty);
								break;
					case 2: simulationTwo(5+difficulty,difficulty);
								break;}
						}
	}
}