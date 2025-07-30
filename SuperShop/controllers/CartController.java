package controllers;
import models.*;

public class CartController {
 
    public void insertCart(Cart c)
	{
		Cart cart[]=this.getAllCart();
		
		for(int i=0;i<cart.length;i++)
		{
			if(cart[i]==null)
			{
				cart[i]=c;
				break;
			}
		}
		
		this.write(cart);
		
	}
    
    public void updateCart(Cart c)
	{
		Cart cart[]=this.getAllCart();
		
		for(int i=0;i<cart.length;i++)
		{
			if(cart[i]!=null)
			{
				if(cart[i].getCartId().equals(c.getCartId()))
				{
					cart[i]=c;
				}
			}
		}
		
		this.write(cart);
	}

    public void deleteCart(String cartId)
	{
		Cart cart[]=this.getAllCart();
		
		for(int i=0;i<cart.length;i++)
		{
			if(cart[i]!=null)
			{
				if(cart[i].getCartId().equals(cartId))
				{
					cart[i]=null;
				}
			}
		}
		
		this.write(cart);
	}

    public Cart searchCartByProductId(String productId)
	{
		Cart cart[]=this.getAllCart();
		for(int i=0;i<cart.length;i++)
		{
			if(cart[i]!=null)
			{
				if(cart[i].getCartId().equals(productId))
				{
					return cart[i];
				}
			}
		}
		return null;
	}

	public Cart[] searchCartByproductId(String productId)
	{
		Cart cart[]=this.getAllCart();
		Cart cart1[]=new Cart[100];
		
		for(int i=0;i<cart.length;i++)
		{
			if(cart[i]!=null)
			{
				if(cart[i].getCartId().equals(productId))
				{
					cart1[i]=cart[i];
				}
			}
		}
		
		return cart1;
	}
	
    public Cart[] searchCartByUserId(String userId)
	{
		Cart cart[]=this.getAllCart();
		Cart cart1[]=new Cart[100];
		
		for(int i=0;i<cart.length;i++)
		{
			if(cart[i]!=null)
			{
				if(cart[i].getUserId().equals(userId))
				{
					cart1[i]=cart[i];
				}
			}
		}
		
		return cart1;
	}


   public Cart[] getAllCart() {
    String fileName = "controllers/data/Carts.txt";
    FileIO fio = new FileIO();
    String data[] = fio.readFile(fileName);

    Cart cart[] = new Cart[100];
    for (int i = 0; i < data.length; i++) {
        if(data[i] != null && !data[i].trim().isEmpty()) {
            Cart c = new Cart();
            cart[i] = c.formCart(data[i]);
        }
    }

    return cart;
}


    public void write(Cart cart[])
	{
		String data[]=new String[100];
		
		for(int i=0;i<cart.length;i++)
		{
			if(cart[i]!=null)
			{
				data[i]=cart[i].toStringCart();
			}
		}
		
		String fileName="controllers/data/Carts.txt";
		FileIO fio=new FileIO();
		fio.writeFile(fileName, data);
	}
}  