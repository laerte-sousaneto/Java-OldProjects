import java.awt.Image;

import javax.swing.ImageIcon;


public class Floors {
	private ImageLoader loader;
	private Image[] imgs;
	private int tile_quantity = 15;
	private String[] path;
	
	Floors()
	{
		loader = new ImageLoader();
		path = new String[tile_quantity];
		loadPaths();
		imgs = loader.loadImages(path);
	}
	public void loadPaths()
	{
		String temp_path;
		for(int i =0; i< tile_quantity;i++)
		{
			temp_path = "image/tiles/tile"+i+".png";
			path[i] = temp_path;
		}
	}
	public Image getImage(int index)
	{
		if(index < imgs.length)
		{
			return imgs[index];
		}
		else
		{
			System.out.println("Index is out of bounds.");
			return null;
		}
	}
	public Image[] getImageArray()
	{
		return imgs;
	}
	public ImageIcon[] getImageIconArray()
	{
		ImageIcon[] icons = new ImageIcon[imgs.length];
		for(int i =0; i<imgs.length;i++)
		{
			icons[i] = new ImageIcon(imgs[i]);
			icons[i].setDescription(String.valueOf(i));
		}
		return icons;
	}
	public int getLength()
	{
		return imgs.length;
	}
}
