import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Student extends GenericServlet {
    public void service(ServletRequest rq, ServletResponse rs)
            throws IOException, ServletException {
        PrintWriter p = rs.getWriter();
        rs.setContentType("text/html");
        String sid, spw, srn, sbr;
        String url, dbid, dbpw;
        sid = rq.getParameter("a");
        spw = rq.getParameter("b");
        srn = rq.getParameter("c");
        sbr = rq.getParameter("d");
        url = "jdbc:oracle:thin:@localhost:1521:xe";
        dbid = "system";
        dbpw = "vbit";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c = DriverManager.getConnection(url, dbid, dbpw);
            PreparedStatement s = c.prepareStatement("insert into student values(?,?,?,?)");
            s.setString(1, sid);
            s.setString(2, spw);
            s.setString(3, srn);
            s.setString(4, sbr);
            s.executeUpdate();
            RequestDispatcher rd = rq.getRequestDispatcher("log.html");
            rd.forward(rq, rs);

            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
