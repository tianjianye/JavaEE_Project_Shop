package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import dao.*;
import db.ConnectDB;
import model.*;

public class Mainpage {
	static CategoryDao cd=new CategoryDao();
	static MarkDao md=new MarkDao();
	static ProduitDao pd=new ProduitDao();
	static CommandeDao cod=new CommandeDao();
	static SalesmanDao sd=new SalesmanDao();
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws SQLException  {
		createTables();
		importCSV("D:/ProgramData/MySQL/MySQL Server 8.0/Uploads/datacsv/category.CSV","category");
		importCSV("D:/ProgramData/MySQL/MySQL Server 8.0/Uploads/datacsv/mark.CSV","mark");
		importCSV("D:/ProgramData/MySQL/MySQL Server 8.0/Uploads/datacsv/produit.CSV","produit");
		importCSV("D:/ProgramData/MySQL/MySQL Server 8.0/Uploads/datacsv/salesman.CSV","salesman");
		//addData();
		//login();
	}
	public static void login() throws SQLException {
		Salesman s=null;
		while(s==null) {
			System.out.println("Please enter your name:");
			String name=sc.nextLine();
			System.out.println("Please enter your password:");
			String pwd=sc.nextLine();
			s=sd.findSalesmanByNameAndPassword(name, pwd);
			if(s==null) {
				System.out.println("Name or password incorrect! Please retry! ");
			}
		}
		manageCommande(s);
	}
	public static void manageCommande(Salesman s) throws SQLException {
		while(true) {
			System.out.println("Please enter your choice:");
			System.out.println("1.Add Commande");
			System.out.println("2.Delete Commande");
			System.out.println("3.Update Commande");
			System.out.println("4.List My Commande");
			System.out.println("5.List All Commande");
			System.out.println("6.Exit");
		    String choice = sc.nextLine();
		    switch (choice) {
		    	case "1":
		    		addCommande(s);break;
		    	case "2":
		    		deleteCommande(s);break;
		    	case "3":
		    		updateCommande(s);break;
		    	case "4":
		    		listCommandeBySalesman(s);break;
		    	case "5":
		    		listAllCommande();break;
		    	case "6":
		    		System.out.println("Thank you for your next visit!");
		    		System.exit(0);
		    	default:break;
		    }
	    }
	}
	public static void createTables() throws SQLException {
		cod.dropTable();
		sd.dropTable();
		pd.dropTable();
		md.dropTable();
		cd.dropTable();
		sd.createTable();
		cd.createTable();
		md.createTable();
		pd.createTable();
		cod.createTable();
		md.alterTable();
		pd.alterTable();
		cod.alterTable();	
	}
	public static void importCSV(String filepath,String tablename) throws SQLException {
		ConnectDB cdb=new ConnectDB();
		Connection conn=cdb.getConn();
		String sql=	"load data infile '"+filepath+"' into table "+tablename+ 
					" fields terminated by ';' optionally enclosed by '\"' escaped by '\"' " + 
					"lines terminated by '\\r\\n';"; 
		Statement s=null;
		s = conn.createStatement();
		s.execute(sql);
		cdb.close(s,conn);
				
	}
	public static void addData() throws SQLException {
		Category c1=new Category(1,"WATER");
		Category c2=new Category(2,"SODA");
		Category c3=new Category(3,"BEER");
		cd.addCategory(c1);
		cd.addCategory(c2);
		cd.addCategory(c3);
		Mark m1=new Mark(1,"EVIAN",c1);
		Mark m2=new Mark(2,"VITTEL",c1);
		Mark m3=new Mark(3,"COCA",c2);
		Mark m4=new Mark(4,"OASIS",c2);
		Mark m5=new Mark(5,"FANTA",c2);
		Mark m6=new Mark(6,"HEINEKEN",c3);
		md.addMark(m1);
		md.addMark(m2);
		md.addMark(m3);
		md.addMark(m4);
		md.addMark(m5);
		md.addMark(m6);
		Produit p1=new Produit(1,"FANTA 250ML",2.5,m5);
		Produit p2=new Produit(2,"FANTA 500ML",3,m5);
		Produit p3=new Produit(3,"COCA 250ML",2.5,m3);
		Produit p4=new Produit(4,"COCA 500ML",3,m3);
		pd.addProduit(p1);
		pd.addProduit(p2);
		pd.addProduit(p3);
		pd.addProduit(p4);
		Salesman s0=new Salesman(0,"admin","admin");
		Salesman s1=new Salesman(1,"Arthur","arthur");
		Salesman s2=new Salesman(2,"Beatrice","bea");
		Salesman s3=new Salesman(3,"Ceclie","cc");
		Salesman s4=new Salesman(4,"David","david");
		sd.addSalesman(s0);
		sd.addSalesman(s1);
		sd.addSalesman(s2);
		sd.addSalesman(s3);
		sd.addSalesman(s4);
	}
	public static void addCommande(Salesman s) throws SQLException {
		String sname=s.getSalesmanName();
		if(!sname.equals("admin")) {
			int coid=enterCommandeIdToAdd();
			int pid=enterProduitId();
			int quantity=enterCommandeNum();
			cod.addCommande(new Commande(coid,pd.findProduitById(pid),quantity,s));
		}
		else {
			int sid=enterSalesmanId();
			int coid=enterCommandeIdToAdd();
			int pid=enterProduitId();
			int quantity=enterCommandeNum();
			cod.addCommande(new Commande(coid,pd.findProduitById(pid),quantity,sd.findSalesmanById(sid)));
		}
	}
	public static void deleteCommande(Salesman s) throws SQLException {
		int coid=enterCommandeIdToModify(s);
		cod.deleteCommande(cod.findCommandeByID(coid));
	}
	public static void updateCommande(Salesman s) throws SQLException {
		String sname=s.getSalesmanName();
		int coid=enterCommandeIdToModify(s);
		Commande c=cod.findCommandeByID(coid);
		if(sname.equals("admin")) {
			System.out.println("Please enter your field to change:");
			System.out.println("1.Produit");
			System.out.println("2.Commande Number");
			System.out.println("3.Salesman");
			String choice=sc.nextLine();
			switch(choice) {
				case "1": 
					updateProduit(c);break;
				case "2":
					updatequantity(c);break;
				case "3":
					updateSalesman(c);break;
				default:
					break;
			}
		}
		else {
			System.out.println("Please enter your field to change:");
			System.out.println("1.Produit");
			System.out.println("2.Commande Number");
			String choice=sc.nextLine();
			switch(choice) {
				case "1": 
					updateProduit(c);break;
				case "2":
					updatequantity(c);break;
				default:
					break;
			}
		}
	}
	public static void updateProduit(Commande c) throws SQLException {
		int pid=enterProduitId();
		cod.updateCommandeByProduit(c, pid);
	}
	public static void updatequantity(Commande c) throws SQLException {
		int quantity=enterCommandeNum();
		cod.updateCommandeByquantity(c,quantity);
	}
	public static void updateSalesman(Commande c) throws SQLException {
		int sid=enterSalesmanId();
		cod.updateCommandeBySalesman(c, sid);
	}
	public static void listCommandeBySalesman(Salesman s) throws SQLException {
		if(!s.getSalesmanName().equals("admin")) {
			displayList(cod.findAllCommandesBySalesman(s));
		}
		else{
			displayList(cod.findAllCommandes());
		}
	}	
	public static void listAllCommande() throws SQLException {
		displayList(cod.findAllCommandes());
	}
	public static void listProduit() throws SQLException {
		displayList(pd.listProduit());
	}
	public static void listSalesman() throws SQLException {
		displayList(sd.listSalesman());
	}
	public static int enterSalesmanId() throws SQLException {
		listSalesman();
		Salesman s=null;
		int sid;
		while(true) {
			System.out.println("Please enter the salesman id:");
			sid=Integer.parseInt(sc.nextLine());
			s=sd.findSalesmanById(sid);
			if(s==null ||s.getSalesmanName().equals("admin")) {
				System.out.println("Salesman name not found, please retry!");
			}
			else {
				break;
			}
		}
		return sid;
	}
	public static int enterCommandeIdToAdd() throws SQLException {
		listAllCommande();
		Commande c=null;
		int coid;
		while(true) {
			System.out.println("Please enter your commande id:");
			coid=Integer.parseInt(sc.nextLine());
			c=cod.findCommandeByID(coid);
			if(c==null) {
				break;
			}
			else{
				System.out.println("Commande exist, please retry!");
			}
		}
		return coid;
	}
	public static int enterCommandeIdToModify(Salesman salesman) throws SQLException {
		listCommandeBySalesman(salesman);
		Commande c=null;
		int coid;
		while(true) {
			System.out.println("Please enter your commande id:");
			coid=Integer.parseInt(sc.nextLine());
			if(!salesman.getSalesmanName().equals("admin")) {
				c=cod.findCommandesByIdAndSalesman(coid, salesman);
			}
			else{
				c=cod.findCommandeByID(coid);
			}
			if(c!=null) {
				break;
			}
			else{
				System.out.println("Commande not found, please retry!");
			}
		}
		return coid;
	}
	public static int enterProduitId() throws SQLException {
		Produit p=null;
		int pid;
		listProduit();
		while (true) {
			System.out.println("Please enter your produit:");
			pid=Integer.parseInt(sc.nextLine());
			p=pd.findProduitById(pid);
			if(p!=null) {
				break;
			}
			else {
				System.out.println("Produit not found. Please retry!");
			}
		}
		return pid;
	}
	public static int enterCommandeNum() {
		int quantity;
		while(true) {
			System.out.println("Please enter your commande number:");
			quantity=Integer.parseInt(sc.nextLine());
			if(quantity>0) {
				break;
			}
			else {
				System.out.println("Number equal or less than zero. Please retry!");
			}
		}
		return quantity;
	}
	public static void displayList(List<?> list) {
		if(list!=null && !list.isEmpty()) {
			String className=list.get(0).getClass().getSimpleName();
			System.out.println("-----------------------List "+className+":----------------------");
			for(Object o:list) {
				System.out.println(o.toString());
			}
			System.out.println("-----------------------------------------------------------");
		}
		else {
			System.out.println("-----------------------------------------------------------");
		}
	}
}
