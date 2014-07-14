import java.awt.Image;

import javax.swing.ImageIcon;


class ImageLoader {	
	ImageLoader(){}
	
	public Image loadImage(String path)
	{
		ImageIcon icon = new ImageIcon(getClass().getResource(path));
		return icon.getImage();
	}
	public Image[] loadImages(String[]path)
	{
		Image[] imgs = new Image[path.length];
		for(int i = 0; i < path.length;i++)
		{
			ImageIcon icon = new ImageIcon(getClass().getResource(path[i]));
			imgs[i] = icon.getImage();
		}
		return imgs;
	}
}
