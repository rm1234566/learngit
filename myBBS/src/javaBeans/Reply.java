package javaBeans;

public class Reply {
    private String title;
    private String content;
    private String publishTime;
    private int userId;
    private int tipId;
    
    public Reply(){}
    public Reply(String title,String content,String publishTime,int userId,int tipId){
    	this.title=title;
    	this.content=content;
    	this.publishTime=publishTime;
    	this.userId=userId;
    	this.tipId=tipId;
    }
    public void setTitle(String title){this.title=title;}
    public void setContent(String content){this.content=content;}
    public void setPublishTime(String publishTime){this.publishTime=publishTime;}
    public void setUserId(int userId){this.userId=userId;}
    public void setTipId(int tipId){this.tipId=tipId;}
    public String getTitle(){return this.title;}
    public String getContent(){return this.content;}
    public String getPublishTime(){return this.publishTime;}
    public int getUserId(){return this.userId;}
    public int getTipId(){return this.tipId;}
}
