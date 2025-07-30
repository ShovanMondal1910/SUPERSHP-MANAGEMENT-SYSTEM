package models;

public class Customer extends User {
    private String membershipType;
    private String photoPath;
   
    public Customer() 
    {
        super();
    }
                 
    public Customer(String userID, String name, String gender, int age, String email, String phone, String address, String securityQuestion, String securityAnswer, String password, String membershipType, String photoPath) {
        this.userId = userID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phoneNo = phone;
        this.address = address;
        this.securityAns = securityAnswer;
        this.password = password;
        this.membershipType = membershipType;
        this.photoPath = photoPath;
    }

    public void setMembershipType(String membershipType) 
    {
        this.membershipType = membershipType;
    }

    public String getMembershipType() 
    {
        return this.membershipType;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
    public String getPhotoPath() {
        return this.photoPath;
    }

    public String toStringCustomer() 
    {
        String str = this.userId + "," + this.name + "," + this.gender + "," + this.age + "," + this.email + "," + this.phoneNo + "," + this.address + "," + this.securityAns + "," + this.password + "," + this.membershipType + "," + this.photoPath + "\n";
        return str;
    }

    public Customer formCustomer(String str) 
    {
        String data[] = str.split(",");
        Customer customer = new Customer();
        customer.setUserId(data[0]);
        customer.setName(data[1]);
        customer.setGender(data[2]);
        customer.setAge(Integer.parseInt(data[3]));
        customer.setEmail(data[4]);
        customer.setPhoneNo(data[5]);
        customer.setAddress(data[6]);
        customer.setSecurityAns(data[7]);
        customer.setPassword(data[8]);
        customer.setMembershipType(data[9]);
        if (data.length > 10) customer.setPhotoPath(data[10]);
        return customer;
    }
}
