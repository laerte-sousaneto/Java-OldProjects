/* ------------------------------------------------------------------|
|Designer: 			  Laerte Sousa													|
|Basic Information: This class implements KeyListener.               |
|Purpose: 			  The purpose of this class is to listen to key		|
|						  events sent by the user keyboard.						|	
|--------------------------------------------------------------------*/
import java.awt.event.*;

public class MyKeyListener extends KeyAdapter {
	private ShipFrame ship; 			 //holds spaceship object
	private boolean pressed;
	
	/*--------------------------------------------------|
	|	CONSTRUCTOR													 |
	|--------------------------------------------------*/
	/* The parameters nessesary to initialize this		|
	|	constructor are SpaceShip,int,int. SpaceShip		|
	|	will be nessesary to manipulate it's behavier	|
	|	according to each key event being sent by the 	|
	|	user. The other 2 parameters will hold the main	|
	|	panel size.													|
	|--------------------------------------------------*/
	MyKeyListener(ShipFrame s) {
		ship = s;
	}
	/*--------------------------------------------------|
	|	keyPressed method											 |
	|--------------------------------------------------*/
	/*	Handles arraw keys and space events sent from 	|
	|	the user's keyboard, and makes sure that the		|
	|	SpaceShip object's location doesn't go out of	|
	|	boundaries.													|
	---------------------------------------------------*/
	public void keyPressed(KeyEvent ke) {
		int keyCode = ke.getKeyCode();
		int space = 30;
		pressed = true;
		
		if(keyCode == KeyEvent.KEY_RELEASED)
		{
			System.out.println("la");
		}
		try{
		if(keyCode == KeyEvent.VK_LEFT){
			ship.moveLeft(space);
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			ship.moveRight(space);
		}
		if(keyCode == KeyEvent.VK_UP){
			ship.moveUp(space);
		}
		if(keyCode == KeyEvent.VK_DOWN){
			ship.moveDown(space);
		}
		if(keyCode == KeyEvent.VK_SPACE)
		{
			ship.shootWeapon();
		}
		}catch(NullPointerException ex){System.out.println(ex.getLocalizedMessage());}
	}
	
}