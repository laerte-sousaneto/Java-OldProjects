import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/*
 *  Author: Laerte SOusa
 *  Date: 4/26/2013
 *  Purpose: Class was design to hold components and functions
 *  in order to interact with the MiniMap class and  its  components.
 *  Other Information: This class extends from JPanel class. It also
 *  has a static member of Type Floors.
 */

public class Menu extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Static public variable meant to hold various types of tile images.
	public static Floors floor = new Floors();
	
	//Defines the dimension of this main pane.
	private int width;
	private int height;
	
	//Holds the map attributes to be interacted with.
	private MiniMap map;
	
	//Holds the various type of tile images to be selected.
	private SelectionBox tileSelection;
	private JComboBox<String> layerSelection;
	private String[] layers = new String[]{"Layer 1","Layer 2","Layer 3"};
	
	//Default Constructor
	Menu()
	{
		this.map = null;
		width = 200;
		height = 200;
		selectorSetup();
		panelSetup();
		setupTiles();
	}
	//Constructor with MiniMap parameter
	Menu(MiniMap map)
	{
		this.map = map;
		width = 200;
		height = 400;
		selectorSetup();
		panelSetup();
		setupTiles();
	}
	//Function meant to setup menu's options/buttons.
	private void selectorSetup()
	{ 
		JButton save = new JButton("Save");
		JButton load = new JButton("Load");
		@SuppressWarnings("unused")
		FileChooser fileChooser = new FileChooser(map,load,save);
		JButton showgrid = new JButton("Show Grid");
		tileSelection = new SelectionBox(floor.getImageIconArray());
		layerSelection = new JComboBox<String>(layers);
		
		showgrid.addMouseListener(new MenuListener(map));
		
		save.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		load.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		showgrid.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		tileSelection.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		layerSelection.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		save.setMaximumSize(new Dimension(width-10,50));
		load.setMaximumSize(new Dimension(width-10,50));
		showgrid.setMaximumSize(new Dimension(width-10,50));
		layerSelection.setMaximumSize(new Dimension(width-10,50));
		
		add(save);
		add(load);
		add(showgrid);
		add(layerSelection);
		add(tileSelection);
		
	}
	//Function meant to setup this component layout and design.
	private void panelSetup()
	{
		setMaximumSize(new Dimension(width,height));
		setVisible(true);
		BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		setLayout(layout);
		setBorder(BorderFactory.createLineBorder(Color.green));
	}
	//Function meant to setupTiles on map.
	private void setupTiles()
	{
		map.addActionListener(this);
	}
	//returns tile selection box
	public SelectionBox getTileSelectionBox()
	{
		return tileSelection;
	}
}
