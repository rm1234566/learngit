package javaBeans;

public class User {
	private String name;
	private String password;
	private String age;
	private String sex;
	
    public User(){}
    
    public User(String name,String password,String age,String sex){
    	this.name=name;
    	this.password=password;
    	this.age=age;
    	this.sex=sex;
    }
    
    public String getName(){return this.name;}
    public String getPassword(){return this.password;}
    public String getAge(){return this.age;}
    public String getSex(){return this.sex;}
    
    public void setName(String name){this.name=name;}
    public void setPassword(String password){this.password=password;}
    public void setAge(String age){this.age=age;}
    public void setSex(String sex){this.sex=sex;}
}

