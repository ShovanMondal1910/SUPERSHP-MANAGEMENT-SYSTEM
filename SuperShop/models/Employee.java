package models;

public class Employee extends User {
    private String NID;
    private String designation;
    private double salary;
    public Employee() 
    {
        super();
    }

    public Employee(String userId, String name, String gender, int age, String email, String phoneNo, String address, int role, String securityAns, String password, String NID,String designation, double salary, String photoPath) 
    {
        super(userId, 2, securityAns, password, photoPath);
        setName(name);
        setGender(gender);
        setAge(age);
        setEmail(email);
        setPhoneNo(phoneNo);
        setAddress(address);
        this.NID = NID;
        this.designation = designation;
        this.salary = salary;
    }

    public void setNID(String NID) 
    {
        this.NID = NID;
    }

    public void setDesignation(String designation) 
    {
        this.designation = designation;
    }

    public void setSalary(double salary) 
    {
        this.salary = salary;
    }

    public String getNID() 
    {
        return this.NID;
    }

    public String getDesignation() 
    {
        return this.designation;
    }

    public double getSalary() 
    {
        return this.salary;
    }

    public String toStringEmployee() 
    {
        String str = getUserId() + "," + getName() + "," + getGender() + "," + getAge() + "," + getEmail() + "," + getPhoneNo() + "," + getAddress() + "," + this.NID + "," + this.designation + "," + this.salary + "," + getPhotoPath() + "\n";
        return str;
    }

    public Employee formEmployee(String str)
    {
        String data[]=str.split(",");

        Employee e=new Employee();
        e.setUserId(data[0]);
        e.setName(data[1]);
        e.setGender(data[2]);
        e.setAge(Integer.parseInt(data[3]));
        e.setEmail(data[4]);
        e.setPhoneNo(data[5]);
        e.setAddress(data[6]);
        e.setNID(data[7]);
        e.setDesignation(data[8]);
        e.setSalary(Double.parseDouble(data[9]));
        if (data.length > 10) {
            e.setPhotoPath(data[10]);
        }
        return e;
    }
}
