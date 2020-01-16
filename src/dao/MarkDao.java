package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.ConnectDB;
import model.*;

public class MarkDao {
	ConnectDB cdb=new ConnectDB();
	public void createTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="create table mark (mid int not null primary key,mark varchar(255) not null, cid int not null)";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void dropTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="drop table if exists mark";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void alterTable() throws SQLException {
		Connection conn=cdb.getConn();
		String sql="alter table mark add constraint FK_M foreign key (cid) references category (cid)";
		Statement s=null;
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void addMark(Mark m) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		int id=m.getMid();
		String name=m.getMark();
		int cid=m.getCategory().getCid();
		String sql="insert ignore into mark values ("+id+",'"+name+"',"+cid+")";
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public void deleteMark(Mark m) throws SQLException {
		Connection conn=cdb.getConn();
		Statement s=null;
		String name=m.getMark();
		String sql="delete from mark where mark='"+name+"'";
		s = conn.createStatement();
		s.executeUpdate(sql);
		cdb.close(s,conn);
	}
	public Mark findMarkById(int mid) throws SQLException {
		Connection conn=cdb.getConn();
		CategoryDao cd=new CategoryDao();
		Mark m=new Mark();
		Statement s=null;
		String sql="select * from mark where mid="+mid;
		s = conn.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while(rs.next()) {
			m.setMid(mid);
			m.setMark(rs.getString("mark"));
			m.setCategory(cd.findCategoryById(rs.getInt("cid")));
		}
		cdb.close(s,conn);
		return m;
	}
}
