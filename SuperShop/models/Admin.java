package models;

public class Admin extends User
{
	private String adminType;
	private String photoPath;
	public Admin()
	{
		super();
	}
	
	public Admin(String userId, String name, String gender,int age, String email, String phoneNo, String address, int role, String securityAns, String password, String adminType, String photoPath)
	{
		super(userId,1,securityAns,password,null);
		this.name=name;
		this.gender=gender;
		this.age=age;
		this.email=email;
		this.phoneNo=phoneNo;
		this.address=address;
		this.adminType=adminType;
		this.photoPath=photoPath;
	}

	public void setAdminType(String adminType)
	{
		this.adminType=adminType;
	}
	
	public String getAdminType()
	{
		return this.adminType;
	}

	public void setPhotoPath(String photoPath)
	{
		this.photoPath=photoPath;
	}
	
	public String toStringAdmin()
	{
		String str=this.userId+","+this.name+","+this.gender+","+this.age+","+this.email+","+this.phoneNo+","+this.address+","+this.securityAns+","+this.password+","+this.adminType+","+this.photoPath+"\n";
		return str;
	}
	
	public Admin formAdmin(String str)
	{
		String data[]=str.split(",");

		Admin a=new Admin();
		a.setUserId(data[0]);
		a.setName(data[1]);
		
		if (data.length >= 11) {
			a.setGender(data[2]);
			a.setAge(Integer.parseInt(data[3]));
			a.setEmail(data[4]);
			a.setPhoneNo(data[5]);
			a.setAddress(data[6]);
			a.setRole(1); 
			a.setSecurityAns(data[7]);
			a.setPassword(data[8]);
			a.setAdminType(data[9]);
			a.setPhotoPath(data[10]);
		} else if (data.length >= 10) {
			a.setGender("Male"); 
			a.setAge(Integer.parseInt(data[2]));
			a.setEmail(data[3]);
			a.setPhoneNo(data[4]);
			a.setAddress(data[5]);
			a.setRole(1); 
			a.setSecurityAns(data[6]);
			a.setPassword(data[7]);
			a.setAdminType(data[8]);
			a.setPhotoPath(data[9]);
		} else {
			throw new IllegalArgumentException("Invalid data format in admin record");
		}
		return a;
	}
}
