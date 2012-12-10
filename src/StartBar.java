import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartBar implements GUIComponent
{
	private JFrame frame;
	
	static JButton booking;
	static JButton flights;
	static JButton search;
	static JButton system;
	
			public class BookingListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					if(BookingBar.isOpen)
					{
						BookingBar.getFrame().setVisible(false);
						BookingBar.isOpen = false;
					}
					
					else
					{
						new BookingBar();
						BookingBar.isOpen = true;
					}
				}
			}
	
			public class FlightsListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{	
					if(FlightsBar.isOpen)
					{
						FlightsBar.getFrame().setVisible(false);
						FlightsBar.isOpen = false;
					}
					
					else
					{
						new FlightsBar();
						FlightsBar.isOpen = true;
					}
				}
			}
	
			public class SearchListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					if(SearchBar.isOpen)
					{
						SearchBar.getFrame().setVisible(false);
						SearchBar.isOpen = false;
					}
					
					else
					{
						new SearchBar();
					}
				}
			}
	
			public class SystemListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					if(SystemBar.isOpen)
					{
						SystemBar.getFrame().setVisible(false);
						SystemBar.isOpen = false;
					}
					
					else
					{
						new SystemBar();
					}
				}
			}
	
	public StartBar()
	{
		makeFrame();
		makeButtons();
		
		showFrame();
	}
	
	public void makeFrame()
	{
		// Sets up the frame and its dimensions.
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension size = new Dimension((int)(d.width*(128f/1920f)), (int)(d.height*(258/1080f)));	
			
			frame = new JFrame();
				frame.setSize(size.width, size.height);
				frame.setUndecorated(true);
				frame.setResizable(false);
				frame.getContentPane().setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				frame.setLocation((int)(d.width*(896f/1920f)), (int)(d.height*(431f/1080f)));
	}
	
	public void makeButtons()
	{
		// Sets up the buttons of the start bar.
			JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());
				buttonPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
			
			booking = new JButton("Booking");
			flights = new JButton("Flights");
			search = new JButton("Search");
			system = new JButton("System");
			
				booking.addActionListener(new BookingListener());
				flights.addActionListener(new FlightsListener());
				search.addActionListener(new SearchListener());
				system.addActionListener(new SystemListener());
			
			JButton[] buttons = new JButton[3];
				buttons[0] = booking;
				buttons[1] = flights;
				buttons[2] = search;
			
				for(JButton button : buttons)
				{
					button.setBorderPainted(false);
					button.setFocusPainted(false);
					button.setForeground(Color.WHITE);
					button.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
					button.setFont(new Font("sansserif", Font.BOLD, 16));
					button.setPreferredSize(booking.getPreferredSize());
					buttonPanel.add(button);
				}
			
			JLabel spacingLabel = new JLabel("");
				spacingLabel.setPreferredSize(booking.getPreferredSize());
				
			JLabel anotherSpacingLabel = new JLabel("");
				anotherSpacingLabel.setPreferredSize(booking.getPreferredSize());
				
			JLabel lastSpacingLabel = new JLabel("");
				lastSpacingLabel.setPreferredSize(booking.getPreferredSize());
			
			buttonPanel.add(spacingLabel);
			buttonPanel.add(anotherSpacingLabel);
			buttonPanel.add(lastSpacingLabel);
			
			system.setBorderPainted(false);
			system.setFocusPainted(false);
			system.setForeground(Color.WHITE);
			system.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
			system.setFont(new Font("sansserif", Font.PLAIN, 10));
			system.setPreferredSize(booking.getPreferredSize());
			buttonPanel.add(system);
			
			frame.add(buttonPanel);
	}
	
	public void showFrame()
	{
		// Shows the frame.
			frame.setVisible(true);
	}
}