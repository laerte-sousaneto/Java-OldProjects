import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;



public class FileChooser implements ActionListener {
	
	
	private MiniMap parent;
	private JButton openButton;
	private JButton saveButton;
	
	private JFileChooser fc;
	
	FileChooser(MiniMap parent, JButton open, JButton save)
	{
		this.parent = parent;
		this.openButton = open;
		this.saveButton =  save;
		fc = new JFileChooser();
		openButton.addActionListener(this);
		saveButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == openButton)
		{
			int returnValue = fc.showOpenDialog(parent.getPanel());
			if(returnValue == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				System.out.println(file.getName());
				System.out.println(file.getPath());
			}
			else
			{
				 System.out.println("Command cancelled by user.");
			}
		}
		else if(e.getSource() == saveButton)
		{
			int returnValue = fc.showSaveDialog(parent.getPanel());
			if(returnValue == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				System.out.println(file.getName());
				System.out.println(file.getPath());
				try {
					FileOutputStream outputStream = new FileOutputStream(file);
					ObjectOutputStream out = new ObjectOutputStream(outputStream);
					out.writeObject(parent.getTiles());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else
			{
				 System.out.println("Command cancelled by user.");
			}
		}	
	}

}
