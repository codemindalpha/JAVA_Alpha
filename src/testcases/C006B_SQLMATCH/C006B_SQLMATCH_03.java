package testcases.C006B_SQLMATCH;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class C006B_SQLMATCH_03
{
	public void bad3(Connection con) throws Throwable {
		String where = "and address = ?";
		// FLAW:
		PreparedStatement p = con.prepareStatement("select * from people where (first_name = ? or last_name = ?) " + where);
		p.setString(1, "a");
		p.setLong(2, 0);
	}
}
