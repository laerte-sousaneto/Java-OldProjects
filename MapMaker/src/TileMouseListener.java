import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class TileMouseListener implements MouseListener{
	private Floors tiles;
	private int index;
	private MiniMap map;
	private Menu menu;
	TileMouseListener(MiniMap map, Menu menu)
	{
		tiles = new Floors();
		index = 0;
		this.map = map;
		this.menu = menu;
	}
	
	public void mousePressed(MouseEvent e)
	{
		Tile tileSelected = (Tile)e.getSource();
		SelectionBox box = menu.getTileSelectionBox();
		ImageIcon[]icon = box.getImageIcons();
		int index = box.getBox().getSelectedIndex();
		tileSelected.setTileImage(icon[index].getImage());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		boolean option;
		if(map.isGridOn())
		{
			option = false;
		}
		else
		{
			option = true;
		}
		Tile tileSelected = (Tile)e.getSource();
		tileSelected.setGrid(option);
				
	}
	@Override
	public void mouseExited(MouseEvent e) {
		boolean option;
		if(map.isGridOn())
		{
			option = true;
		}
		else
		{
			option = false;
		}
		Tile tileSelected = (Tile)e.getSource();
		tileSelected.setGrid(option);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
