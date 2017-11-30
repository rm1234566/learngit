package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javaBeans.ConnectionDB;
import javaBeans.Board;

public class BoardDAO {
	private Board boards[];
	private int nrOfBoards;
	
	public BoardDAO(){
		this.boards=new Board[100];
    	this.nrOfBoards=0;
    	
    	for(int i=0;i<100;i++){
    		 boards[i]=null;
    	}
	}
	
	public void getBoards(){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from board";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Board board = new Board();
				board.setBoardName(rs.getString("boardName"));
				board.setBoarHead(rs.getString("boardHead"));
				if(this.nrOfBoards<100) this.boards[this.nrOfBoards++] = board;
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
	
	public int getBoardIdByBname(String Bname){
		ConnectionDB cdb=new ConnectionDB();
    	int id=0;
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select * from board where boardName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,Bname);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) id=rs.getInt("boardId");
			
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
	
	public boolean findBoard(String str){
    	boolean find=false;
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "select boardName from board where boardName=?";
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
	
	public void addBoard(Board board,String head){
    	ConnectionDB cdb=new ConnectionDB();
    	
    	try {
    		Connection con = cdb.getConnectionDB();
			String sql = "insert into board(boardName,boardHead) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,board.getBoardName());
			ps.setString(2,head);
			ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
	
	public int getBoardNum(){
		ConnectionDB cdb=new ConnectionDB();
        int num_result = -2;

        try {
    		Connection con = cdb.getConnectionDB();
			
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery("select * from board"); 
			rs.last(); 
			num_result = rs.getRow(); 

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 

        return num_result;
    }
	
	public int getNrOfBoards(){return this.nrOfBoards;}
	public Board getBoardByIndex(int i){Board b=new Board();if(this.nrOfBoards!=0&&this.nrOfBoards<100) b=boards[i];return b;}
}
