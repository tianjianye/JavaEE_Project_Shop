package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
    public static String db_url="jdbc:mysql://localhost:3306/shop?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static String db_user="root";
    public static String db_password="root";
    public Connection getConn() throws SQLException{
        Connection conn=null;
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        conn=DriverManager.getConnection(db_url,db_user,db_password);
        return conn;
    }
    public void close(Statement state,Connection conn) throws SQLException{
        state.close();
        conn.close();
    }
    public void close(ResultSet rs,Statement state,Connection conn) throws SQLException{
        if(rs!=null){
        	rs.close();
        }
       	state.close();
		conn.close();
    }
}