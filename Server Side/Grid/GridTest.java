import javax.swing.*;
import java.awt.*;

public class GridTest extends JFrame{
	public GridTest(){
	   setLayout(null);
		setSize(300,300);
		int x = 0;
		int y = 0;
		for(int i =0; i < 50;i++)
		{
			ImagePanel panel = new ImagePanel(new ImageIcon("empty_tyle.png").getImage());
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			panel.setSize(30,30);
			panel.setLocation(x,y);
			add(panel);
			if(x < this.getWidth())
			{
				x +=panel.getWidth();
			}
			else
			{
				x = 0;
				y += panel.getHeight();
			}
		}
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	public static void main(String[]args)
	{	
	  GridTest test = new GridTest();
	}
}
