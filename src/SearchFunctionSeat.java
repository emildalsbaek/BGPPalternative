import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchFunctionSeat
{	
	static ArrayList<String> st = new ArrayList<String>();
	
	// Searching the database.
	// Takes DataBase.getEntry(int FlightID)
	// empty strings allowed.
		public static ArrayList<String> getEntry(int qr1) throws Exception
		{
			String query =	"SELECT Seats," +
							" ID FROM passengers" + " WHERE FlightID = '" + qr1 + "'";

			ResultSet rs = Database.getInstance().execute(query);
				while (rs.next())
				{
					for ( int i = 1; i < rs.getMetaData().getColumnCount(); i++)
					{
						String[] str = rs.getString(i).split(",");
						for( int k = 0; k < str.length; k++ )
							st.add(str[k]);
					}
				}	
			return st;
		}
}