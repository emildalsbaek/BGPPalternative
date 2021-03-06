import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SeatsBar implements GUIComponent
{	
	public static boolean fromEdit;

	private String name;
	private String phone;
	private String from;
	private String to;
	private String date;
	private int flightID;
	
	private ArrayList<JButton> selectedSeats = new ArrayList<JButton>();
	private ArrayList<JButton> seatsArrayList = new ArrayList<JButton>();
	
	private JFrame frame;
	
	private JButton backButton;
	private JButton closeButton;
	private JButton clearButton;
	private JButton cancelButton;
	private JButton confirmButton;
	
	private JPanel northSeats;
	private JPanel southSeats;
	
	private static JLabel flightIDLabel;
	private String seatString;
	
			public class Seat
			{
				protected JButton seat;
				
						public class SeatListener implements MouseListener
						{
							public void mouseClicked(MouseEvent e){}
		
							public void mouseEntered(MouseEvent e){}
							
							public void mouseExited(MouseEvent e){}
		
							public void mousePressed(MouseEvent e){}
		
							public void mouseReleased(MouseEvent e)
							{
								if(seat.getIcon().toString().equals("seatTaken.PNG"))
								{
									
								}
								
								else if(seat.isSelected())
									
								{
									seat.setSelected(false);
									selectedSeats.remove(seat);
								}
								
								else
								{
									seat.setSelected(true);
									selectedSeats.add(seat);
								}	
							}
							
						}
				
				public Seat(String letter, int number)
				{
					seat = new JButton(new ImageIcon("seatFree.PNG"));
						seat.setBorderPainted(false);
						seat.setBorder(null);
						seat.setBackground(Color.WHITE);
						seat.setFocusPainted(false);
						seat.setToolTipText(letter + number);
						seat.addMouseListener(new SeatListener());
						seat.setSelectedIcon(new ImageIcon("seatSelected.PNG"));
						seat.setName(letter + number);
						seatsArrayList.add(seat);
				}
			}
			
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
					if(BookingBar.isOpen)
						BookingBar.getFrame().setVisible(false);
					
					frame.setVisible(false);
				}
			}
	
			public class ClearListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					for(int i=0; i<selectedSeats.size(); i++)
					{
						selectedSeats.get(i).setSelected(false);
					}
					
					selectedSeats.clear();
				}
			}
			
			public class CancelListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						DelFunctionPas.delEntry(EditPassengerBar.getPassengerID());
					} 
					
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				
					frame.setVisible(false);
					
					JOptionPane.showMessageDialog(frame, "Reservation cancelled.");
					
				}
			}
			
			public class ConfirmListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{	
					if(selectedSeats.size() == 0 && fromEdit == true)
					{
						try
						{
							DelFunctionPas.delEntry(EditPassengerBar.getPassengerID());
						} 
						
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
					
						frame.setVisible(false);
						
						selectedSeats.clear();
						JOptionPane.showMessageDialog(frame, "Reservation cancelled.");
					}
					
						if(!((selectedSeats.size()) == 0))
						{
							if(fromEdit)
							{
								try
								{
								AddFunctionPas.setEntry(EditPassengerBar.passengerID,
														SearchFunctionpasID.getFirst(),
														SearchFunctionpasID.getLast(),
														phone, flightID, getSelectedSeats());
								}

								catch (Exception e1)
								{
								e1.printStackTrace();
								}
							}
						
							else
							{
								try 
								{
									AddFunctionPas.setEntry(0, 	BookingBar.firstNameField.getText(),
																BookingBar.lastNameField.getText(),
																BookingBar.phoneNumberField.getText(),
																flightID, getSelectedSeats());
								} 

								catch (Exception e1) 
								{
									e1.printStackTrace();
								}
							}

							frame.setVisible(false);

							if(BookingBar.isOpen)
							{
								BookingBar.getFrame().setVisible(false);
								BookingBar.isOpen = false;
							}
							
							selectedSeats.clear();
							JOptionPane.showMessageDialog(frame, "Passenger added to flight.");
						}
				}
			}
			
	public SeatsBar(String name, String phone, String from, String to, String date, int flightID)
	{	
		this.name = name;
		this.phone = phone;
		this.from = from;
		this.to = to;
		this.date = date;
		this.flightID = flightID;
		
		selectedSeats.clear();
		
		makeFrame();
		makeButtons();
		makePassengerPane();
		makeSeats();
		
		showFrame();
	}
	
	public void makeFrame()
	{
		// Sets up the frame and its dimensions.
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension size = new Dimension((int)(d.width*(1136f/1920f)), (int)(d.height*(350f/1080f)));
			
			frame = new JFrame();
				frame.setAlwaysOnTop(true);	
				frame.setSize(size.width, size.height);
				frame.setUndecorated(true);
				frame.setResizable(false);
				frame.getContentPane().setBackground(Color.getHSBColor(0.55f, 0.55f, 0.75f));
				frame.setLocation((int)(d.width*(392f/1920f)), (int)(d.height*(77f/1080f)));
				frame.setLayout(new BorderLayout());
			
			JLabel tail = new JLabel(new ImageIcon("largeeast.PNG"));
			JLabel nose = new JLabel(new ImageIcon("largewest.PNG"));	
			
			frame.add(tail, BorderLayout.EAST);
			frame.add(nose, BorderLayout.WEST);
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
				
				if(fromEdit == false)
					cancelButton.setEnabled(false);
			
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
				
			JLabel nameLabel = new JLabel(	"   Name: " + name);
			JLabel phoneLabel = new JLabel("   Phone: " + phone);
			JLabel fromLabel = new JLabel("   From: " + from);
			JLabel toLabel = new JLabel("   To: " + to);
			JLabel dateLabel = new JLabel("   Date: " + date);
			flightIDLabel = new JLabel("   FlightID: " + flightID);

			JLabel[] labels	= new JLabel[6];
				labels[0] = nameLabel;
				labels[1] = phoneLabel;
				labels[2] = fromLabel;
				labels[3] = toLabel;
				labels[4] = dateLabel;
				labels[5] = flightIDLabel;
				
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
				topwing.add(flightIDLabel);
				
			frame.add(topwing, BorderLayout.NORTH);
	}
	
	public void makeSeats()
	{
		// Sets up the seats.
			try
			{
				if(SearchFunctionFlights.getMaxSeats(from, to, date) == 40)
					seats40();
				
				else
					seats100();
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void seats100()
	{
		// Sets up the seats in a 100-seater plane model.
			JLabel body = new JLabel(new ImageIcon("largecenter.PNG"));
				body.setLayout(new BorderLayout());
		
			northSeats = new JPanel();
				northSeats.setLayout(new GridLayout(2, 20));
				northSeats.setOpaque(false);
		
			southSeats = new JPanel();
				southSeats.setLayout(new GridLayout(3, 20));
				southSeats.setOpaque(false);
				
			JLabel centerRow = new JLabel("");
		
				for(int i=1; i<=100; i++)
				{
					if(i<=20)
					{
						Seat seat = new Seat("A", i);
						northSeats.add(seat.seat);
					}
					
					else if(i<=40)
					{
						Seat seat = new Seat("B", i-20);
						northSeats.add(seat.seat);
					}
	
					else if(i<=60)
					{
						Seat seat = new Seat("C", i-40);
						southSeats.add(seat.seat);
					}
					
					else if(i<=80)
					{
						Seat seat = new Seat("D", i-60);
						southSeats.add(seat.seat);
					}
					
					else if(i<=100)
					{
						Seat seat = new Seat("E", i-80);
						southSeats.add(seat.seat);
					}
				}
				
				body.add(northSeats, BorderLayout.NORTH);
				body.add(southSeats, BorderLayout.SOUTH);
				body.add(centerRow, BorderLayout.CENTER);
			
			setTakenSeats();
			
			if(fromEdit == true)
				showBookedSeats();
				
			frame.add(body, BorderLayout.CENTER);	
		}
	
	public void seats40()
	{
		// Sets up the seats in a 40-seater plane model.
			JLabel body = new JLabel(new ImageIcon("largecenter.PNG"));
				body.setLayout(new BorderLayout());

			northSeats = new JPanel();
				northSeats.setLayout(new GridLayout(2, 10));
				northSeats.setOpaque(false);

			southSeats = new JPanel();
				southSeats.setLayout(new GridLayout(2, 10));
				southSeats.setOpaque(false);
		
			JLabel centerRow = new JLabel("");

				for(int i=1; i<=40; i++)
				{
					if(i<=10)
					{
						Seat seat = new Seat("A", i);
						northSeats.add(seat.seat);
					}
					
					else if(i<=20)
					{
						Seat seat = new Seat("B", i-10);
						northSeats.add(seat.seat);
					}
		
					else if(i<=30)
					{
						Seat seat = new Seat("C", i-20);
						southSeats.add(seat.seat);
					}
					
					else if(i<=40)
					{
						Seat seat = new Seat("D", i-30);
						southSeats.add(seat.seat);
					}
				}
	
			setTakenSeats();
			
			if(fromEdit == true)
				showBookedSeats();
				
			body.add(northSeats, BorderLayout.NORTH);
			body.add(southSeats, BorderLayout.SOUTH);
			body.add(centerRow, BorderLayout.CENTER);
	
		frame.add(body, BorderLayout.CENTER);	
	}
	
	public String getSelectedSeats()
	{	
		seatString = "";
		ArrayList<String> seats = new ArrayList<String>();
		
			for(int i=0; i<selectedSeats.size(); i++)
			{
				if(selectedSeats.get(i).isSelected())
				{
					seats.add(selectedSeats.get(i).getToolTipText());
				}
			}
			
			for(String seat : seats)
			{
					if(seatString == null)
					{
						seatString = "";
					}
					
				seatString = seatString + "," + seat;
			}
			
			if (seatString == null)
			{
			     return "All your problems are belong to us";
			}
			
			else
			{
				System.out.println(seatString);
				
				return seatString.toString();
			}
	}
	
	public void setTakenSeats()
	{
		try
		{
			SearchFunctionSeat.getEntry(flightID);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		for(int i=0; i<SearchFunctionSeat.st.size(); i++)
		{
			for(JButton seat : seatsArrayList)
			{
				if(seat.getToolTipText().equals(SearchFunctionSeat.st.get(i)))
				{
					seat.setIcon(new ImageIcon("seatTaken.PNG"));
				}
			}
		}
	}
	
	public void showBookedSeats()
	{
		int passengerID = EditPassengerBar.getPassengerID();
		
			try
			{
				SearchFunctionSeat.getReservedSeats(passengerID);
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			for(int i=0; i<SearchFunctionSeat.st2.size(); i++)
			{
				for(JButton seat : seatsArrayList)
				{
					if(seat.getToolTipText().equals(SearchFunctionSeat.st2.get(i)))
					{
						seat.setIcon(new ImageIcon("seatFree.PNG"));
						seat.setSelected(true);
						selectedSeats.add(seat);
					}
				}
			}
	}
	
	public void showFrame()
	{
		frame.setVisible(true);
	}

	public static void cameFromEdit()
	{
		fromEdit = true;
	}
	
	public static void cameFromBooking()
	{
		fromEdit = false;
	}
}