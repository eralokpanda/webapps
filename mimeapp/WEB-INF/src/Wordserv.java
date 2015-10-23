import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Wordserv extends HttpServlet
{
public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
res.setContentType("application/msword");
PrintWriter pw = res.getWriter();
pw.println("<table><tr><td>hello</td><td>hello</td><td>hello</td></tr></table>");
pw.close();

}

}