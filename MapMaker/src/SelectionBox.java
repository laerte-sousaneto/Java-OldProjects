import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/*
 *  Author: Laerte Sousa
 *  Date: 4/26/2013
 *  Purpose: This class is meant to Design combo box needed to hold
 *  tile selection of images.
 *  Other Information: This 
 */
public class SelectionBox extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> box;
	private ComboBoxRenderer renderer;
	private ImageIcon[] icons;
	
	SelectionBox(ImageIcon[] selections)
	{	
		super(new BorderLayout());
		icons = selections;
		setupBox(this.getIntArray());
		setupPane(100,100);
	}
	protected void setupPane(int width, int height)
	{
		setPreferredSize(new Dimension(width,height));
		setMaximumSize(new Dimension(width,height));
		this.add(box,BorderLayout.CENTER);
		this.setVisible(true);
	}
	@SuppressWarnings("unchecked")
	protected void setupBox(Integer[] indexSelection)
	{
		renderer = new ComboBoxRenderer(icons);
		box = new JComboBox<Integer>(indexSelection);
		box.setPreferredSize(new Dimension(100,100));
		box.setRenderer(renderer);
	}
	SelectionBox(ImageIcon selections, int width, int height)
	{		
		//super(selections);
		setAlignmentX(JComboBox.CENTER_ALIGNMENT);
		setSize(width,height);
	}
	protected Integer[] getIntArray()
	{
		Integer[] array = new Integer[icons.length];
		for(int i = 0; i < icons.length ; i++)
		{
			array[i] = i;
		}
		return array;
	}
	public ImageIcon[] getImageIcons()
	{
		return icons;
	}
	public JComboBox<Integer> getBox()
	{
		return box;
	}
}
@SuppressWarnings("rawtypes")
class ComboBoxRenderer extends JLabel implements ListCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon[] icons;
	public ComboBoxRenderer(ImageIcon[] i) {
			icons = i;
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
	}

	/*
	 * This method finds the image and text corresponding
	 * to the selected value and returns the label, set up
	 * to display the text and image.
	 */
	public Component getListCellRendererComponent(JList list,Object value,int index,
                							boolean isSelected,boolean cellHasFocus) 
	{
		//Get the selected index. (The index param isn't
		//always valid, so just use the value.)
		int selectedIndex = ((Integer) value).intValue();

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} 
		else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		//Set the icon and text.  If icon was null, say so.
		ImageIcon icon = icons[selectedIndex];
		String pet = icons[selectedIndex].getDescription();
		setIcon(icon);
		if (icon != null) {
			setText(pet);
			setFont(list.getFont());
		} else 
		{
			setText("no image available");
		}

		return this;
	}
}

