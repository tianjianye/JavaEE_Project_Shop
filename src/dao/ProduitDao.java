package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.ConnectDB;
import model.Produit;

public class ProduitDao {
	ConnectDB cdb=new ConnectDB();
	public void createTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="create table produit (pid int not null primary key,pname varchar(255) not null, price double not null,mid int not null)";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void dropTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="drop table if exists produit";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void alterTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="alter table produit add constraint FK_P foreign key (mid) references mark (mid)";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void addProduit(Produit p) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		int id=p.getPid();
		String name=p.getPname();
		double price=p.getPrice();
		int mid=p.getMark().getMid();
		String sql="insert ignore into produit values ("+id+",'"+name+"',"+price+","+mid+")";
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void deleteProduit(Produit p) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		String name=p.getPname();
		String sql="delete from produit where pname='"+name+"'";
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public Produit findProduitById(int pid) throws SQLException {
		Connection conn=cdb.getConn();
		Produit p=null;
		Statement s=null;
		MarkDao md=new MarkDao();
		String sql="select * from produit where pid="+pid;
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while(rs.next()) {
			p=new Produit();
			p.setPid(pid);
			p.setPname(rs.getString("pname"));
			p.setPrice(rs.getDouble("price"));
			p.setMark(md.findMarkById(rs.getInt("mid")));
		}
		cdb.close(s,conn);
		return p;
	}
	public List<Produit> listProduit() throws SQLException{
		Connection conn=cdb.getConn();
		Produit p=null;
		List<Produit> lp=new ArrayList<>();
		Statement s=null;
		MarkDao md=new MarkDao();
		String sql="select * from produit";
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while(rs.next()) {
			p=new Produit();
			p.setPid(rs.getInt("pid"));
			p.setPname(rs.getString("pname"));
			p.setPrice(rs.getDouble("price"));
			p.setMark(md.findMarkById(rs.getInt("mid")));
			lp.add(p);
		}
		cdb.close(s,conn);
		return lp;
	}
}
