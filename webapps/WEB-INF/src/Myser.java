import javax.servlet.*;
import java.io.*;
public class Myser extends GenericServlet
{
public void init(ServletConfig conf) throws ServletException 
{
System.out.println("in init");
}
public void service(ServletRequest req,ServletResponse res) throws ServletException,IOException
{
System.out.println("in service");
}
public void destroy()
{
System.out.println("in destroy");
}
}