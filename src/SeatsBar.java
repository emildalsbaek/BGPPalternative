import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SeatsBar implements GUIComponent
{
	JFrame frame;
	
	public void makeFrame()
	{
		// Sets up the frame and its dimensions.
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension size = new Dimension((int)(d.width*(1136f/1920f)), (int)(d.height*(350f/1080f)));
			
			frame = new JFrame();
				frame.setSize(size.width, size.height);
				frame.setUndecorated(false);
				frame.setResizable(true);
				frame.getContentPane().setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				frame.setLocation((int)(d.width*(392f/1920f)), (int)(d.height*(77f/1080f)));
				frame.setLayout(new BorderLayout());
				
			JLabel nose = new JLabel(new ImageIcon("nose.PNG"));
			JLabel tail = new JLabel(new ImageIcon("tail.PNG"));
			JLabel topwing = new JLabel(new ImageIcon("topwing.PNG"));
			JLabel bottomwing = new JLabel(new ImageIcon("botomwing.PNG"));
			JLabel body = new JLabel(new ImageIcon("body.PNG"));
			
			frame.add(nose, BorderLayout.WEST);
			frame.add(tail, BorderLayout.EAST);
			frame.add(topwing, BorderLayout.NORTH);
			frame.add(bottomwing, BorderLayout.SOUTH);
	}

	public void makeButtons()
	{
			
	}

	public void showFrame()
	{
		frame.setVisible(true);
	}
}