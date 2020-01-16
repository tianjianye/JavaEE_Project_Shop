package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import model.*;

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login=request.getParameter("login");
		SalesmanDao sd=new SalesmanDao();
		try {
			Salesman s=sd.findSalesmanByName(login);
			request.setAttribute("salesman", s.getSalesmanName());
			request.setAttribute("login", login);
			request.getRequestDispatcher("addCommande.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringSid=request.getParameter("add_sid");
		String sname=request.getParameter("add_sname");
		//String login=request.getParameter("login");
		String stringNum=request.getParameter("cnum");
		try {
			if(sname!=null) { 
				String stringpid=request.getParameter("pid");
				int cnum=Integer.parseInt(stringNum);
				int pid=Integer.parseInt(stringpid);
				int quantity=Integer.parseInt(request.getParameter("quantity"));
				CommandeDao cod=new CommandeDao();
				ProduitDao pd=new ProduitDao();
				SalesmanDao sd=new SalesmanDao();
				Produit p=pd.findProduitById(pid);
				Salesman s=sd.findSalesmanByName(sname);
				Commande c=new Commande(cnum,p,quantity,s);
				cod.addCommande(c);
				response.sendRedirect("listCommande?login="+s.getSalesmanName());
				//request.getRequestDispatcher("listCommande").forward(request, response);
			}
			if(stringSid!=null) {
				String stringpid=request.getParameter("pid");
				int cnum=Integer.parseInt(stringNum);
				int pid=Integer.parseInt(stringpid);
				int quantity=Integer.parseInt(request.getParameter("quantity"));
				int sid=Integer.parseInt(stringSid);
				CommandeDao cod=new CommandeDao();
				ProduitDao pd=new ProduitDao();
				SalesmanDao sd=new SalesmanDao();
				Produit p=pd.findProduitById(pid);
				Salesman s=sd.findSalesmanById(sid);
				Commande c=new Commande(cnum,p,quantity,s);
				cod.addCommande(c);
				response.sendRedirect("listCommande?login=admin");
				//request.getRequestDispatcher("listCommande").forward(request, response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
