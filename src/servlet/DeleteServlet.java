package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommandeDao;
public class DeleteServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			deleteC(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("listCommande").forward(request, response);
	}
	protected void deleteC(HttpServletRequest request) throws SQLException {
		String stringCoid=request.getParameter("id");
		String login=request.getParameter("login");
		int coid=Integer.parseInt(stringCoid);
		CommandeDao cod=new CommandeDao();
		cod.deleteCommande(cod.findCommandeByID(coid));
		request.setAttribute("salesman", login);
	}

}
