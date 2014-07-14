import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/* Author: Laerte Sousa
 * Date: 4/24/2013
 * 
 * Class Name: Tile
 * Purpose: Class was designed to hold images and details
 * of each tile the character will interact with.
 * 
 * Other Information: 
 * -Class extends from JPanel and use the method
 * paintComponent(Graphics g) to display the image over the Component.
 * -JPanel Component Layout is set to null.
 */
class Tile extends JPanel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Dimension of the tile;
	public static int width = 30;
	public static int height = 30;
	
	//Image and position to be displayed
	private Image img;
	private int x;
	private int y;
	
	//Defines if tile can be walked on or not
	private boolean walkable;
	
	//Default Constructor.
	Tile()
	{
		walkable = true;
		this.img = null;
		this.panelSetup();
	}
	//Constructor with parameter to define image.
	Tile(Image img)
	{
		walkable = true;
		this.img = img;
		this.panelSetup();
	}
	//Sets up JPanel Component according to class's information
	private void panelSetup()
	{
		setSize(width,height);
		setLocation(x,y);
		setVisible(true);
		setOpaque(false);
		setLayout(null);
	}
	//returns x-axis location
	public int getXaxis()
	{
		return this.x;
	}
	//returns y-axis location
	public int getYaxis()
	{
		return this.y;
	}
	//returns tile's image
	public Image getImage()
	{
		return img;
	}
	//returns if tile is walk-able or not in boolean.
	public boolean isWalkable()
	{
		return walkable;
	}
	//set both x-axis and y-axis location
	public void setMapLocation(int x, int y)
	{
		this.x =x;
		this.y = y;
	}
	//set x-axis location
	public void setXaxis(int x)
	{
		this.x =x;
	}
	//set y-axis location
	public void setYaxis(int y)
	{
		this.y =y;
	}
	//sets tile image and call repaint
	public void setTileImage(Image img)
	{
		this.img = img;
		repaint();
	}
	//sets tile's walk-able definition
	public void setWalkable(boolean value)
	{
		this.walkable = value;
	}
	//if true add borders to the tile, for
	//better reading during map design.
	public void setGrid(boolean mode)
	{
		if(mode == true)
		{
			this.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		else
		{
			this.setBorder(null);
		}
	}
	//paints image to component if img is not null.
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(img != null)
		{
			g.drawImage(img,0,0,this);
		}
	}
}
