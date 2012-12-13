import java.sql.ResultSet;

public class SearchFunctionpasID
{
	private static String FirstName;
	private static String LastName;
	
	protected static String name;
	protected static String phone;
	protected static String from;
	protected static String to;
	protected static String date;
	protected static int flightID;
	
	public static void setEntry(int passengerID) throws Exception
	{
		String query =	"SELECT FirstName, LastName, PhoneNumber, FlightID," + 
				" ID FROM passengers" +	" WHERE ID = '" + passengerID +"'";
		
		ResultSet rs = Database.getInstance().execute(query);
		
		while (rs.next()) {
			for ( int i = 1; i < rs.getMetaData().getColumnCount(); i++)
				if (i == 1) {
					FirstName = rs.getString(i);
				} else if (i == 2) {
					LastName = rs.getString(i);
				} else if (i == 3) {
					phone = rs.getString(i);
				} else if (i == 4)
					flightID = rs.getInt(i);
			name = FirstName + " " + LastName;
		}
		
		rs.close();
		
		String query2 = "SELECT FromCity, ToCity, DepartureDate," +
				" FlightID FROM Flights" + " WHERE FlightID = '" + flightID + "'";

		
		ResultSet rs2 = Database.getInstance().execute(query2);
		
		while (rs2.next()) {
			for ( int i = 1; i < rs2.getMetaData().getColumnCount(); i++)
				if (i == 1) {
					from = rs2.getString(i);
				} else if (i == 2) {
					to = rs2.getString(i);
				} else if (i == 3) {
					date = rs2.getString(i);
				}
		}
	}
	
	public static String getFirst()
	{
		return FirstName;
	}
	
	public static String getLast()
	{
		return LastName;
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
