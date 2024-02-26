import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends GenericServlet {
    public void service(ServletRequest rq, ServletResponse rs)
            throws IOException, ServletException {
        PrintWriter p = rs.getWriter();
        rs.setContentType("text/html");
        String sid, spw, srn, sbr;
        String url, dbid, dbpw;
        sid = rq.getParameter("a");
        spw = rq.getParameter("b");
        url = "jdbc:oracle:thin:@localhost:1521:xe";
        dbid = "system";
        dbpw = "vbit";
        Connection c = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // Load the drivers
            c = DriverManager.getConnection(url, dbid, dbpw); // Register the driver and have the connection
            PreparedStatement s = c.prepareStatement("select * from student where id=? and pw=?"); // create SQL query
            s.setString(1, sid);
            s.setString(2, spw);
            ResultSet r = s.executeQuery();
            if (r.next()) {
                // Redirect to the .html page upon successful login
                RequestDispatcher dispatcher = rq.getRequestDispatcher("today.html");
                dispatcher.forward(rq, rs);
            } else {
                p.println("Wrong credentials");
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Print the error message and details
            p.println("Error: " + e.getMessage());
            e.printStackTrace(p);
        } finally {
            // Close the database connection in the finally block to ensure it's always closed
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                // Print the error message and details if closing connection fails
                p.println("Error closing connection: " + e.getMessage());
                e.printStackTrace(p);
            }
        }
    }
}
