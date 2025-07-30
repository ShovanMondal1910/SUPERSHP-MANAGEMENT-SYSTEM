package models;

public class Cart {
    private String cartId;  
    private String userId;
    private String productId;
    private int quantity;
    private double price;
    private double VAT;
    private String discount;
    private String date;
    private double totalPrice;
    private String status;

    public Cart()
    {

    }
    
    public Cart(String cartId, String userId, String productId, int quantity, double price, double VAT, String discount, String date, double totalPrice, String status)
    {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.VAT = VAT;
        this.discount = discount;
        this.date = date;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getCartId()
    {
        return cartId;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getProductId()
    {
        return productId;
    }

    public int getQuantity()
    {
        return quantity;
    }   

    public double getPrice()
    {
        return price;
    }

    public double getVAT()
    {
        return VAT;
    }

    public String getDiscount()
    {
        return discount;
    }

    public String getDate()
    {
        return date;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public String getStatus()
    {
        return status;
    }

    public void setCartId(String cartId)
    {
        this.cartId = cartId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setVAT(double VAT)
    {
        this.VAT = VAT;
    }

    public void setDiscount(String discount)
    {
        this.discount = discount;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String toStringCart()
    {
        return cartId + "," + userId + "," + productId + "," + quantity + "," + price + "," + VAT + "," + discount + "," + date + "," + totalPrice + "," + status;
    }

    public Cart formCart(String str)
    {
        String data[] = str.split(",");
        Cart cart = new Cart();
        cart.setCartId(data[0]);
        cart.setUserId(data[1]);
        cart.setProductId(data[2]);
        cart.setQuantity(Integer.parseInt(data[3]));
        cart.setPrice(Double.parseDouble(data[4]));
        cart.setVAT(Double.parseDouble(data[5]));
        cart.setDiscount(data[6]);
        cart.setDate(data[7]);
        cart.setTotalPrice(Double.parseDouble(data[8]));
        cart.setStatus(data[9]);
        return cart;
    }
}
