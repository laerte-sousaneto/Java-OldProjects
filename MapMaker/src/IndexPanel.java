import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class IndexPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[] labels;
	private int index_length;
	
	private int labels_width;
	private int labels_height;
	
	private String axis;
	
	IndexPanel(int index_length, String axis)
	{
		this.index_length = index_length;
		this.axis = axis.toLowerCase();
		labels_width = 30;
		labels_height = 30;
		setupLabels();
		setupPanel();
		
	}
	protected void setupPanel()
	{
		setLayout(null);
		if(axis.equals("x"))
		{
			setPreferredSize(new Dimension(labels_width*index_length,labels_height));
		}
		else if(axis.equals("y"))
		{
			setPreferredSize(new Dimension(labels_width,labels_height*index_length));
		}
		else
		{
			System.out.println("Invalid axis.");
		}
	}
	protected void setupLabels()
	{
		labels = new JLabel[index_length];
		JLabel label = new JLabel("x/y");
		label.setSize(labels_width,labels_height);
		label.setLocation(0,0);
		label.setHorizontalAlignment(JLabel.CENTER);
		int index = 0;
		int x = 0,y = 0;
		if(axis.equals("x"))
		{
			add(label);
			x = labels_width;
			y = 0;
		}
		else if(axis.equals("y"))
		{
			x = 0;
			y = 0;
		}
		
		for(int i = index;i < index_length; i++)
		{
			labels[i] = new JLabel(String.valueOf(i));
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			labels[i].setLocation(x,y);
			if(axis.equals("x"))
			{
				labels[i].setSize(labels_width,labels_height);
				x += labels_width;
			}
			else if(axis.equals("y"))
			{
				labels[i].setSize(labels_width,labels_height);
				y += labels_height;
			}
			else
			{
				System.out.println("Invalid axis.");
			}
			add(labels[i]);
		}
	}
}
