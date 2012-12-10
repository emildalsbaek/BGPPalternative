import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SystemBar implements GUIComponent
{	
	static boolean isOpen = false;
	
	private static JFrame frame;
	private JButton about;
	private JButton exit;
		
			public class ExitListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			}
	
			public class AboutListener implements ActionListener
			{	
				public void actionPerformed(ActionEvent e)
				{
					new AboutBar();
					frame.setVisible(false);
				}
			}
			
	public SystemBar()
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
			Dimension size = new Dimension((int)(d.width*(175f/1920f)), (int)(d.height*(41f/1080f)));
			
			frame = new JFrame();
				frame.setSize(size.width, size.height);
				frame.setUndecorated(true);
				frame.setResizable(false);
				frame.getContentPane().setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				frame.setLocation(d.width/2-size.width/2, (int)(d.height*(694f/1080f)));
				frame.setAlwaysOnTop(true);
	}

	public void makeButtons()
	{
		JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			buttonPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
		
		about = new JButton("About");
		exit = new JButton("Exit");
		
			about.addActionListener(new AboutListener());
			exit.addActionListener(new ExitListener());
		
		JButton[] buttons = new JButton[2];
			buttons[0] = about;
			buttons[1] = exit;
		
			for(JButton button : buttons)
			{
				button.setBorderPainted(false);
				button.setFocusPainted(false);
				button.setForeground(Color.WHITE);
				button.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
				button.setFont(new Font("sansserif", Font.BOLD, 16));
				button.setPreferredSize(about.getPreferredSize());
				buttonPanel.add(button);
			}
		
		frame.add(buttonPanel);
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