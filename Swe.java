import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Swe extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    PrintWriter out = response.getWriter();
    response.setContentType("text/html");

    String sfn, sln, sfa, sdb, spn, sem, sma, sbr;
    String url, dbid, dbpw;
    sfn = request.getParameter("a");
    sln = request.getParameter("b");
    sfa = request.getParameter("c");
    sdb = request.getParameter("d");
    spn = request.getParameter("e");
    sem = request.getParameter("f");
    sma = request.getParameter("g");
    sbr = request.getParameter("i");

    url = "jdbc:oracle:thin:@localhost:1521:xe";
    dbid = "system";
    dbpw = "vbit";

    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection c = DriverManager.getConnection(url, dbid, dbpw);

      try {
        PreparedStatement s = c.prepareStatement("insert into student1 values(?,?,?,?,?,?,?,?)");
        s.setString(1, sfn);
        s.setString(2, sln);
        s.setString(3, sfa);
        s.setString(4, sdb);
        s.setString(5, spn);
        s.setString(6, sem);
        s.setString(7, sma);
        s.setString(8, sbr);

        s.executeUpdate();
        out.println("<p style=\"font-size: 50px; font-weight: bold;\">Registered Successfully</p>");

        out.println("<form action=\"main.html\">");
      out.println("<p>hall ticket will be sent to your mail<p>");
      out.println("</form>");
      } finally {
        c.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
      out.println("Error in registration: " + e.getMessage());
    }
  }
}
