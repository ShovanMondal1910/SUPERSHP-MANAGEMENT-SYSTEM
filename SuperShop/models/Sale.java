package models;

public class Sale {
    private String saleId;
    private String productId;
    private String customerId;
    private String date;
    private int quantity;
    private double totalPrice;
    
    public Sale(String saleId, String productId, String customerId, String date, int quantity, double totalPrice) {
        this.saleId = saleId;
        this.productId = productId;
        this.customerId = customerId;
        this.date = date;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }   

    public String getSaleId() {
        return saleId;
    }

    public String getProductId() {
        return productId;
    }   

    public String getCustomerId() {
        return customerId;
    }

    public String getDate() {
        return date;
    }   

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }   

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }   

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }   

    public void setDate(String date) {
        this.date = date;
    }   

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }   

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }       

    public String toStringSale() {
        String str=saleId + "," + productId + "," + customerId + "," + date + "," + quantity + "," + totalPrice+"\n";
        return str;
    }

    public Sale formSale(String str) {
        String[] data = str.split(",");
        return new Sale(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Double.parseDouble(data[5]));
    }


}
