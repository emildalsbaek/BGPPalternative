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
	public void setEntry(int i1, String i2, String i3, int i4, int i5, String i6) throws Exception {
		
		if (i1 == 0) {
			Database.getInstance().execute("INSERT INTO passengers (FirstName, LastName, PhoneNumber, FlightID, Seats, BookingNumber) VALUES ("+ i2 +","+ i3 +","+ i4 +","+ i5 +","+ i6 +","+ bookingNrGen(i2, i3, i4) +")");
		} else {
			Database.getInstance().execute("INSERT INTO passengers (FirstName, LastName, PhoneNumber, FlightID, Seats, BookingNumber) WHERE ID ="+ i1 +" VALUES ("+ i2 +","+ i3 +","+ i4 +","+ i5 +","+ i6 +","+ bookingNrGen(i2, i3, i4) +")");
		}
	}
	
	private static int bookingNrGen(String curFirst, String curLast, int curPhone){
		int hash = 1;
		hash = hash + curFirst.hashCode();
		hash = hash + curLast.hashCode();
		hash = hash + curPhone;
		
		System.out.println(hash);
		
		return hash;
	}
}
