import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class App extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response){

		response.setContentType("text/html;charset=UTF-8");
		
		try{
		
			PrintWriter out = response.getWriter();	
			out.println("hello");
			out.close();
		}
		catch (IOException error){
		
			System.err.println("IO Error occured:"+error);
		}	
		catch(Exception error){
			
			System.out.println("Error:"+error);
		}
	}
}
