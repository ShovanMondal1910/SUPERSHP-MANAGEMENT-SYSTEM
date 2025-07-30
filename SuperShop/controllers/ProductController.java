package controllers;
import models.*;

public class ProductController {

    public void insertProduct(Product p)
	{
		Product product[]=this.getAllProduct();
		
		for(int i=0;i<product.length;i++)
		{
			if(product[i]==null)
			{
				product[i]=p;
				break;
			}
		}
		
		this.write(product);
		
		
	}

    public void updateProduct(Product p)
	{
		Product product[]=this.getAllProduct();
		
		for(int i=0;i<product.length;i++)
		{
			if(product[i]!=null)
			{
				if(product[i].getProductId().equals(p.getProductId()))
				{
					product[i]=p;
				}
			}
		}
		
		this.write(product);
	}
    
    public void delateProduct(String productId)
    {
        Product product[]=this.getAllProduct();
		
		for(int i=0;i<product.length;i++)
		{
			if(product[i]!=null)
			{
				if(product[i].getProductId().equals(productId))
				{
					product[i]=null;
				}
			}
		}
		
		this.write(product);
    }

    public Product searchProduct(String productId)
    {
        Product product[]=this.getAllProduct();

        for(int i=0;i<product.length;i++)
        {
            if(product[i]!=null)
            {
                if(product[i].getProductId().equals(productId))
                {
                    return product[i];
                }

            }
        }
        return null;
    }

    public Product[] getAllProduct() {
        String fileName = "controllers/data/Products.txt";
        FileIO fio = new FileIO();
        String values[] = fio.readFile(fileName);


        Product product[] = new Product[100];
        Product p = new Product();

        for (int i = 0; i < values.length; i++) {
            if(values[i] != null && !values[i].trim().isEmpty()) {
                if (product[i] == null) {
                    product[i] = p.formProduct(values[i]);
                }
            }
        }

        return product;
    }

    public void write(Product products[])
    {
        String data[]=new String[100];

        for(int i=0;i<data.length;i++)
        {
            if(products[i]!=null)
            {
                data[i]=products[i].toStringProduct();
            }
        }

        String fileName="controllers/data/Products.txt";

        FileIO fio=new FileIO();
        fio.writeFile(fileName,data);
    }

    // Returns an array of top N selling products (by quantity sold)
    public Product[] getTopSellingProducts(int topN) {
        String invoiceFile = "controllers/data/InvoiceList.txt";
        FileIO fio = new FileIO();
        String[] lines = fio.readFile(invoiceFile);
        java.util.Map<String, Integer> salesCount = new java.util.HashMap<>();
        for (String line : lines) {
            if (line != null && !line.trim().isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String productName = parts[3].trim();
                    int qty = 0;
                    try { qty = Integer.parseInt(parts[4].trim()); } catch (Exception e) { qty = 0; }
                    salesCount.put(productName, salesCount.getOrDefault(productName, 0) + qty);
                }
            }
        }
        // Get all products
        Product[] allProducts = getAllProduct();
        java.util.List<Product> productList = new java.util.ArrayList<>();
        for (Product p : allProducts) {
            if (p != null && salesCount.containsKey(p.getProductName())) {
                productList.add(p);
            }
        }
        // Sort by sales count descending
        productList.sort((a, b) -> Integer.compare(salesCount.get(b.getProductName()), salesCount.get(a.getProductName())));
        // Return top N
        int size = Math.min(topN, productList.size());
        Product[] result = new Product[size];
        for (int i = 0; i < size; i++) {
            result[i] = productList.get(i);
        }
        return result;
    }
}
