import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class ImageSelection extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<ImageIcon> combobox;
	private Floors floors;
	
	ImageSelection(Floors f)
	{
		floors = f;
		
		combobox = new JComboBox<ImageIcon>(floors.getImageIconArray());
		combobox.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
		
		add(combobox);
	}
	public JComboBox<ImageIcon> getBox()
	{
		return combobox;
	}
}
