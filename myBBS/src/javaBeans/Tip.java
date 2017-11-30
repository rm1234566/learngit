package javaBeans;

public class Tip {
    private String title;
    private String content;
    private String publishTime;
    private int userId;
    private int boardId;
    private int tipPrivate;
    
    public Tip(){}
    public Tip(String title,String content,String publishTime,int userId,int boardId,int tipPrivate){
    	this.title=title;
    	this.content=content;
    	this.userId=userId;
    	this.boardId=boardId;
    	this.publishTime=publishTime;
    	this.tipPrivate=tipPrivate;
    }
    public void setTitle(String title){this.title=title;}
    public void setContent(String content){this.content=content;}
    public void setUserId(int userId){this.userId=userId;}
    public void setBoardId(int boardId){this.boardId=boardId;}
    public void setPublishTime(String publishTime){this.publishTime=publishTime;}
    public void setTipPrivate(int tipPrivate){this.tipPrivate=tipPrivate;}
    public String getTitle(){return this.title;}
    public String getContent(){return this.content;}
    public int getUserId(){return this.userId;}
    public int getBoardId(){return this.boardId;}
    public String getPublishTime(){return this.publishTime;}
    public int getTipPrivate(){return this.tipPrivate;}
}

