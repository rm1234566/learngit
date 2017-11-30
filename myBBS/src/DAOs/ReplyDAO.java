package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaBeans.ConnectionDB;
import javaBeans.Reply;

public class ReplyDAO {
	private Reply replys[];
    private int nrOfReplys;
    
    public ReplyDAO(){
    	this.replys=new Reply[100];
    	this.nrOfReplys=0;
    	
    	for(int i=0;i<100;i++){
    		 replys[i]=null;
    	}
    }
    public void getReplys(int id){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from reply where tipId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Reply reply=new Reply();
				reply.setTitle(rs.getString("title"));
				reply.setContent(rs.getString("content"));
				reply.setPublishTime(rs.getString("publishTime"));
				reply.setUserId(rs.getInt("userId"));
				reply.setTipId(rs.getInt("tipId"));
				if(this.nrOfReplys<100) this.replys[this.nrOfReplys++]=reply;
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
    public void addReply(Reply reply){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "insert into reply(title,content,publishTime,userId,tipId) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,reply.getTitle());
			ps.setString(2,reply.getContent());
			ps.setString(3,reply.getPublishTime());
			ps.setInt(4,reply.getUserId());
			ps.setInt(5,reply.getTipId());
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
			String sql = "select publishTime from reply where replyId=?";
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
    public int getUserIdById(int id){
    	ConnectionDB cdb=new ConnectionDB();
    	int userId=0;
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select userId from reply where replyId=?";
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
    public int getReplyIdByIndex(int index,int tipId){
    	ConnectionDB cdb=new ConnectionDB();
    	int id=0;
    	int j=0;
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select replyId from reply where tipId=? order by replyId";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,tipId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(j==index){id=rs.getInt("replyId");break;}
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
    public String getContentById(int id){
    	ConnectionDB cdb=new ConnectionDB();
    	String content="";
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select content from reply where replyId=?";
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
    public int getNrOfReplys(){return this.nrOfReplys;}
    public Reply getReplyByIndex(int i){Reply r=new Reply();if(this.nrOfReplys!=0&&this.nrOfReplys<100) r=replys[i];return r;}
}
