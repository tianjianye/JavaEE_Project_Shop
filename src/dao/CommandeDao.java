package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.ConnectDB;
import model.Commande;
import model.Salesman;

public class CommandeDao {
	ConnectDB cdb=new ConnectDB();
	public void createTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="create table commande (coid int not null primary key AUTO_INCREMENT,c_num int not null,pid int not null,quantity int not null,sid int not null)";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void dropTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="drop table if exists commande";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void alterTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql1="alter table commande add constraint FK_CP foreign key (pid) references produit (pid)";
		String sql2="alter table commande add constraint FK_CS foreign key (sid) references salesman(sid)";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql1);
		s.executeUpdate(sql2);
		cdb.close(s,conn);
	}
	
	public void addCommande(Commande c) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		int commandeNumber=c.getCommandeNumber();
		int pid=c.getProduit().getPid();
		int quantity=c.getQuantity();
		int sid=c.getSalesman().getSalesmanId();
		String sql="insert ignore into commande (c_num,pid,quantity,sid) values ("+commandeNumber+","+pid+","+quantity+","+sid+")";
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void deleteCommande(Commande c) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		int coid=c.getCommandeId();
		String sql="delete from commande where coid="+coid;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void updateCommandeByCnum(Commande c,int cnum) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		int coid=c.getCommandeId();
		String sql="update commande set c_num="+cnum+" where coid="+coid;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void updateCommandeByProduit(Commande c,int pid) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		int coid=c.getCommandeId();
		String sql="update commande set pid="+pid+" where coid="+coid;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void updateCommandeByquantity(Commande c,int quantity) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		int coid=c.getCommandeId();
		String sql="update commande set quantity="+quantity+" where coid="+coid;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void updateCommandeBySalesman(Commande c,int sid) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		int coid=c.getCommandeId();
		String sql="update commande set sid="+sid+" where coid="+coid;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public Commande findCommandeByID(int coid) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		Commande c=null;
		ProduitDao pd=new ProduitDao();
		SalesmanDao sd=new SalesmanDao();
		String sql="select * from commande where coid="+coid;
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while (rs.next()) {
			c=new Commande();
			c.setCommandeId(coid);
			c.setCommandeNumber(rs.getInt("c_num"));
			c.setProduit(pd.findProduitById(rs.getInt("pid")));
			c.setQuantity(rs.getInt("quantity"));
			c.setSalesman(sd.findSalesmanById(rs.getInt("sid")));
		}
		cdb.close(s,conn);
		return c;
	}
	public List<Commande> findAllCommandesBySalesman(Salesman salesman) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		Commande c=null;
		List<Commande> lc=new ArrayList<>();
		ProduitDao pd=new ProduitDao();
		int sid=salesman.getSalesmanId();
		String sql="select * from commande where sid="+sid;
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while (rs.next()) {
			c=new Commande();
			c.setCommandeId(rs.getInt("coid"));
			c.setCommandeNumber(rs.getInt("c_num"));
			c.setProduit(pd.findProduitById(rs.getInt("pid")));
			c.setQuantity(rs.getInt("quantity"));
			c.setSalesman(salesman);
			lc.add(c);
		}
		cdb.close(s,conn);
		return lc;
	}
	public Commande findCommandesByIdAndSalesman(int coid,Salesman salesman) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		Commande c=null;
		ProduitDao pd=new ProduitDao();
		int sid=salesman.getSalesmanId();
		String sql="select * from commande where sid="+sid+" and coid="+coid;
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while (rs.next()) {
			c=new Commande();
			c.setCommandeId(rs.getInt("coid"));
			c.setCommandeNumber(rs.getInt("c_num"));
			c.setProduit(pd.findProduitById(rs.getInt("pid")));
			c.setQuantity(rs.getInt("quantity"));
			c.setSalesman(salesman);
		}
		cdb.close(s,conn);
		return c;
	}
	public List<Commande> findAllCommandes() throws SQLException{
		Connection conn=cdb.getConn();
		Statement s=null;
		Commande c=null;
		List<Commande> lc=new ArrayList<>();
		ProduitDao pd=new ProduitDao();
		SalesmanDao sd=new SalesmanDao();
		String sql="select * from commande";
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while (rs.next()) {
			c=new Commande();
			c.setCommandeId(rs.getInt("coid"));
			c.setCommandeNumber(rs.getInt("c_num"));
			c.setProduit(pd.findProduitById(rs.getInt("pid")));
			c.setQuantity(rs.getInt("quantity"));
			c.setSalesman(sd.findSalesmanById(rs.getInt("sid")));
			lc.add(c);
		}
		cdb.close(s,conn);
		return lc;
	}
}
