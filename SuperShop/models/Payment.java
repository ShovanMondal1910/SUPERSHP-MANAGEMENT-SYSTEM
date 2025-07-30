package models;

public class Payment{
    private  String paymentId;
    private  String userId;
    private  String orderId;
    private  String amount;
    private  String date;
	
    public Payment()
    {

    }
	
    public Payment( String paymentId, String userId, String orderId, String amount, String date)
    {
        this.paymentId=paymentId;
        this.userId=userId;
        this.orderId=orderId;
        this.amount=amount;
        this.date=date;
    }
	
    public void setPaymentId( String paymentId)
    {
        this.paymentId=paymentId;
    }
    public void setUserId( String userId)
    {
        this.userId=userId;
    }
    public void setOrderId( String orderId)
    {
        this.orderId=orderId;
    }
    public void setAmount( String amount)
    {
        this.amount=amount;
    }
    public void setDate( String date)
    {
        this.date=date;
    }
    public  String getPaymentId()
    {
        return this.paymentId;
    }
    public  String getUserId()
    {
        return this.userId;
    }
    public  String getOrderId()
    {
        return this.orderId;
    }
    public  String getAmount()
    {
        return this.amount;
    }
    public  String getDate()
    {
        return this.date;
    }


    public String toStringPayment()
    {
        String str=this.paymentId+","+this.userId+","+this.orderId+","+this.amount+","+this.date+"\n";
        return str;
    }

    public Payment formPayment(String str){
        String data[]=str.split(",");
        Payment pay=new Payment();
        pay.setPaymentId(data[0]);
        pay.setUserId(data[1]);
        pay.setOrderId(data[2]);
        pay.setAmount(data[3]);
        pay.setDate(data[4]);

        return pay;
    }
}
