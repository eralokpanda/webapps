import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Red2 extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		int data = Integer.parseInt(request.getParameter("data"));
		String name = request.getParameter("name");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServletContext context = getServletContext();
		String regd = (String)context.getAttribute("regd");
	
		out.println("<html><head> </head><body>mydata:"+(data*3)+"<br />Name:"+name+"<br />Registeration no:"+regd+"</body></html>");
		out.close();

	}
}
