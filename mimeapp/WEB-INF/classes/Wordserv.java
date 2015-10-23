import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Wordserv extends HttpServlet
{
public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
res.setContentType("application/msword");
PrintWriter pw = res.getWriter();
pw.println("<h1>hello</h1>");
pw.close();

}

}