package testcases.C006A_SQLASTER;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class C006A_SQLASTER_03
{
	public void bad3(Connection con) throws Throwable {
		// FLAW:
		String where = "and (last_name LIKE '%')";
		PreparedStatement p = con.prepareStatement("SELECT first_name from people where (first_name = ? or last_name = ?) " + where);
		p.setString(1, "a");
		p.setString(2, "b");
	}
}
