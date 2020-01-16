package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommandeDao;
import dao.SalesmanDao;
import model.Commande;
import model.Salesman;

//@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login=request.getParameter("login");
		SalesmanDao sd=new SalesmanDao();
		CommandeDao cod=new CommandeDao();
		List<Commande> listCod=null;
		try {
			Salesman s=sd.findSalesmanByName(login);
			if(!login.equals("admin")) {
				listCod = cod.findAllCommandesBySalesman(s);
			}
			else {
				listCod= cod.findAllCommandes();
			}
			request.setAttribute("salesman", s.getSalesmanName());
			request.setAttribute("listCommande", listCod);
			request.getRequestDispatcher("listCommande.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post list");
		String login=request.getParameter("login");
		System.out.println(login);
		response.sendRedirect("addCommande?login="+login);
		//doGet(request, response);
	}

}
