import java.sql.ResultSet;

//import java.awt.List;
//import java.rmi.server.ServerCloneException;
//import java.sql.ResultSet;
//
//import javax.management.Query;

public class SearchFunctionpasID
{
	protected static String name;
	protected static String phone;
	protected static String from;
	protected static String to;
	protected static String date;
	protected static int flightID;
	
	public static void setEntry(int passengerID) throws Exception
	{
		String query =	"SELECT FirstName, LastName, PhoneNumber, FlightID," + 
				" ID FROM passengers" +	" WHERE ID = '%" + passengerID +
				"%' ORDER BY ID";

		ResultSet rs = Database.getInstance().execute(query);
		
		name = rs.getString("FirstName") + " " + rs.getString("LastName");
		phone = rs.getString("PhoneNumber");
		flightID = rs.getInt("FlightID");
		
		String query2 = "SELECT FromCity, ToCity, DepartureDate" +
				" FlightID FROM Flights" + " WHERE FlightID = '" + flightID + "'";
		
		ResultSet rs2 = Database.getInstance().execute(query2);
		
		from = rs2.getString("FromCity");
		to = rs2.getString("ToCity");
		date = rs2.getString("DepartureDate");
	}
	
	public static String getName()
	{
		return name;
	}
	
	public static String getPhone()
	{
		return phone;
	}
	
	public static String getFrom()
	{
		return from;
	}
	
	public static String getTo()
	{
		return to;
	}
	
	public static String getDate()
	{
		return date;
	}
	
	public static int getFlightID()
	{
		return flightID;
	}
}
