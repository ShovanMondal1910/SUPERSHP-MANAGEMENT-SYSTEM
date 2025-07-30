package controllers;
import models.*;

public class PaymentController
{
	public void insertPayment(Payment p)
	{
		Payment Payment[]=this.getAllPayment();
		
		for(int i=0;i<Payment.length;i++)
		{
			if(Payment[i]==null)
			{
				Payment[i]=p;
				break;
			}
		}
		
		this.write(Payment);
		
		
	}
	
	public void updatePayment(Payment p)
	{
		Payment Payment[]=this.getAllPayment();
		
		for(int i=0;i<Payment.length;i++)
		{
			if(Payment[i]!=null)
			{
				if(Payment[i].getUserId().equals(p.getUserId()))
				{
					Payment[i]=p;
				}
			}
		}
		
		this.write(Payment);
	}
	
	public void deletePayment(String userId)
	{
		Payment Payment[]=this.getAllPayment();
		
		for(int i=0;i<Payment.length;i++)
		{
			if(Payment[i]!=null)
			{
				if(Payment[i].getUserId().equals(userId))
				{
					Payment[i]=null;
				}
			}
		}
		
		this.write(Payment);
	}
	
	public Payment searchPayment(String userId)
	{
		Payment Payment[]=this.getAllPayment();
		
		for(int i=0;i<Payment.length;i++)
		{
			if(Payment[i]!=null)
			{
				if(Payment[i].getUserId().equals(userId))
				{
					
					return Payment[i];
				}
			}
			
		}
		
		return null;
	}
	
	public Payment[] getAllPayment()
	{
		String fileName="controllers/data/Payments.txt";
		FileIO fio=new FileIO();
		String values[]=fio.readFile(fileName);
		
		Payment Payment[]=new Payment[100];
		
		Payment a=new Payment();
		
		for(int i=0;i<values.length;i++)
		{
			if(values[i] != null && !values[i].trim().isEmpty())
			{
				if(Payment[i]==null)
				{
					Payment[i]=a.formPayment(values[i]);
				}
			}
			
		}
		
		return Payment;
	}
	
	public void write(Payment Payment[])
	{
		String data[]=new String[100];
		
		for(int i=0;i<data.length;i++)
		{
			if(Payment[i]!=null)
			{
				data[i]=Payment[i].toStringPayment();
			}
		}
		
		String fileName="controllers/data/Payments.txt";
		
		FileIO fio=new FileIO();
		fio.writeFile(fileName, data);
	}
}