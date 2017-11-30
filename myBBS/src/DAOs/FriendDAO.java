package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaBeans.ConnectionDB;
import javaBeans.Friend;

public class FriendDAO {
	private Friend friends[];
	private int nrOfFriends;
	
	public FriendDAO(){
    	this.friends=new Friend[100];
    	this.nrOfFriends=0;
    	
    	for(int i=0;i<100;i++){
    		 friends[i]=null;
    	}
    }
	
	public void getFriends(int u_id_1){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from friend where u_id_1=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,u_id_1);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Friend friend=new Friend();
				friend.setU_id_1(u_id_1);
				friend.setU_id_2(rs.getInt("u_id_2"));
				if(this.nrOfFriends<100) this.friends[this.nrOfFriends++]=friend;
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
	
	public boolean findFriend(int u_id_1,int u_id_2){
    	boolean find=false;
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from friend where u_id_1=? and u_id_2=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,u_id_1);
			ps.setInt(2,u_id_2);
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
	
	public void addFriend(Friend friend){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "insert into friend(u_id_1,u_id_2) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,friend.getU_id_1());
			ps.setInt(2,friend.getU_id_2());
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void deleteFriendByUid_1AndUid_2(int u_id_1,int u_id_2){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "delete from friend where u_id_1=? and u_id_2=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,u_id_1);
			ps.setInt(2,u_id_2);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
	
	
	public int getNrOfFriends(){return this.nrOfFriends;}
	
	public Friend getFriendByIndex(int i){Friend f=new Friend();if(this.nrOfFriends!=0&&this.nrOfFriends<100) f=friends[i];return f;}
}
