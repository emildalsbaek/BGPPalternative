import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditPassengerBar implements GUIComponent
{
	protected static boolean isOpen;
	protected static int passengerID;
	
	private JFrame frame;
	private static JFormattedTextField inputField;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private int number;
	
			public class ConfirmListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					number = Integer.valueOf(inputField.getText());
					
					try 
					{
						SearchFunctionpasID.setEntry(number);
					} 
					
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					
					new SeatsBar(	SearchFunctionpasID.getName(), SearchFunctionpasID.getPhone(),
									SearchFunctionpasID.getFrom(), SearchFunctionpasID.getTo(),
									SearchFunctionpasID.getDate(), SearchFunctionpasID.getFlightID());
					
					SeatsBar.fromEdit = true;
					
					frame.setVisible(false);
					isOpen = false;

					SearchBar.getFrame().setVisible(false);
					SearchBar.isOpen = false;
				}
			}

			public class CancelListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					
					SearchBar.editButton.setEnabled(true);
					SearchBar.closeButton.setEnabled(true);
					SearchBar.clearButton.setEnabled(true);
					SearchBar.searchButton.setEnabled(true);
				}
			}
	
	public EditPassengerBar()
	{
		makeFrame();
		makeButtons();
		makeTextField();
		
		showFrame();
		
		isOpen = true;
	}
			
	public void makeFrame()
	{
		// Sets up the contents of the edit confirm frame and its dimensions.
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension size = new Dimension((int)(d.width*(300f/1920f)), (int)(d.height*(200f/1080f)));
			
			frame = new JFrame();
				frame.setLayout(new BorderLayout());
				frame.setSize(size.width, size.height);
				frame.setUndecorated(true);
				frame.setResizable(false);
				frame.setLocation((int)(d.width*(1533f/1920f)), (int)(d.height*(431f/1080f)));
			
			JPanel emptyEast = new JPanel();
			JPanel emptyWest = new JPanel();
				emptyEast.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				emptyWest.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
			
			frame.add(emptyEast, BorderLayout.EAST);
			frame.add(emptyWest, BorderLayout.WEST);
	}

	public void makeButtons()
	{
		// Sets up the buttons of the frame.
			confirmButton = new JButton("Confirm");
				confirmButton.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
				confirmButton.setBorderPainted(false);
				confirmButton.setForeground(Color.WHITE);
				confirmButton.addActionListener(new ConfirmListener());
				confirmButton.setFocusPainted(false);
			
			cancelButton = new JButton("Cancel");
				cancelButton.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
				cancelButton.setBorderPainted(false);
				cancelButton.setForeground(Color.WHITE);
				cancelButton.addActionListener(new CancelListener());
				confirmButton.setFocusPainted(false);
		
			JPanel buttonPanel = new JPanel(new FlowLayout());
				buttonPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				buttonPanel.add(confirmButton);
				buttonPanel.add(cancelButton);
				
			frame.add(buttonPanel, BorderLayout.SOUTH);
	}

	public void makeTextField()
	{
		// Sets a text field to receive passenger IDs.
			inputField = new JFormattedTextField(NumberFormat.getInstance());
				inputField.setBorder(null);
				inputField.setFont(new Font("sansserif", Font.PLAIN, 20));
				inputField.setBorder(new EmptyBorder(12, 12, 12, 12));
				inputField.setHorizontalAlignment(JTextField.CENTER);
			
			frame.add(inputField, BorderLayout.CENTER);
			
		// Sets up the title text of the confirm frame.
			JLabel text = new JLabel("Please enter passenger ID", JLabel.CENTER);
			JPanel labelPanel = new JPanel();
				labelPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				labelPanel.add(text);
			
			frame.add(labelPanel, BorderLayout.NORTH);
	}
	
	public void showFrame()
	{
		frame.setVisible(true);
	}
	
	public static int getPassengerID()
	{
		passengerID = Integer.parseInt(inputField.getText().toString());
		
		return passengerID;
	}
}
