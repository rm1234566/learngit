package javaBeans;

public class Friend {
	private int u_id_1;
	private int u_id_2;
	
	public Friend(){}
	public Friend(int u_id_1,int u_id_2){
		this.u_id_1 = u_id_1;
		this.u_id_2 = u_id_2;
	}
	
	public int getU_id_1(){ return this.u_id_1; }
	public int getU_id_2(){ return this.u_id_2; }
	
	public void setU_id_1(int u_id_1){ this.u_id_1 = u_id_1; }
	public void setU_id_2(int u_id_2){ this.u_id_2 = u_id_2; }
}
