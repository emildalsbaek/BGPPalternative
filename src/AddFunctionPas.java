public class AddFunctionPas {
	
	/*	
	 *  used for inserting or changing data.
	 *  int i1 is ID OBS only used when changing a passenger entry
	 *	String i2 is FirstName
	 *  String i3 is LastName
	 *	int i4 is PhoneNumber in format xxxxxxxxxx
	 *	int i5 is FlightID
	 *	String i6 is Seats
	 *	empty strings not allowed!
	 */
	public static void setEntry(int i1, String i2, String i3, String i4, int i5, String i6) throws Exception {
		
		if (i1 == 0) {
			Database.getInstance().execute("INSERT INTO passengers (FirstName, LastName, PhoneNumber, FlightID, Seats, BookingNumber) VALUES ('"+ i2 +"','"+ i3 +"','"+ i4 +"','"+ i5 +"','"+ i6 +"','"+ bookingNrGen(i2, i3, i4) +"')");
		} else {
			Database.getInstance().execute("UPDATE passengers SET FirstName = '"+ i2 +"', LastName = '"+ i3 +"', PhoneNumber = '"+ i4 +"', FlightID = '"+ i5 +"', Seats = '"+ i6 +"', BookingNumber = '"+ bookingNrGen(i2, i3, i4) +"' WHERE ID = "+ i1 +"");
		}
	}
	
	private static int bookingNrGen(String curFirst, String curLast, String curPhone){
		int hash = 100000;
		hash = hash + curFirst.hashCode();
		
		return hash;
	}
}
