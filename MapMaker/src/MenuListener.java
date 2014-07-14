import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class MenuListener implements MouseListener{
	private MiniMap map;
	
	MenuListener(MiniMap map)
	{
		this.map = map;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getText().equals("Show Grid"))
		{
			if(map.isGridOn())
			{
				map.showGrid(false);
			}
			else
			{
				map.showGrid(true);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
