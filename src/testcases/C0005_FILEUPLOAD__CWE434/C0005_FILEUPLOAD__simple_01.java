package testcases.C0005_FILEUPLOAD__CWE434;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import testcasesupport.IO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class C0005_FILEUPLOAD__simple_01 {

    private String path;
    private String path_bak;
    private int size;

    public void bad(HttpServletRequest request, HttpServletResponse response, String savePath) throws IOException {
        int sizeLimit = 5 * 1024 * 1024 ;
        MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit,"euc-kr",new DefaultFileRenamePolicy());

        String stemail = request.getParameter("stemail");
        String stpwd = request.getParameter("stpwd");
        String stcontent = request.getParameter("stcontent");
        String stre_step = request.getParameter("stre_step");
        String stre_num = request.getParameter("stre_num");

        String fileName = multi.getFilesystemName("filename");

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = IO.getDBConnection();
            String sql = " INSERT INTO board(email,r_num,w_date,pwd,content,re_step,re_num,filename) "
                    + " values ( ?, 0, sysdate(), ?, ?, ?, ?, ? ) ";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, stemail);
            pstmt.setString(2, stpwd);
            pstmt.setString(3, stcontent);
            pstmt.setString(4, stre_step);
            pstmt.setString(5, stre_num);
            pstmt.setString(6, fileName);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } finally{
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                IO.logger.log(Level.WARNING, e.toString());
            }
        }

        /* FLAW : CWE-434 */
        createThumnail(savePath+"/"+fileName, savePath+"/"+"s_"+fileName, 150);
    }

    public void good(HttpServletRequest request, HttpServletResponse response, String savePath) throws IOException {
        int sizeLimit = 5 * 1024 * 1024 ;
        MultipartRequest multi = new MultipartRequest(request,savePath,sizeLimit,"euc-kr",new DefaultFileRenamePolicy());

        String stemail = request.getParameter("stemail");
        String stpwd = request.getParameter("stpwd");
        String stcontent = request.getParameter("stcontent");
        String stre_step = request.getParameter("stre_step");
        String stre_num = request.getParameter("stre_num");
        String fileName = multi.getFilesystemName("filename");

        Connection con = null;
        PreparedStatement pstmt = null;

        if (fileName != null) {
            String fileExt = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
            if (!"gif".equals(fileExt) && !"jpg".equals(fileExt) && !"png".equals(fileExt)) {
                alertMessage(response, "업로드 불가능한 파일입니다.");
            return;
        }
    }
        try {
            con = IO.getDBConnection();
            String sql = " INSERT INTO board(email,r_num,w_date,pwd,content,re_step,re_num,file-name) "
                    + " values ( ?, 0, sysdate(), ?, ?, ?, ?, ? ) ";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, stemail);
            pstmt.setString(2, stpwd);
            pstmt.setString(3, stcontent);
            pstmt.setString(4, stre_step);
            pstmt.setString(5, stre_num);
            pstmt.setString(6, fileName);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } finally{
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                IO.logger.log(Level.WARNING, e.toString());
            }
        }


        createThumnail(savePath+"/"+fileName, savePath+"/"+"s_"+fileName, 150);

}

    public void createThumnail(String path, String path_bak, int size) {
        this.path = path;
        this.path_bak = path_bak;
        this.size = size;
    }

    private void alertMessage(HttpServletResponse response, String s) throws IOException {
        PrintWriter o = response.getWriter();
        o.println("<script>alert('" + s + "');</script>");
    }
}
