package javaBeans;

public class Collect {
    private int u_id;
    private int t_id;
    
    public Collect(){}
    public Collect(int u_id,int t_id){
    	this.u_id = u_id;
    	this.t_id = t_id;
    }
    
    public int getUid(){ return this.u_id; }
    public int getTid(){ return this.t_id; }
    
    public void setUid(int u_id){ this.u_id = u_id; }
    public void setTid(int t_id){ this.t_id = t_id; }
}
