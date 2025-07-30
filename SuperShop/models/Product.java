package models;

public class Product 
{
    private String productId;
    private String productName;
    private String category;
    private String brand;
    private double price;
    private int availableQuantity;
    private String details;
    private String image;
    private String status;
    private String unit;
    
    public Product()
    {

    }

    public Product(String productId, String productName, String category, String brand, double price, int availableQuantity, String details, String image, String status, String unit) 
    {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.details = details;
        this.image = image;
        this.status = status;
        this.unit = unit;
    }


    public String getProductId()
     {
         return productId;
     }
    public void setProductId(String productId)
    {
         this.productId = productId;
    }

    public String getProductName() 
    {
         return productName;
    }
    public void setProductName(String productName) 
    {
         this.productName = productName; 
    }

    public String getCategory() 
    {
         return category; 
    }
    public void setCategory(String category) 
    {
         this.category = category; 
    }

    public String getBrand() 
    {
         return brand; 
    }
    public void setBrand(String brand) 
    {
         this.brand = brand; 
    }

    public double getPrice() 
    {
         return price; 
    }
    public void setPrice(double price) 
    {
         this.price = price; 
    }

    public int getAvailableQuantity() 
    {
         return availableQuantity; 
    }
    public void setAvailableQuantity(int availableQuantity) 
    {
         this.availableQuantity = availableQuantity; 
    }

    public String getDetails() 
    {
         return details; 
    }
    public void setDetails(String details) 
    {
         this.details = details;
    }

    public String getImage() 
    {
         return image; 
    }
    public void setImage(String image) 
    {
         this.image = image; 
    }

    public String getStatus() 
    {
         return status; 
    }
    public void setStatus(String status) 
    {
         this.status = status; 
    }

    public String getUnit() 
    {
         return unit; 
    }
    public void setUnit(String unit) 
    {
         this.unit = unit; 
    }
    public String toStringProduct() 
    {
        String str=this.productId + "," + this.productName + "," + this.category + "," + this.brand + "," + this.price + "," + this.availableQuantity + "," + this.details + "," + this.image + "," + this.status + "," + this.unit+"\n"; 
        return str;
    }

    //formProduct is a method that takes a string and returns a product object
    public Product formProduct(String productData) 
    {
        String data[]=productData.split(",");
        Product p=new Product();
        p.setProductId(data[0]);
        p.setProductName(data[1]);
        p.setCategory(data[2]);
        p.setBrand(data[3]);
        p.setPrice(Double.parseDouble(data[4]));
        p.setAvailableQuantity(Integer.parseInt(data[5]));
        p.setDetails(data[6]);
        p.setImage(data[7]);
        p.setStatus(data[8]);
        p.setUnit(data[9]);
        
        return p;
    }

}
