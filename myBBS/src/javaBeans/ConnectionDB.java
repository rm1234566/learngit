package javaBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private  final static String diver="com.mysql.jdbc.Driver";
    private  final static String url="jdbc:mysql://localhost:3306/bbs";
    private  final static String name="root";
    private  final static String password="1235869470";
    
    public Connection getConnectionDB()throws SQLException,Exception{
    	Connection con=null;
    	
    	Class.forName(diver);
    	con=DriverManager.getConnection(url, name, password);
    	
    	return con;
    }
    	
}