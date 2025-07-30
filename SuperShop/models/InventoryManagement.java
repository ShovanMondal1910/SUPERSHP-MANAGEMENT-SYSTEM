package models;

public class InventoryManagement {
    private String inventoryId;
    private String productId;
    private int quantity;
    private String date;

    public InventoryManagement() {
        this.inventoryId = "";
        this.productId = "";
        this.quantity = 0;
        this.date = "";
    }
    public InventoryManagement(String inventoryId, String productId, int quantity, String date) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantity = quantity;
        this.date = date;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String toStringInventoryManagement() {
        String str=this.inventoryId + "," + this.productId + "," + this.quantity + "," + this. date+"\n";
        return str;
    }

    public InventoryManagement formInventoryManagement(String str) {
        String data[] = str.split(",");
        InventoryManagement inventoryManagement = new InventoryManagement();
        inventoryManagement.setInventoryId(data[0]);
        inventoryManagement.setProductId(data[1]);
        inventoryManagement.setQuantity(Integer.parseInt(data[2]));
        inventoryManagement.setDate(data[3]);
        return inventoryManagement;
    }
}