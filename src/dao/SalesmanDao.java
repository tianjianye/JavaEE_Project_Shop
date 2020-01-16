package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.ConnectDB;
import model.Salesman;

public class SalesmanDao {
	ConnectDB cdb=new ConnectDB();
	public void createTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="create table salesman (sid int not null primary key,sname varchar(255) not null, password varchar(255) not null)";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void dropTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="drop table if exists salesman";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void addSalesman(Salesman sman) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		int id=sman.getSalesmanId();
		String name=sman.getSalesmanName();
		String pwd=sman.getPwd();
		String sql="insert ignore into salesman values ("+id+",'"+name+"','"+pwd+"')";
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void deleteSalesman(Salesman sman) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		String name=sman.getSalesmanName();
		String pwd=sman.getPwd();
		String sql="delete from salesman where sname='"+name+"' and password='"+pwd+"'";
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public Salesman findSalesmanById(int sid) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		Salesman sman=null;
		String sql="select * from salesman where sid="+sid;
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while (rs.next()) {
			sman=new Salesman();
			sman.setSalesmanId(sid);
			sman.setPwd(rs.getString("password"));
			sman.setSalesmanName(rs.getString("sname"));
		}
		cdb.close(s,conn);
		return sman;
	}
	public Salesman findSalesmanByName(String sname) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		Salesman sman=null;
		String sql="select * from salesman where sname='"+sname+"'";
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while (rs.next()) {
			sman=new Salesman();
			sman.setSalesmanId(rs.getInt("sid"));
			sman.setPwd(rs.getString("password"));
			sman.setSalesmanName(sname);
		}
		cdb.close(s,conn);
		return sman;
	}
	public Salesman findSalesmanByNameAndPassword(String sname,String pwd) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		Salesman sman=null;
		String sql="select * from salesman where sname='"+sname+"' and password='"+pwd+"'";
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while (rs.next()) {
			sman=new Salesman();
			sman.setSalesmanId(rs.getInt("sid"));
			sman.setPwd(pwd);
			sman.setSalesmanName(sname);
		}
		cdb.close(s,conn);
		return sman;
	}
	public List<Salesman> listSalesman() throws SQLException{
		Connection conn=cdb.getConn();
		Salesman sman=null;
		List<Salesman> ls=new ArrayList<>();
		Statement s=null;
		String sql="select * from salesman";
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while(rs.next()) {
			sman=new Salesman();
			sman.setSalesmanId(rs.getInt("sid"));
			sman.setSalesmanName(rs.getString("sname"));
			sman.setPwd(rs.getString("password"));
			ls.add(sman);
		}
		cdb.close(s,conn);
		return ls;
	}
	public List<Salesman> listSalesmanWithoutAdmin() throws SQLException{
		Connection conn=cdb.getConn();
		Salesman sman=null;
		List<Salesman> ls=new ArrayList<>();
		Statement s=null;
		String sql="select * from salesman where sname!='admin'";
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while(rs.next()) {
			sman=new Salesman();
			sman.setSalesmanId(rs.getInt("sid"));
			sman.setSalesmanName(rs.getString("sname"));
			sman.setPwd(rs.getString("password"));
			ls.add(sman);
		}
		cdb.close(s,conn);
		return ls;
	}
}
