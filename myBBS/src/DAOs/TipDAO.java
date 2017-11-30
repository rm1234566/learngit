package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaBeans.Tip;
import javaBeans.ConnectionDB;

public class TipDAO {
	private Tip tips[];
    private int nrOfTips;
	
    public TipDAO(){
    	this.tips=new Tip[100];
    	this.nrOfTips=0;
    	
    	for(int i=0;i<100;i++){
    		 tips[i]=null;
    	}
    }
    
    public void getTipsByPrivate(int p){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from tip where tipPrivate=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,p);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Tip tip=new Tip();
				tip.setTitle(rs.getString("title"));
				tip.setContent(rs.getString("content"));
				tip.setPublishTime(rs.getString("publishTime"));
				tip.setUserId(rs.getInt("userId"));
				tip.setBoardId(1);
				if(this.nrOfTips<100) this.tips[this.nrOfTips++]=tip;
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
    
    public void getTipsByUidAndPrivate(int u_id,int p){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from tip where userId=? and tipPrivate=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,u_id);
			ps.setInt(2,p);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Tip tip=new Tip();
				tip.setTitle(rs.getString("title"));
				tip.setContent(rs.getString("content"));
				tip.setPublishTime(rs.getString("publishTime"));
				tip.setUserId(rs.getInt("userId"));
				tip.setBoardId(1);
				if(this.nrOfTips<100) this.tips[this.nrOfTips++]=tip;
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
    
    public void getTipsByBid(int b_id,int p){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from tip where boardId=? and tipPrivate=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,b_id);
			ps.setInt(2,p);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Tip tip=new Tip();
				tip.setTitle(rs.getString("title"));
				tip.setContent(rs.getString("content"));
				tip.setPublishTime(rs.getString("publishTime"));
				tip.setUserId(rs.getInt("userId"));
				tip.setBoardId(b_id);
				if(this.nrOfTips<100) this.tips[this.nrOfTips++]=tip;
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
    
    public String getContentById(int id){
    	ConnectionDB cdb=new ConnectionDB();
    	String content="";
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select content from tip where tipId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				content=rs.getString("content");
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return content;
    }
    public int getUserIdById(int id){
    	ConnectionDB cdb=new ConnectionDB();
    	int userId=0;
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select userId from tip where tipId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userId=rs.getInt("userId");
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return userId;
    }
    public void deleteTipById(int id){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "delete from tip where tipId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    public String getPublishTimeById(int id){
    	ConnectionDB cdb=new ConnectionDB();
    	String publishTime="";
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select publishTime from tip where tipId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				publishTime=rs.getString("publishTime");
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return publishTime;
    }
    
    public String getTitleById(int id){
    	ConnectionDB cdb=new ConnectionDB();
    	String title="";
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select title from tip where tipId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				title=rs.getString("title");
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return title;
    }
    
    public int getTipIdByTitle(String title){
    	ConnectionDB cdb=new ConnectionDB();
    	int t_id=0;
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select tipId from tip where title=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,title);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				t_id=rs.getInt("tipId");
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return t_id;
    }
    
    public void addTip(Tip tip){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "insert into tip(title,content,publishTime,userId,boardId,tipPrivate) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,tip.getTitle());
			ps.setString(2,tip.getContent());
			ps.setString(3,tip.getPublishTime());
			ps.setInt(4,tip.getUserId());
			ps.setInt(5,1);
			ps.setInt(6,tip.getTipPrivate());
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void addTipAndBid(Tip tip,int b_id){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "insert into tip(title,content,publishTime,userId,boardId,tipPrivate) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,tip.getTitle());
			ps.setString(2,tip.getContent());
			ps.setString(3,tip.getPublishTime());
			ps.setInt(4,tip.getUserId());
			ps.setInt(5,b_id);
			ps.setInt(6,tip.getTipPrivate());
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public int getTipIdByIndex(int index){
    	ConnectionDB cdb=new ConnectionDB();
    	int id=0;
    	int j=0;
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select tipId from tip order by tipId";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(j==index){id=rs.getInt("tipId");break;}
				else j++;
			}
			
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
    public int getNrOfTips(){return this.nrOfTips;}
    public Tip getTipByIndex(int i){Tip t=new Tip();if(this.nrOfTips!=0&&this.nrOfTips<100) t=tips[i];return t;}
}
