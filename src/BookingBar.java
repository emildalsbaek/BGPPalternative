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
import javax.swing.JPanel;

public class BookingBar implements GUIComponent 
{
	static boolean isOpen = false;

	private static JFrame frame;

	private JButton nextButton;
	private JButton clearButton;
	private JButton closeButton;

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
				
			JPanel emptyEast = new JPanel();
			JPanel emptyWest = new JPanel();
				emptyEast.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
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

	public void showFrame() 
	{
		frame.setVisible(true);
	}

	public static JFrame getFrame()
	{
		return frame;
	}
}