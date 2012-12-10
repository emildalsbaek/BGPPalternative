import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutBar implements GUIComponent
{
	static boolean isOpen = false;
	
	private JDialog frame;
	private JButton close;
	
			public class CloseListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					SystemBar.isOpen = false;
					AboutBar.isOpen = false;
				}
			}
	
	public AboutBar()
	{
		makeFrame();
		makeButtons();
		
		showFrame();
		
		isOpen = true;
	}
	
	public void makeFrame()
	{
		// Sets up the contents of the about frame and its dimensions.
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension size = new Dimension((int)(d.width*(300f/1920f)), (int)(d.height*(200f/1080f)));
			
			frame = new JDialog();
				frame.setLayout(new BorderLayout());
				frame.setSize(size.width, size.height);
				frame.setUndecorated(true);
				frame.setResizable(false);
				frame.setLocation(d.width/2-size.width/2, (int)(d.height*(694f/1080f)));
				frame.setAlwaysOnTop(true);
				frame.setModal(true);
			
			JPanel aboutPanel = new JPanel();
				aboutPanel.setLayout(new GridLayout(0, 1));
				aboutPanel.setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
			
			JLabel l1 = new JLabel("EMMA BOOKING SYSTEMS", JLabel.CENTER);
				l1.setFont(new Font("sansserif", Font.BOLD, 16));
			JLabel l2 = new JLabel("version 0.1", JLabel.CENTER);
			JLabel l3 = new JLabel("Matt Jensen & Emil Dalsbæk 2012", JLabel.CENTER);
			JLabel l4 = new JLabel("All Rights Reserved", JLabel.CENTER);
			
			aboutPanel.add(l1);
			aboutPanel.add(l2);
			aboutPanel.add(l3);
			aboutPanel.add(l4);
			
			frame.add(aboutPanel, BorderLayout.CENTER);
	}
	
	public void makeButtons()
	{
		// Sets up the about frame close button.
			close = new JButton ("Close");
				close.setBackground(Color.getHSBColor(0.55f, 0.50f, 0.50f));
				close.setForeground(Color.WHITE);
				close.setBorderPainted(false);
				close.setFocusPainted(false);
				close.addActionListener(new CloseListener());
				
			frame.add(close, BorderLayout.SOUTH);
	}
	
	public void showFrame()
	{
		// Shows the frame.
			frame.setVisible(true);
	}	
}