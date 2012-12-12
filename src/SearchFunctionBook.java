import java.awt.List;
import java.sql.ResultSet;

public class SearchFunctionBook
{	
	static List st = new List();
	
	// Searching the database.
	// Takes DataBase.getEntry(string fromcity, string tocity)
	// empty strings allowed.
		public static List getEntry(String qr1, String qr2) throws Exception
		{
			String query =	"SELECT DepartureDate," +
							" FlightID FROM Flights" + " WHERE FromCity LIKE '" + qr1 + "' AND ToCity LIKE '" + qr2 +
							"' ORDER BY DepartureDate";

			ResultSet rs = Database.getInstance().execute(query);
		
				while (rs.next())
				{
					for ( int i = 1; i < rs.getMetaData().getColumnCount(); i++)
						st.add(rs.getMetaData().getColumnName(i)+": "+rs.getString(rs.getMetaData().getColumnName(i)));
				}
			return st;
		}
}