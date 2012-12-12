import java.awt.List;
import java.sql.ResultSet;

public class SearchFunctionFlights
{	
	static List st = new List();
	static int SeatsTotal = 0;
	
	// Searching the database.
	// Takes DataBase.getEntry(string fromcity, string tocity)
	// empty strings allowed.
		public static List getEntry(String qr1, String qr2) throws Exception
		{
			String query =	"SELECT FlightID, FromCity, ToCity, DepartureDate, SeatsTotal," +
							" FlightID FROM Flights" + " WHERE FromCity LIKE '" + qr1 + "' AND ToCity LIKE '" + qr2 +
							"' ORDER BY FlightID";

			ResultSet rs = Database.getInstance().execute(query);
		
				while (rs.next())
				{
					for ( int i = 1; i < rs.getMetaData().getColumnCount(); i++)
						st.add(rs.getMetaData().getColumnName(i)+": "+rs.getString(rs.getMetaData().getColumnName(i)));
					
					st.add("");
				}
			return st;
		}
		
		public static int getMaxSeats(int qr1) throws Exception
		{
			String query =	"SELECT SeatsTotal," +
							" FlightID FROM Flights" + " WHERE FlightID = '" + qr1 + "'";

			ResultSet rs = Database.getInstance().execute(query);
		
				while (rs.next())
				{
					for ( int i = 1; i < rs.getMetaData().getColumnCount(); i++)
						SeatsTotal = rs.getInt(i);
				}
			return SeatsTotal;
		}
}