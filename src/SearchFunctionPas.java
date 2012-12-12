import java.awt.List;
import java.sql.ResultSet;

public class SearchFunctionPas
{	
	static List st = new List();
	
	// Searching the database.
	// Takes DataBase.getEntry(string firstName, string lastName,string PhoneNumber, string bookingnr)
	// empty strings allowed.
		public static List getEntry(String qr1, String qr2, String qr3, String qr4) throws Exception
		{
			String query =	"SELECT ID, FirstName, LastName, PhoneNumber, FlightID, Seats, BookingNumber," + 
							" ID FROM passengers" +	" WHERE FirstName LIKE '%" + qr1 + "%' AND LastName LIKE '%"
							+ qr2 + "%' AND PhoneNumber LIKE '%" + qr3 + "%' AND BookingNumber LIKE '%" + qr4 +
							"%' ORDER BY ID";

			ResultSet rs = Database.getInstance().execute(query);
		
				while (rs.next())
				{
					for ( int i = 1; i < rs.getMetaData().getColumnCount(); i++)
						st.add(rs.getMetaData().getColumnName(i)+": "+rs.getString(rs.getMetaData().getColumnName(i)));
					
					st.add("");
				}
				
			return st;
		}
}