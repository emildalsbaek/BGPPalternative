import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
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
	
	static JComboBox<String> fromBox;
	static JComboBox<String> toBox;
	static JComboBox<String> dateBox;
	
	static JTextField firstNameField;
	static JTextField lastNameField;
	static JTextField phoneNumberField;
	
	private String[] fromArray = { "Select Departure", "Amsterdam", "Copenhagen", "Rønne", "Timbuktu" };
	private String[] toArray = { "Select Destination", "Amsterdam", "Copenhagen", "Rønne", "Timbuktu" };
	private String[] dateArray = { "Select Date" };

			public class FNListener implements KeyListener
			{
				public void keyPressed(KeyEvent e){}

				public void keyReleased(KeyEvent e){}

				public void keyTyped(KeyEvent e)
				{
					nextEnabler();
				}
			}
			
			public class LNListener implements KeyListener
			{
				public void keyPressed(KeyEvent e){}

				public void keyReleased(KeyEvent e){}

				public void keyTyped(KeyEvent e)
				{
					nextEnabler();
				}
			}
			
			public class PNListener implements KeyListener
			{
				public void keyPressed(KeyEvent e){}

				public void keyReleased(KeyEvent e){}

				public void keyTyped(KeyEvent e)
				{
					nextEnabler();
				}
			}
	
			public class FromListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{	
					refillToBox();
					resetDateBox();
					
					if(! fromBox.getSelectedItem().equals(toBox.getSelectedItem()))
					{
						toBox.removeItem(fromBox.getSelectedItem());
					}
					
					else if(fromBox.getSelectedItem().equals(toBox.getSelectedItem()))
					{
						fromBox.setSelectedIndex(0);
						toBox.setSelectedIndex(0);
						dateBox.setSelectedIndex(0);
					}
					
					nextEnabler();
				}
			}
			
			public class ToListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{	
					if(toBox.getSelectedIndex() != 0 && fromBox.getSelectedIndex() != 0)
					{
						try
						{
							SearchFunctionBook.getEntry((String)fromBox.getSelectedItem(), (String)toBox.getSelectedItem());
						}
						
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
						
							for(int i=0; i<SearchFunctionBook.st.getItemCount(); i++)
								dateBox.addItem(SearchFunctionBook.st.getItem(i));
															
						// Clears the list of search results setting it up for a new search.
							SearchFunctionBook.st.removeAll();
					}
					
					nextEnabler();
				}
			}
			
			public class DateListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					nextEnabler();
				}
			}
			
			public class NextListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					SeatsBar.cameFromBooking();
					
					try
					{
						new SeatsBar(	BookingBar.firstNameField.getText() +
										" " + BookingBar.lastNameField.getText(),
										BookingBar.phoneNumberField.getText(),
										BookingBar.fromBox.getSelectedItem().toString(),
										BookingBar.toBox.getSelectedItem().toString(),
										BookingBar.dateBox.getSelectedItem().toString(),
										SearchFunctionFlights.getFlightID(
										BookingBar.fromBox.getSelectedItem().toString(),
										BookingBar.toBox.getSelectedItem().toString(), 
										BookingBar.dateBox.getSelectedItem().toString()));
					}
					
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			}

			public class ClearListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					firstNameField.setText(null);
					lastNameField.setText(null);
					phoneNumberField.setText(null);
					fromBox.setSelectedIndex(0);
					toBox.setSelectedIndex(0);
					dateBox.setSelectedIndex(0);
					
					nextButton.setEnabled(false);
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
		// Sets up the from, to and date selection boxes.
			JPanel boxPanel = new JPanel();
				boxPanel.setLayout(new FlowLayout());
				boxPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
			
			JPanel layoutPanel = new JPanel();
				layoutPanel.setLayout(new BoxLayout(layoutPanel, 1));
				layoutPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				
			fromBox = new JComboBox<String>(fromArray);
			toBox = new JComboBox<String>(toArray);
			dateBox = new JComboBox<String>(dateArray);
			
				fromBox.addActionListener(new FromListener());
				toBox.addActionListener(new ToListener());
				dateBox.addActionListener(new DateListener());
							
				boxPanel.add(fromBox);
				boxPanel.add(toBox);
				boxPanel.add(dateBox);
				
				layoutPanel.add(new JLabel(" "));
				layoutPanel.add(new JLabel(" "));
				layoutPanel.add(new JLabel(" "));
				layoutPanel.add(new JLabel(" "));
				layoutPanel.add(new JLabel(" "));
				layoutPanel.add(new JLabel(" "));
				layoutPanel.add(boxPanel);
			
			frame.add(layoutPanel, BorderLayout.CENTER);
	}
	
	public void makeTextFields()
	{	
		// Sets up the passenger details text fields.
			JLabel passengerHeader = new JLabel("Enter passenger details");
				passengerHeader.setFont(new Font("sansserif", Font.BOLD, 16));
				passengerHeader.setForeground(Color.WHITE);
				
			JPanel textFieldPanel = new JPanel();
				textFieldPanel.setLayout(new GridLayout(0, 1));
				textFieldPanel.add(passengerHeader);
				textFieldPanel.add(new JLabel(" "));
				textFieldPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
			
			JLabel firstLabel = new JLabel("First name:");
			JLabel lastLabel = new JLabel("Last name:");
			JLabel phoneLabel = new JLabel("Phone number:");
			
				JLabel[] labels = new JLabel[3];
					labels[0] = firstLabel;
					labels[1] = lastLabel;
					labels[2] = phoneLabel;
					
					for(JLabel label : labels)
					{
						label.setForeground(Color.white);
					}
					
			firstNameField = new JTextField();
			lastNameField = new JTextField();
			phoneNumberField = new JTextField();
			
				firstNameField.addKeyListener(new FNListener());
				lastNameField.addKeyListener(new LNListener());
				phoneNumberField.addKeyListener(new PNListener());
			
				JTextField[] fields = new JTextField[3];
					fields[0] = firstNameField;
					fields[1] = lastNameField;
					fields[2] = phoneNumberField;
					
					for(JTextField field : fields)
					{
						field.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
						field.setForeground(Color.WHITE);
						field.setCaretColor(Color.WHITE);
						field.setHorizontalAlignment(JTextField.CENTER);
						field.setBorder(null);
					}
			
					for(int i=0; i<labels.length && i<fields.length; i++)
					{
						textFieldPanel.add(labels[i]);
						textFieldPanel.add(fields[i]);
					}
			
			JPanel layoutPanel = new JPanel();
				layoutPanel.setLayout(new FlowLayout());
				layoutPanel.add(textFieldPanel);
				layoutPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				
			frame.add(layoutPanel, BorderLayout.WEST);
	}
	
	public void showFrame() 
	{
		frame.setVisible(true);
	}
	
	public void refillToBox()
	{
		toBox.removeAllItems();
		
		for(String destination : toArray)
			toBox.addItem(destination);
	}
	
	public void resetDateBox()
	{
		dateBox.removeAllItems();
		
		for(String date : dateArray)
			dateBox.addItem(date);
	}

	public void nextEnabler()
	{
		if(	fromBox.getSelectedIndex() != 0 && toBox.getSelectedIndex() != 0 && dateBox.getSelectedIndex() != 0 && 
			!firstNameField.getText().equalsIgnoreCase("") && !lastNameField.getText().equalsIgnoreCase("") &&
			!phoneNumberField.getText().equalsIgnoreCase("") && !(phoneNumberField.getText().length() >= 10))
		{
			nextButton.setEnabled(true);
		}
		else
			nextButton.setEnabled(false);
	}
	
	public static JFrame getFrame()
	{
		return frame;
	}
}