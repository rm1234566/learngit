package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaBeans.Collect;
import javaBeans.ConnectionDB;

public class CollectDAO {
	private Collect collects[];
	private int nrOfCollects;
	
	public CollectDAO(){
    	this.collects=new Collect[100];
    	this.nrOfCollects=0;
    	
    	for(int i=0;i<100;i++){
    		 collects[i]=null;
    	}
    }
	
	public void getCollects(int u_id){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from collect where u_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,u_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Collect collect=new Collect();
				collect.setUid(u_id);
				collect.setTid(rs.getInt("t_id"));
				if(this.nrOfCollects<100) this.collects[this.nrOfCollects++]=collect;
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
	
	public boolean findCollect(int u_id,int t_id){
    	boolean find=false;
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from collect where u_id=? and t_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,u_id);
			ps.setInt(2,t_id);
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
	
	public void addCollect(Collect collect){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "insert into collect(u_id,t_id) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,collect.getUid());
			ps.setInt(2,collect.getTid());
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void deleteCollectByUidAndTid(int u_id,int t_id){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "delete from collect where u_id=? and t_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,u_id);
			ps.setInt(2,t_id);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
	
	public int getNrOfCollects(){return this.nrOfCollects;}
	
	public Collect getCollectByIndex(int i){Collect c=new Collect();if(this.nrOfCollects!=0&&this.nrOfCollects<100) c=collects[i];return c;}
}
