package javaBeans;

public class Board {
    private String boardName;
    private String boardHead;
    
    public Board(){}
    
    public Board(String boardName, String boardHead){
    	this.boardName = boardName;
    	this.boardHead = boardHead;
    }
    
    public Board(String boardName){
    	this.boardName = boardName;
    }
    
    public String getBoardName(){ return this.boardName; }
    public String getBoardHead(){ return this.boardHead; }
    
    public void setBoardName(String boardName){ this.boardName = boardName; }
    public void setBoarHead(String boardHead){ this.boardHead = boardHead; }
}
