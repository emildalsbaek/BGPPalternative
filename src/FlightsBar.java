import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FlightsBar implements GUIComponent
{
	static boolean isOpen = false;
	
	private static JFrame frame;
	private JButton viewButton;
	private JButton clearButton;
	private JButton closeButton;
	
	private JTextArea outputArea;
	
	private JComboBox<String> fromBox;
	private JComboBox<String> toBox;
	private String[] fromArray = {"Select Departure", "Amsterdam", "Copenhagen", "Rønne", "Timbuktu"};
	private String[] toArray = {"Select Destination", "Amsterdam", "Copenhagen", "Rønne", "Timbuktu"};
	
			public class FromListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{	
					if(! fromBox.getSelectedItem().equals(toBox.getSelectedItem()))
					{
						refillToBox();
						toBox.removeItem(fromBox.getSelectedItem());
					}
					
					else if(fromBox.getSelectedItem().equals(toBox.getSelectedItem()))
					{
						fromBox.setSelectedIndex(0);
						toBox.setSelectedIndex(0);
						refillToBox();
					}
				}
			}

			public class ToListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{

				}
			}
			
			public class ViewListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			}
	
			public class ClearListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					fromBox.setSelectedIndex(0);
					toBox.setSelectedIndex(0);
				}
			}
			
			public class CancelListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					isOpen = false;
				}
			}
	
	public FlightsBar()
	{
		makeFrame();
		makeButtons();
		makeComboBoxes();
		makeOutputField();
		
		showFrame();
		
		isOpen = true;
	}

	public void makeFrame()
	{
		// Sets up the frame and its dimensions.
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension size = new Dimension((int)(d.width*(500f/1920f)), (int)(d.height*(500f/1080f)));
			
			frame = new JFrame();
				frame.setSize(size.width, size.height);
				frame.setUndecorated(true);
				frame.setResizable(false);
				frame.getContentPane().setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				frame.setLocation((int)(d.width*(392f/1920f)), (int)(d.height*(431f/1080f)));
				frame.setLayout(new BorderLayout());
				
			JPanel emptyEast = new JPanel();
			JPanel emptyWest = new JPanel();
				emptyEast.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				emptyWest.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
			
			frame.add(emptyEast, BorderLayout.EAST);
			frame.add(emptyWest, BorderLayout.WEST);
	}

	public void makeButtons()
	{
		// Sets up the buttons at the bottom of the frame.
			JPanel buttonPanel = new JPanel();
				buttonPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				buttonPanel.setLayout(new FlowLayout());
			
			viewButton = new JButton("View");
			clearButton = new JButton("Clear");
			closeButton = new JButton("Close");
			
				viewButton.addActionListener(new ViewListener());
				clearButton.addActionListener(new ClearListener());
				closeButton.addActionListener(new CancelListener());
				
			JButton[] buttons = new JButton[3];
				buttons[0] = viewButton;
				buttons[1] = clearButton;
				buttons[2] = closeButton;
				
				for(JButton button : buttons)
				{
					button.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
					button.setForeground(Color.WHITE);
					button.setBorderPainted(false);
					button.setFocusPainted(false);
					button.setFont(new Font("sansserif", Font.BOLD, 16));
					buttonPanel.add(button);
				}
				
				viewButton.setPreferredSize(closeButton.getPreferredSize());
				clearButton.setPreferredSize(closeButton.getPreferredSize());
				
			frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	public void makeComboBoxes()
	{
		// Sets up the drop down selection menus.
			fromBox = new JComboBox<String>(fromArray);
				fromBox.setBorder(null);
				fromBox.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
				fromBox.setForeground(Color.WHITE);
				fromBox.setFocusable(false);
				fromBox.addActionListener(new FromListener());
				
			toBox = new JComboBox<String>(toArray);
				toBox.setBorder(null);
				toBox.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
				toBox.setForeground(Color.WHITE);
				toBox.setFocusable(false);
				toBox.addActionListener(new ToListener());
		
			JPanel comboJPanel = new JPanel();
				comboJPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				comboJPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
				comboJPanel.add(fromBox);
				comboJPanel.add(toBox);
			
			
			
			frame.add(comboJPanel, BorderLayout.NORTH);
	}
	
	public void makeOutputField()
	{
		outputArea = new JTextArea();
			outputArea.setEditable(false);
			outputArea.setFont(new Font("sansserif", Font.BOLD, 12));
		
		JPanel outputJPanel = new JPanel();
			outputJPanel.add(outputArea);
			
			JScrollPane scrollPanel = new JScrollPane(outputArea);
				scrollPanel.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
				scrollPanel.setBorder(null);
				scrollPanel.setWheelScrollingEnabled(true);
				
		frame.add(scrollPanel, BorderLayout.CENTER);	
	}
	
	public void showFrame()
	{
		// Shows the frame on screen.
			frame.setVisible(true);
	}
	
	public void refillToBox()
	{
		toBox.removeAllItems();
		
		for(String string : toArray)
			toBox.addItem(string);
	}
	
	public static JFrame getFrame()
	{
		return frame;
	}
}