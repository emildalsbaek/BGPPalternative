import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookingBar implements GUIComponent 
{
	static boolean isOpen = false;

	private static JFrame frame;

	private JButton nextButton;
	private JButton clearButton;
	private JButton closeButton;
	
	private JComboBox<String> fromBox;
	private JComboBox<String> toBox;
	private JComboBox<String> dateBox;
	
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneNumberField;
	
	private String[] fromArray = { "Select Departure", "Amsterdam", "Copenhagen", "Rønne", "Timbuktu" };
	private String[] toArray = { "Select Destination", "Amsterdam", "Copenhagen", "Rønne", "Timbuktu" };

			public class NextListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			}

			public class ClearListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			}

			public class CloseListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					isOpen = false;
				}
			}

	public BookingBar()
	{
		makeFrame();
		makeButtons();
		makeComboBoxes();
		makeTextFields();
		
		showFrame();
		
		isOpen = true;
	}

	public void makeFrame()
	{
		// Sets up the frame and its dimensions.
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension size = new Dimension((int)(d.width*(1136f/1920f)), (int)(d.height*(350f/1080f)));
			
			frame = new JFrame();
				frame.setSize(size.width, size.height);
				frame.setUndecorated(true);
				frame.setResizable(false);
				frame.getContentPane().setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				frame.setLocation((int)(d.width*(392f/1920f)), (int)(d.height*(77f/1080f)));
				frame.setLayout(new BorderLayout());
				
			JPanel emptyWest = new JPanel();
				emptyWest.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				
			frame.add(emptyWest, BorderLayout.WEST);
	}

	public void makeButtons()
	{
		// Sets up the buttons in the right part of the frame.
			JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new GridLayout(0, 1, 5, 5));
				buttonPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
			
			nextButton = new JButton("Next");
			clearButton = new JButton("Clear");
			closeButton = new JButton("Close");
			
				nextButton.setEnabled(false);
			
				nextButton.addActionListener(new NextListener());
				clearButton.addActionListener(new ClearListener());
				closeButton.addActionListener(new CloseListener());
			
				nextButton.setPreferredSize(closeButton.getPreferredSize());
				clearButton.setPreferredSize(closeButton.getPreferredSize());
			
			JButton[] buttons = new JButton[3];
				buttons [0] = nextButton;
				buttons [1] = clearButton;
				buttons [2] = closeButton;
				
				for(JButton button : buttons)
				{
					button.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
					button.setForeground(Color.WHITE);
					button.setBorderPainted(false);
					button.setFocusPainted(false);
					button.setFont(new Font("sansserif", Font.BOLD, 16));
					buttonPanel.add(button);
				}
				
			JPanel bookingPanel = new JPanel();
				bookingPanel.setLayout(new FlowLayout());
				bookingPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));	
				bookingPanel.add(buttonPanel, BorderLayout.CENTER);
				
			frame.add(bookingPanel, BorderLayout.EAST);
	}

	public void makeComboBoxes()
	{
		
		
		fromBox = new JComboBox<String>(fromArray);
		toBox = new JComboBox<String>(toArray);
		dateBox = new JComboBox<String>();
		
		
	}
	
	public void makeTextFields()
	{
		JPanel textFieldPanel = new JPanel();
			textFieldPanel.setLayout(new FlowLayout());
		
		JLabel passengerHeader = new JLabel("Enter passenger details.");
		
		JLabel firstLabel = new JLabel("First name:");
		JLabel lastLabel = new JLabel("Last name:");
		JLabel phoneLabel = new JLabel("Phone number:");
		
		firstNameField = new JTextField();
		lastNameField = new JTextField();
		phoneNumberField = new JTextField();
	}
	
	public void showFrame() 
	{
		frame.setVisible(true);
	}

	public static JFrame getFrame()
	{
		return frame;
	}
}