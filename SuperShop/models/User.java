package models;
public class User
{
	protected String userId;
	protected String name;
	protected String gender;
	protected int age;
	protected String email;
	protected String phoneNo;
	protected String address;
	protected int role;
	protected String securityAns;
	protected String password;
    protected String photoPath;
	public User()
	{
	}
	
	public User(String userId, int role, String securityAns, String password, String photoPath)
	{
		this.userId=userId;
		this.role=role;
		this.securityAns=securityAns;
		this.password=password;
		this.photoPath=photoPath;
	}
	
	public void setUserId(String userId)
	{
		this.userId=userId;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void setGender(String gender)
	{
		this.gender=gender;
	}
	
	public void setAge(int age)
	{
		this.age=age;
	}
	
	public void setEmail(String email)
	{
		this.email=email;
	}
	
	public void setAddress(String address)
	{
		this.address=address;
	}
	
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo=phoneNo;
	}
	
	public void setRole(int role)
	{
		this.role=role;
	}
	
	public void setSecurityAns(String securityAns)
	{
		this.securityAns=securityAns;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public void setPhotoPath(String photoPath)
	{
		this.photoPath=photoPath;
	}
	
	public String getUserId()
	{
		return this.userId;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getGender()
	{
		return this.gender;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public String getPhoneNo()
	{
		return this.phoneNo;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public int getRole()
	{
		return this.role;
	}
	
	public String getSecurityAns()
	{
		return this.securityAns;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getPhotoPath()
	{
		return this.photoPath;
	}
	
	public String toStringUser()
	{
		String str=this.userId+","+this.role+","+this.securityAns+","+this.password+","+this.photoPath+"\n";
		return str;
	}
	
	public User formUser(String str)
	{
		String data[]=str.split(",");
		
		User u=new User();
		u.setUserId(data[0]);
		u.setRole(Integer.parseInt(data[1]));
		u.setSecurityAns(data[2]);
		u.setPassword(data[3]);
		u.setPhotoPath(data.length > 4 ? data[4] : "");
		return u;
	}
}