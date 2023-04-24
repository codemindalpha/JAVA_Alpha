package testcases.C001C_LEAKCOM__CWE615_Info_Exposure_by_Comment.s01;

import java.sql.*;

public class C001C_LEAKCOM__simple_01 {

    public void bad(String url, String user, String pass) throws SQLException {

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        /* FLAW : CWE-615 */
        // DB 연결 root / a1q2w3r3f2!@
        con = DriverManager.getConnection(url, user, pass);
        preparedStatement = con.prepareStatement("select * from test_table");
        resultSet = preparedStatement.executeQuery();
    }

    public void good(String url, String user, String pass) throws SQLException {

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // ID, 패스워드등의 중요정보는 주석에 포함해서는 안된다.
        con = DriverManager.getConnection(url, user, pass);
        preparedStatement = con.prepareStatement("select * from test_table");
        resultSet = preparedStatement.executeQuery();
    }

}
