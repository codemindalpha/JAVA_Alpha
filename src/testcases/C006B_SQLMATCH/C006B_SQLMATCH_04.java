package testcases.C006B_SQLMATCH;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class C006B_SQLMATCH_04
{
	public void bad4DIFFICULT(Connection con) throws Throwable {
		String where = "and address = ?";
		PreparedStatement p = con.prepareStatement("select * from people where (first_name = ? or last_name = ?) " + where);
		p.setString(1, "a");
		p.setString(1, "b");
		p.setString(1, "c");
	}
}
