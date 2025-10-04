package farmersmarket;

public class Product {
    private String productId;
    private String name;
    private int stock;
    private double price;
    private Vendor vendor;

    public Product(String productId, String name, int stock, double price, Vendor vendor) {
        this.productId = productId;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.vendor = vendor;
    }

    public String getProductId() {
    	return productId; 
    	}
    public String getName() {
    	return name; 
    	}
    public int getStock() {
    	return stock; 
    	}
    public double getPrice() { 
    	return price; 
    	}
    public Vendor getVendor() { 
    	return vendor; 
    	}

    public void reduceStock(int qty) {
        if (qty <= stock) stock -= qty;
    }

    public void increaseStock(int qty) {
        stock += qty;
    }

    @Override
    public String toString() {
        return productId + " | " + name + " | Stock: " + stock + " | Price: " + price;
    }
}
