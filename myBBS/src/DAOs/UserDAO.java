package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaBeans.ConnectionDB;
import javaBeans.User;

public class UserDAO {
	private User users[];
    private int nrOfUsers;
    
    public UserDAO(){
    	this.users=new User[100];
    	this.nrOfUsers=0;
    	
    	for(int i=0;i<100;i++){
    		 users[i]=null;
    	}
    }
    
    public void getUsers(){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from user";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setName(rs.getString("userName"));
				if(this.nrOfUsers<100) this.users[this.nrOfUsers++]=user;
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
    public boolean findUser(String str){
    	boolean find=false;
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select userName from user where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,str);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) find=true;
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	
    	return find;
    }
    
    public boolean findManager(){
    	boolean find=false;
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select userName from user where flag=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,1);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) find=true;
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	
    	return find;
    }
    
    public boolean findUser(String name,String passwd){
    	boolean find=false;
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from user where userName=? and userPassword=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,passwd);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) find=true;
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	
    	return find;
    }
    public void addUser(User user,String time,String h){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "insert into user(userName,userPassword,age,regTime,head,gender,flag,slience) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,user.getName());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getAge());
			ps.setString(4,time);
			ps.setString(5,h);
			ps.setString(6,user.getSex());
			ps.setInt(7,0);
			ps.setInt(8,0);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
    
    public void addManager(User user,String time,String h){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "insert into user(userName,userPassword,age,regTime,head,gender,flag,slience) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,user.getName());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getAge());
			ps.setString(4,time);
			ps.setString(5,h);
			ps.setString(6,user.getSex());
			ps.setInt(7,1);
			ps.setInt(8,0);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
    
    public String getUserNameByUserId(int id){
    	ConnectionDB cdb=new ConnectionDB();
    	String name="";
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from user where userId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) name=rs.getString("userName");
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return name;
    }
    public String getRegTimeByUserNmae(String name){
    	ConnectionDB cdb=new ConnectionDB();
    	String regTime="";
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select regTime from user where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) regTime=rs.getString("regTime");
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return regTime;
    }
    public String getPasswordByUserNmae(String name){
    	ConnectionDB cdb=new ConnectionDB();
    	String password="";
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select userPassword from user where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) password=rs.getString("userPassword");
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return password;
    }
    public void UpdatePasswordByUserNmae(String name,String userPassword){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "update user set userPassword=? where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,userPassword);
			ps.setString(2,name);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void UpdateAgeByUserNmae(int age,String name){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "update user set age=? where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,age);
			ps.setString(2,name);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void UpdateSlienceByUserNmae(int slience,String name){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "update user set slience=? where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,slience);
			ps.setString(2,name);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void UpdateGenderByUserNmae(String gender,String name){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "update user set gender=? where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,gender);
			ps.setString(2,name);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public String getAgeByUserNmae(String name){
    	ConnectionDB cdb=new ConnectionDB();
    	String age="";
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select age from user where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) age=rs.getString("age");
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return age;
    }
    public String getGenderByUserNmae(String name){
    	ConnectionDB cdb=new ConnectionDB();
    	String gender="";
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select gender from user where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) gender=rs.getString("gender");
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return gender;
    }
    public int getUserFlagByUserName(String name){
    	ConnectionDB cdb=new ConnectionDB();
    	int flag=0;
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select flag from user where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) flag=rs.getInt("flag");
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return flag;
    }
    
    public int getSlenceByUserName(String name){
    	ConnectionDB cdb=new ConnectionDB();
    	int sli=0;
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select slience from user where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) sli=rs.getInt("slience");
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return sli;
    }
    
    public int getUserIdByUserName(String name){
    	ConnectionDB cdb=new ConnectionDB();
    	int id=0;
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from user where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) id=rs.getInt("userId");
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return id;
    }
    
    public int getNrOfUsers(){return this.nrOfUsers;}
    public User getUserByIndex(int i){User u=new User();if(this.nrOfUsers!=0&&this.nrOfUsers<100) u=users[i];return u;}
}

