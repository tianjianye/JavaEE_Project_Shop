package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommandeDao;
import model.Commande;
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String updateLogin=request.getParameter("updateLogin");
			String stringCoid = request.getParameter("id");
			int coid = Integer.parseInt(stringCoid);
	        CommandeDao cod = new CommandeDao();
	        Commande c=null;
			c = cod.findCommandeByID(coid);
			request.setAttribute("commande", c);
			request.setAttribute("updateLogin", updateLogin);
	        request.getRequestDispatcher("updateCommande.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updateLogin=request.getParameter("updateLogin");
		String commandeId=request.getParameter("id");
		String newProduitName=request.getParameter("pid");
		String newQuantity=request.getParameter("quantity");
		String newSalesmanName=request.getParameter("sid");
		String newCommandeNumber=request.getParameter("cnum");
		try {
			CommandeDao cod = new CommandeDao();
            int coid = Integer.parseInt(commandeId);
            int cnum = Integer.parseInt(newCommandeNumber);
            Commande c=cod.findCommandeByID(coid);
			int quantity=Integer.valueOf(newQuantity);
	        int pid=Integer.parseInt(newProduitName);
	        cod.updateCommandeByCnum(c, cnum);
	        cod.updateCommandeByquantity(c, quantity);
	        cod.updateCommandeByProduit(c, pid);
	        if(newSalesmanName!=null) {
	        	int sid=Integer.parseInt(newSalesmanName);
	        	cod.updateCommandeBySalesman(c, sid);
	        	response.sendRedirect("listCommande?login=admin");
	        }
	        else {
	        	response.sendRedirect("listCommande?login="+updateLogin);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        //request.getRequestDispatcher("listCommande").forward(request, response);
	}
}
