package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SalesmanDao;
import model.Salesman;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("error", "");
    	request.getRequestDispatcher("login.jsp").forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
    	String pwd = request.getParameter("password");
    	try {
    		SalesmanDao sd=new SalesmanDao();
        	Salesman s=sd.findSalesmanByNameAndPassword(login, pwd);
			if(s!=null) {
				response.sendRedirect("listCommande?login="+login);
	    	}
			else {
				request.setAttribute("error", "login failed!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
