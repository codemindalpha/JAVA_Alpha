package testcases.C0001_SQLI__CWE89_SQL_Injection.s01;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class C0001_SQL_Injection__simple_02 {

    public void bad(HttpServletRequest request, Connection con) throws SQLException {
        String gubun = request.getParameter("gubun");

        String sql = " select b_gubun "
                + " , a.idx "
                + " , a.b_id "
                + " , date_format(a.w_date, '%Y-%m-%d') "
                + " , a.pwd "
                + " , a.content "
                + " , b.idx "
                + " , a.security "
                + " from board a left outer join tail b on a.idx = b.b_id "
                + " where b_gubun = '" + gubun + "' ";

        Statement stmt = con.createStatement();
        new Thread(()-> {
            try {
                /* FLAW : CWE-89 */
                ResultSet rs = stmt.executeQuery(sql);
                if (!rs.next()) {
                    String id = rs.getString("idx");
                    System.out.println("idx:" + id);
                }
            } catch (SQLException sqlException) {
                System.out.println("SQLException ");
            }
        }).start();
        con.close();
    }
    public void good(HttpServletRequest request, Connection con) throws SQLException {
        String gubun = request.getParameter("gubun");

        String sql = "select b_gubun "
                + " , a.idx "
                + " , a.b_id "
                + " , date_format(a.w_date, '%Y-%m-%d') "
                + " , a.pwd "
                + " , a.content "
                + " , b.idx "
                + " , a.security "
                + " from board a left outer join tail b on a.idx = b.b_id "
                + " where b_gubun = ? ";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, gubun);

        new Thread(()-> {
            try {
                // FIX : CWE-89
                ResultSet rs = pstmt.executeQuery(sql);
                if (!rs.next()) {
                    String id = rs.getString("idx");
                    System.out.println("idx:" + id);
                }
            } catch (SQLException sqlException) {
                System.out.println("SQLException ");
            }
        }).start();
    }
}
