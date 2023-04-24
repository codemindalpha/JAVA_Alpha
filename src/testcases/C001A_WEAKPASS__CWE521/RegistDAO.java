package testcases.C001A_WEAKPASS__CWE521;

import testcasesupport.IO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class RegistDAO {

    Connection con = null;
    Statement stmt = null;

    {
        try {
            con = IO.getDBConnection();
        } catch (SQLException e) {
            IO.logger.log(Level.WARNING, e.toString());
        }
    }


    public String regist(UserVO userVO) {
        String result = "";

        result = userVO.getId() + "님 가입이 완료되었습니다.";

        return result;
    }
}
