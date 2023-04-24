package testcases.C0006_REDIRECT__CWE601_Open_Redirect.s01;


import testcasesupport.IO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class C0006_REDIRECT__simple_01 {

    public void bad(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String id = (String) session.getAttribute("id");
        String bn = request.getParameter("gubun");
        //외부로부터 입력받은 URL 이 검증없이 다른 사이트로 이동이 가능하여 안전하지 않다.
        String rd = request.getParameter("redirect");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        if (id.length() > 0) {
            String sql = "select level from customer where customer_id = ?";
            try {
                conn = IO.getDBConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();
                rs.next();
                if ("0".equals(rs.getString(1)) && "01AD".equals(bn)) {
                    /* FLAW : CWE-601 */
                    response.sendRedirect(rd);
                    return;
                }
            } catch (SQLException | IOException e) {
                IO.logger.log(Level.WARNING, e.toString());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    IO.logger.log(Level.WARNING, e.toString());
                }
            }

        }
    }

    public String good(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String allowedUrl[] = {"/main.do", "/login.jsp", "list.do"};

        String id = (String) session.getAttribute("id");
        String bn = request.getParameter("gubun");
        String rd = request.getParameter("redirect");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            rd = allowedUrl[Integer.parseInt(rd)];
        } catch (NumberFormatException e) {
            return "잘못된 접근입니다.";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "잘못된 입력입니다.";
        }

        if (id.length() > 0) {
            String sql = "select level from customer where customer_id = ?";
            try {
                conn = IO.getDBConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();
                rs.next();
                if ("0".equals(rs.getString(1)) && "01AD".equals(bn)) {
                    response.sendRedirect(rd);
                    return "success";
                }
            } catch (SQLException | IOException e) {
                IO.logger.log(Level.WARNING, e.toString());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    IO.logger.log(Level.WARNING, e.toString());
                }
            }
        }
        return "fail";
    }
}
