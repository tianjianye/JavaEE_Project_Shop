package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.ConnectDB;
import model.Category;

public class CategoryDao {
	ConnectDB cdb=new ConnectDB();
	public void createTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="create table category (cid int not null primary key,cname varchar(255) not null)";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void dropTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="drop table if exists category";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void addCategory(Category c) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		int id=c.getCid();
		String name=c.getCategory();
		String sql="insert ignore into category values ("+id+",'"+name+"')";
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void deleteCategory(Category c) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		String name=c.getCategory();
		String sql="delete from category where cname='"+name+"'";
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public Category findCategoryById(int cid) throws SQLException {
		Connection conn=cdb.getConn();
		Category c=new Category();
		Statement s=null;
		String sql="select * from category where cid="+cid;
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while(rs.next()) {
			c.setCid(cid);
			c.setCategory(rs.getString("cname"));
		}
		cdb.close(s,conn);
		return c;
	}
}
