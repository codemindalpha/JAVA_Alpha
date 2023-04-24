package testcases.C006A_SQLASTER;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class C006A_SQLASTER_02
{
	public void bad2(Connection con) throws Throwable {
		String where = "and address = ?";
		// FLAW:
		PreparedStatement p = con.prepareStatement("SELECT * from people where (first_name = ? or last_name = ?) " + where);
		p.setString(1, "a");
		p.setString(2, "b");
		p.setString(3, "c");
	}
}
