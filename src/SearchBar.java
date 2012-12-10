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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchBar implements GUIComponent
{
	static boolean isOpen = false;
	
	private static JFrame frame;
	
	private JTextArea outputTextArea;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneNumberField;
	private JTextField bookingNumberField;
	
	private JTextField[] fields;
	
	static JButton searchButton;
	static JButton clearButton;
	static JButton closeButton;
	static JButton editButton;

			public class SearchListener implements ActionListener
			{	
				public void actionPerformed(ActionEvent e)
				{	
					// Checks to see if a value has been entered in any of the text fields.
						if(	firstNameField.getText().equalsIgnoreCase("") &&
							lastNameField.getText().equalsIgnoreCase("") &&
							phoneNumberField.getText().equalsIgnoreCase("") &&
							bookingNumberField.getText().equalsIgnoreCase(""))
						{
							outputTextArea.setText(null);
							outputTextArea.setText("You must enter at least one search parameter.");
							editButton.setEnabled(false);
						}
						
					// Checks if an entry is found in the database and returns the details on-screen.
						else
						{
							outputTextArea.setText(null);
							
							try
							{
								SearchFunctionPas.getEntry(firstNameField.getText(), lastNameField.getText(),
								phoneNumberField.getText(), bookingNumberField.getText());
							}
							
							catch (Exception e1)
							{
								e1.printStackTrace();
							}
							
							if(SearchFunctionPas.st.getItemCount() == 0)
							{
								outputTextArea.setText("");
								outputTextArea.setText("Passenger not found.");
								editButton.setEnabled(false);
							}
							
							else
							{
								for (int i = 0; i < SearchFunctionPas.st.getItemCount(); i++)
								{
									outputTextArea.append(SearchFunctionPas.st.getItem(i) + "\n");
									editButton.setEnabled(true);
								}
							}

					// Clears the list of search results setting it up for a new search.
						SearchFunctionPas.st.removeAll();
					}
				}
			}

			public class ClearListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					for(JTextField field : fields)
						field.setText(null);
					
					outputTextArea.setText(null);
					editButton.setEnabled(false);
				}
			}

			public class CloseListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					SearchBar.isOpen = false;
				}
			}
	
			public class EditListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					if(EditPassengerBar.isOpen)
					{
						
					}
					
					else
					{
						editButton.setEnabled(false);
						closeButton.setEnabled(false);
						clearButton.setEnabled(false);
						searchButton.setEnabled(false);
						
						new EditPassengerBar();
						isOpen = true;
					}
				}
			}

	public SearchBar()
	{
		makeFrame();
		makeButtons();
		
		makeTextFields();
		makeOutputPanel();
		
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
				frame.setLocation((int)(d.width*(1029f/1920f)), (int)(d.height*(431f/1080f)));
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
		// Sets up the search bar buttons.
			JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());
				buttonPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
		
			searchButton = new JButton("Search");
			clearButton = new JButton("Clear");
			closeButton = new JButton("Close");
			editButton = new JButton("Edit");
			
				searchButton.addActionListener(new SearchListener());
				clearButton.addActionListener(new ClearListener());
				closeButton.addActionListener(new CloseListener());
				editButton.addActionListener(new EditListener());
			
			JButton[] buttons = new JButton[4];
			buttons[0] = searchButton;
			buttons[1] = clearButton;
			buttons[2] = closeButton;
			buttons[3] = editButton;
			
				for(JButton button : buttons)
				{
					button.setBorderPainted(false);
					button.setFocusPainted(false);
					button.setForeground(Color.WHITE);
					button.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
					button.setFont(new Font("sansserif", Font.BOLD, 16));
					button.setPreferredSize(searchButton.getPreferredSize());
					buttonPanel.add(button);
				}
				
				editButton.setEnabled(false);
			
			frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	public void makeTextFields()
	{
		// Sets up the text fields.
			JPanel topPanel = new JPanel();
				topPanel.setLayout(new BorderLayout());
				topPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				
			JPanel searchPanel = new JPanel();
				searchPanel.setLayout(new GridLayout(0, 4, 10, 1));
				searchPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
			
			JLabel firstJLabel = new JLabel("First Name", JLabel.CENTER);
			JLabel lastJLabel = new JLabel("Last Name", JLabel.CENTER);
			JLabel phoneJLabel = new JLabel("Phone Number", JLabel.CENTER);
			JLabel bookingJLabel = new JLabel("Booking Number", JLabel.CENTER);
			
			JLabel[] labels = new JLabel[4];
				labels[0] = firstJLabel;
				labels[1] = lastJLabel;
				labels[2] = phoneJLabel;
				labels[3] = bookingJLabel;
				
				for(JLabel label : labels)
				{	
					label.setFont(new Font("sansserif", Font.BOLD, 12));
					label.setForeground(Color.WHITE);
					searchPanel.add(label);
				}
			
			firstNameField = new JTextField();
			lastNameField = new JTextField();
			phoneNumberField = new JTextField();
			bookingNumberField = new JTextField();
			
			fields = new JTextField[4];
				fields[0] = firstNameField;
				fields[1] = lastNameField;
				fields[2] = phoneNumberField;
				fields[3] = bookingNumberField;
				
				for(JTextField field : fields)
				{
					field.setFont(new Font("sansserif", Font.BOLD, 14));
					field.setBorder(null);
					field.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
					field.setForeground(Color.WHITE);
					field.setHorizontalAlignment(JTextField.CENTER);
					field.setText("");
					field.setCaretColor(Color.WHITE);
					searchPanel.add(field);
				}
	
			topPanel.add(searchPanel, BorderLayout.CENTER);
			topPanel.add(new JLabel(" "), BorderLayout.NORTH);
			topPanel.add(new JLabel(" "), BorderLayout.SOUTH);
			topPanel.add(new JLabel("   "), BorderLayout.WEST);
			topPanel.add(new JLabel("   "), BorderLayout.EAST);
			
			frame.add(topPanel, BorderLayout.NORTH);
	}
	
	public void makeOutputPanel()
	{
		// Sets up the output panel and its scroll panel.
			outputTextArea = new JTextArea();
				outputTextArea.setEditable(false);
				outputTextArea.setFont(new Font("sansserif", Font.BOLD, 16));
				
			JScrollPane scrollPane = new JScrollPane(outputTextArea);
				scrollPane.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
				scrollPane.setBorder(null);
				scrollPane.setWheelScrollingEnabled(true);
				
			frame.add(scrollPane, BorderLayout.CENTER);
	}
	
	public void showFrame() 
	{
		// Show the frame.
			frame.setVisible(true);
	}

	public static JFrame getFrame()
	{
		// A getter to make the frame accessible to other classes.
			return frame;
	}
}