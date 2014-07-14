/* ------------------------------------------------------------------|
|Designer: 			  Laerte Sousa													|
|Basic Information: This class extends  from JPanel.					   |
|Purpose: 			  The  purpose  of  this  class  is  to  add  an   |
|						  independent	background  image  to  another  		| 
|						  frame or  panel.											|	
|--------------------------------------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Image;
import java.applet.*;

class Background extends JPanel  {

	private Image img;			//Background  image
	private AudioClip audio;
	private final int WIDTH;	//Background  width
	private final int HEIGHT;	//Background  height
	
	/*--------------------------------------------------|
	|	CONSTRUCTOR													 |
	|--------------------------------------------------*/
	/* The  parameters  nessesary  to  initialize this	|
	|	constructor  is  Image,int,int. the  image  		|
	|	passed  will be  its  background  and  the 		|	
	|	integer  will be  its  width and  height(size).	|
	|																	|
	|	The  constructor  will also set  the  panel		|  
	|	layout  to  null  and  set  it  focusable.		|
	|--------------------------------------------------*/
	Background(Image i,AudioClip clip,int w, int h){
		img = i;
		audio = clip;
		WIDTH = w;
		HEIGHT = h;
		
		setLocation(0,0);
		setLayout(null);		//sets background  layout  to  none
		setOpaque(true);
		setFocusable(true);	//focus  on  the  panel
		setSize(0,0);			//sets  panel  size
		
	}
	public void playAudio()
	{
		audio.loop();
	}
	public void stopAudio()
	{
		audio.stop();
	}
   /*--------------------------------------------------|
	|	Background's  paint  method							 |
	|--------------------------------------------------*/
	/* This  method  will paint img  on  the  panel		|
	|	starting  from  the  location(0,0);			 		|
	|--------------------------------------------------*/
	public void paintComponent(Graphics g) {
		g = (Graphics2D) g;
		super.paintComponent(g);
		g.drawImage(img,0,0,this);
	}	
 
}