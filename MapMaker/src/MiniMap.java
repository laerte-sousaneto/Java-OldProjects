import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;
/*
 *  Author: Laerte Sousa
 *  Date: 4/24/2013
 *  Purpose: To a list of tiles in a minimap form,
 *  so then it can be placed on a bigger map.
 *  Other Information:
 *  -This class extends from JPanel component, and uses
 *  ArrayList<E> libraries to hold tiles.
 *  -Has function to add Event Listener to the tile's panel.
 */

public class MiniMap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	//Holds list of tile on the map
	private ArrayList<ArrayList<Tile>> tiles;
	
	//Dimension of this panel
	private int width;
	private int height;
	
	//Location to be displayed on main map
	private int x,y;
	
	//how many rows and columns of tiles are in this panel
	private int rows;
	private int columns;
	
	//grid lines are displayed or not
	private boolean showGrid;
	
	//default constructor
	MiniMap()
	{
		this.x = 0;
		this.y = 0;
		rows = 20;
		columns = 20;
		width = Tile.width*rows;
		height = Tile.height*columns;
		tiles = new ArrayList<ArrayList<Tile>>();
		panelSetup();
		setTiles();
	}
	
	//Constructor with parameters for rows and columns.
	MiniMap(int rows, int columns)
	{
		this.x = 0;
		this.y = 0;
		this.rows = rows;
		this.columns = columns;
		width = (Tile.width*rows);
		System.out.println(width);
		height = Tile.height*columns;
		tiles = new ArrayList<ArrayList<Tile>>();
		panelSetup();
		setTiles();		
	}
	//Constructor with parameters for map location, rows and columns. 
	MiniMap(int x, int y,int rows, int columns)
	{
		this.x = x;
		this.y = y;
		this.rows = rows;
		this.columns = columns;
		width = Tile.width*rows;
		height = Tile.height*columns;
		tiles = new ArrayList<ArrayList<Tile>>();
		panelSetup();
		setTiles();
	}
	//This function sets up the Panel Component of this class.
	private void panelSetup()
	{
		panel = new JPanel();
		panel.setLayout(null);
		panel.setLocation(x,y);
		panel.setPreferredSize(new Dimension(width,height));
		panel.setMinimumSize(new Dimension(width,height));
		panel.setMaximumSize(new Dimension(width,height));
		panel.setVisible(true);
	}
	//This function position and sets up the tiles on this map.
	public void setTiles()
	{
		for(int a = 0; a < rows; a++)
		{
			ArrayList<Tile> temp = new ArrayList<Tile>();
			for(int b = 0; b < columns; b++)
			{
				Tile tile = new Tile();
				tile.setLocation(a*Tile.width,b*Tile.height);
				temp.add(tile);
				panel.add(tile);
			}
			tiles.add(temp);
		}
	}
	//Adds Listener Actions to all tiles, and links it to a menu.
	public void addActionListener(Menu m)
	{
		for(int a = 0; a < tiles.size();a++)
		{
			for(int b = 0; b < tiles.get(a).size();b++)
			{
				tiles.get(a).get(b).addMouseListener(new TileMouseListener(this,m));
			}
		}
	}
	//Display or hid grid on tiles.
	public void showGrid(boolean value)
	{
		showGrid = value;
		for(int a = 0; a < tiles.size();a++)
		{
			for(int b = 0; b < tiles.get(a).size();b++)
			{
				tiles.get(a).get(b).setGrid(showGrid);
			}
		}
	}
	//Check if grid is on or not.
	public boolean isGridOn()
	{
		return showGrid;
	}
	
	//------------------------------
	//get and sets
	//------------------------------
	public JPanel getPanel()
	{
		return panel;
	}
	//Returns list with all the tiles objects contained in this map.
	public ArrayList<ArrayList<Tile>> getTiles()
	{
		return tiles;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public boolean isShowGrid() {
		return showGrid;
	}

	public void setShowGrid(boolean showGrid) {
		this.showGrid = showGrid;
	}

	
}