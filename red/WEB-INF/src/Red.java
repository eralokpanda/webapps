import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Red extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		int data = Integer.parseInt(req.getParameter("data"));
		ServletContext context = getServletContext();
		String name = context.getInitParameter("name");
		context.setAttribute("regd","1101219110");
		ServletConfig config = getServletConfig();
		String clg = config.getInitParameter("clg");
		res.sendRedirect("/red/data?data="+data+"&name="+name+"&clg="+clg);
		
		

	}
}
