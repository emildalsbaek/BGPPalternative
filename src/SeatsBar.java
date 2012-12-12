import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeatsBar implements GUIComponent
{
	private JFrame frame;
	
	private JButton backButton;
	private JButton closeButton;
	private JButton clearButton;
	private JButton cancelButton;
	private JButton confirmButton;
	
			public class BackListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
				}
			}
			
			public class CloseListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
//					BookingBar.getFrame().setVisible(false);
					frame.setVisible(false);
				}
			}
	
			public class ClearListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			}
			
			public class CancelListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			}
			
			public class ConfirmListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			}
			
	public SeatsBar()
	{
		makeFrame();
		makeButtons();
		makePassengerPane();
		
		showFrame();
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
			
			JLabel tail = new JLabel(new ImageIcon("largeeast.PNG"));
			JLabel nose = new JLabel(new ImageIcon("largewest.PNG"));	
			JLabel body = new JLabel(new ImageIcon("largecenter.PNG"));
				body.setLayout(new GridLayout(5, 25));
				
				for(int i=0; i<125; i++)
					body.add(new JButton(new ImageIcon("seatIcon.PNG")));
			
			frame.add(tail, BorderLayout.EAST);
			frame.add(nose, BorderLayout.WEST);
			frame.add(body, BorderLayout.CENTER);
	}

	public void makeButtons()
	{		
		// Sets up the buttons - and some of the graphics - in the frame.
			JPanel flowWest = new JPanel();
				flowWest.setLayout(new FlowLayout(FlowLayout.LEFT));
				flowWest.setOpaque(false);
				
			JPanel flowEast = new JPanel();
				flowEast.setLayout(new FlowLayout(FlowLayout.RIGHT));
				flowEast.setOpaque(false);
			
			JPanel southPanel = new JPanel();
				southPanel.setLayout(new BorderLayout());
				southPanel.add(flowWest, BorderLayout.WEST);
				southPanel.add(flowEast, BorderLayout.EAST);
				southPanel.setOpaque(false);
			
			JLabel bottomwing = new JLabel(new ImageIcon("largesouth.PNG"));
				bottomwing.setLayout(new BorderLayout());
				bottomwing.add(southPanel, BorderLayout.SOUTH);
			
			backButton = new JButton("Back");
			clearButton = new JButton("Clear");
			closeButton = new JButton("Close");
			confirmButton = new JButton("Confirm Reservation");
			cancelButton = new JButton("Cancel Reservation");
			
				backButton.addActionListener(new BackListener());
				clearButton.addActionListener(new ClearListener());
				closeButton.addActionListener(new CloseListener());
				confirmButton.addActionListener(new ConfirmListener());
				cancelButton.addActionListener(new CancelListener());
			
				backButton.setPreferredSize(closeButton.getPreferredSize());
				clearButton.setPreferredSize(closeButton.getPreferredSize());
				cancelButton.setPreferredSize(confirmButton.getPreferredSize());
			
			JButton[] buttons = new JButton[5];
				buttons[0] = backButton;
				buttons[1] = clearButton;
				buttons[2] = closeButton;
				buttons[3] = confirmButton;
				buttons[4] = cancelButton;
				
					for(JButton button : buttons)
					{
						button.setBorderPainted(false);
						button.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
						button.setForeground(Color.WHITE);
						button.setFocusPainted(false);
					}
			
				flowWest.add(backButton);
				flowWest.add(closeButton);
				flowWest.add(clearButton);
				flowEast.add(confirmButton);
				flowEast.add(cancelButton);
				
			frame.add(bottomwing, BorderLayout.SOUTH);
	}
	
	public void makePassengerPane()
	{
		// Sets up the passenger information pane at the top left corner of the screen.
			JLabel topwing = new JLabel(new ImageIcon("largenorth.PNG"));
				topwing.setLayout(new GridLayout(0, 1));
				
			JLabel nameLabel = new JLabel(	"   Name: " + BookingBar.firstNameField.getText() +
											" " + BookingBar.lastNameField.getText());
			JLabel phoneLabel = new JLabel("   Phone: " + BookingBar.phoneNumberField.getText());
			JLabel fromLabel = new JLabel("   From: " + BookingBar.fromBox.getSelectedItem().toString());
			JLabel toLabel = new JLabel("   To: " + BookingBar.toBox.getSelectedItem().toString());
			JLabel dateLabel = new JLabel("   " + BookingBar.dateBox.getSelectedItem().toString());

			JLabel[] labels	= new JLabel[5];
				labels[0] = nameLabel;
				labels[1] = phoneLabel;
				labels[2] = fromLabel;
				labels[3] = toLabel;
				labels[4] = dateLabel;
				
					for(JLabel label : labels)
					{
						label.setOpaque(false);
						label.setForeground(Color.WHITE);
					}
			
				topwing.add(nameLabel);
				topwing.add(phoneLabel);
				topwing.add(fromLabel);
				topwing.add(toLabel);
				topwing.add(dateLabel);
				
			frame.add(topwing, BorderLayout.NORTH);
	}
	
	public void showFrame()
	{
		frame.setVisible(true);
	}
}