import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ApplicationSetup extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MiniMap miniMap;
	private Menu menu;
	private IndexPanel x_axis;
	private IndexPanel y_axis;
	
	private int width;
	private int height;
	
	private int x;
	private int y;
	
	
	ApplicationSetup()
	{
		
		x = 10;
		y = 10;
		setupChildComponents();
		
		width = 1000;
		height = 700;
		setupMainComponent();
		
		add(y_axis,BorderLayout.WEST);
		add(miniMap.getPanel(),BorderLayout.CENTER);
		add(menu,BorderLayout.EAST);
		add(x_axis,BorderLayout.SOUTH);
	}
	public void setupMainComponent()
	{
		setLayout(new BorderLayout());
		setSize(width,height);
		setLocation(x,y);
	}
	public void setupChildComponents()
	{
		miniMap = new MiniMap(20,20);
		menu = new Menu(miniMap);
		x_axis = new IndexPanel(20,"x");
		y_axis = new IndexPanel(20,"y");
	}
	public static void main(String[]args)
	{
		JFrame frame = new JFrame();
		ApplicationSetup application = new ApplicationSetup();
		frame.add(application);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
