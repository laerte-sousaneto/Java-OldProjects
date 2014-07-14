import java.awt.*;

class Collision extends Thread {
	
	private Player player;
	private Galaxy main;
	private GameStatus statusPanel;
	private ShipFrame userShip = null;
	private Weapon[] userWeapon = null;
	private ShipFrame[] alienShip = null;
	private Weapon[][] alienWeapon = null;
	private final int MAX_AMMO;
	private Level level;
	private final int PANEL_WIDTH;
	private final int PANEL_HEIGHT;

	
	Collision(Player p,Galaxy t,GameStatus gs,ShipFrame s,ShipFrame[] a,Level l,int w,int h)
	{
		player = p;
		main = t;
		statusPanel = gs;
		userShip = s;
		alienShip = a;
		level = l;
		loadWeapons();
		MAX_AMMO = ShipFrame.MAX_AMMO;
		PANEL_WIDTH = w;
		PANEL_HEIGHT = h;
	}
	public void loadWeapons()
	{
		userWeapon = userShip.getWeapon();
		alienWeapon = new Weapon[alienShip.length][MAX_AMMO];
		for(int a = 0; a< alienWeapon.length; a++)
		{
				alienWeapon[a] = alienShip[a].getWeapon() ; 
		}
	}
	public void checkUserHit(int alienShipIndex,int alienWeaponIndex)
	{
		if(!userShip.isDead())
		{
			boolean hit = isUserHit(alienShipIndex,alienWeaponIndex);
			
			if(hit)
			{
				alienWeapon[alienShipIndex][alienWeaponIndex].setVisible(false);
				alienWeapon[alienShipIndex][alienWeaponIndex].killThread();
				alienShip[alienShipIndex].loadSlot(alienWeaponIndex);
				userShip.decreaseLife();
				statusPanel.refreshLife();
				try{sleep(alienWeapon[alienShipIndex][alienWeaponIndex].getTime()*5);}catch(InterruptedException ie){}				
			}
		}
	}
	public boolean isUserHit(int alienShipIndex,int alienWeaponIndex)
	{
		boolean hit;
		AxisLimit userXLimit = new AxisLimit(userShip.getX(),userShip.getX()+userShip.getWidth());
		AxisLimit userYLimit = new AxisLimit(userShip.getY()-userShip.getHeight(),userShip.getY());
		Point alienWeaponLocation = new Point(alienWeapon[alienShipIndex][alienWeaponIndex].getX()
														,alienWeapon[alienShipIndex][alienWeaponIndex].getY());
		

		if((alienWeaponLocation.getX() >= userXLimit.getStart() && alienWeaponLocation.getX() <= userXLimit.getEnd()) 
			&& (alienWeaponLocation.getY() >= userYLimit.getStart() && alienWeaponLocation.getY() <= userYLimit.getEnd()))
		{
				hit = true;
		}
		else
		{
				hit = false;
		}
		
		return hit;
	}
	public boolean isAlienHit(int alienShipIndex, int userWeaponIndex)
	{
		boolean hit;
		AxisLimit alienXLimit = new AxisLimit(alienShip[alienShipIndex].getX(),alienShip[alienShipIndex].getX()+alienShip[alienShipIndex].getWidth());
	
		AxisLimit alienYLimit = new AxisLimit(alienShip[alienShipIndex].getY(),alienShip[alienShipIndex].getY()+alienShip[alienShipIndex].getHeight());
		Point weaponLocation = new Point(userWeapon[userWeaponIndex].getX(),userWeapon[userWeaponIndex].getY());

		if((weaponLocation.getX() >= alienXLimit.getStart() && weaponLocation.getX() <= alienXLimit.getEnd()) 
			&& (weaponLocation.getY() >= alienYLimit.getStart() && weaponLocation.getY() <= alienYLimit.getEnd()))
		{
				hit = true; 
		}
		else
		{
				hit = false;
		}
		
		return hit;
	}
  public void checkAlienHit(int alienIndex,int shipWeaponIndex)
	{
		if(!userShip.isDead() && !alienShip[alienIndex].isDead())
		{
			boolean hit = isAlienHit(alienIndex,shipWeaponIndex);
			
			if(hit)
			{
				userWeapon[shipWeaponIndex].setVisible(false);
				userWeapon[shipWeaponIndex].killThread();
				userShip.loadSlot(shipWeaponIndex);
				alienShip[alienIndex].decreaseLife();
				if(alienShip[alienIndex].getLife() == 0)
				{
					level.getProgress().increaseCounter();
					player.addScore(alienShip[alienIndex].getPoints());
					player.increaseKills();
					statusPanel.refreshScore();
					statusPanel.refreshKills();
				}
				try{sleep(userWeapon[shipWeaponIndex].getTime()*5);}catch(InterruptedException ie){}
			}
		}
	}	
	public void run()
	{
		while(level.getProgress().getCounter() != alienShip.length)
		{	
		
			for(int wIndex = 0;wIndex<userWeapon.length; wIndex++)
			{
				for(int aIndex = 0;aIndex < alienShip.length; aIndex++)
				{
					checkAlienHit(aIndex,wIndex);
				}
			}
			for(int aIndex = 0; aIndex< alienShip.length;aIndex++)
			{
				for(int wIndex = 0; wIndex < alienWeapon[aIndex].length; wIndex++)
				{
					checkUserHit(aIndex,wIndex);
				}
			}
			
		}
		level.advance();
		System.out.println(userShip.getLife());
		if(level.getLevel()==3)
		{level.setLevel(0);}
		System.out.println(level.getLevel());
		main.runGame(level.getLevel());
	}
	
}