package models;

public class Order{

    private String orderId;
    private String productId;
    private String userId;
    private String totalAmount;
    private String completed;
    private String cancel;

    public Order()
    {
    }

    public Order(String orderId,String productId, String userId, String totalAmount,  String completed,  String cancel){
        this.orderId=orderId;
        this.productId=productId;
        this.userId=userId;
        this.totalAmount=totalAmount;
        this.completed=completed;
        this.cancel=cancel;
    }
    public void setOrderId(String orderId)
    {
        this.orderId=orderId;
	}
    public void setProductId(String productId)
    {
        this.productId=productId;
    } 
    public void setUserId(String userId)
    {
        this.userId=userId;
    }
    public void setTotalAmount( String totalAmount)
    {
        this.totalAmount=totalAmount;
    }
    public void setCompleted(String completed)
    {
        this.completed=completed;
    }
    public void setCancel( String cancel)
    {
        this.cancel=cancel;
    }
    public String getOrderId()
    {
        return this.orderId;
    }
    public String getProductId()
    {
        return this.productId;
    }
    public String getUserId()
    {
        return this.userId;
    }
    public  String getTotalAmount()
    {
        return this.totalAmount;
    }
    public  String getCompleted()
    {
        return this.completed;
    }
    public  String getCancel()
    {
        return this.cancel;
    }
    public String toStringOrder()
    {
        String str=this.orderId+","+this.userId+","+this.totalAmount+","+this.completed+","+this.cancel+"\n";
        return str;
    }
    public Order formOrder(String str)
    {
        String data[]=str.split(",");
        Order or=new Order();
        or.setOrderId(data[0]);
        or.setUserId(data[1]);
        or.setTotalAmount(data[2]);
        or.setCompleted(data[3]);
        or.setCancel(data[4]);
        return or;
    }
}