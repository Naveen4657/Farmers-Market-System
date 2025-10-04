package farmersmarket;
import java.util.ArrayList;
import java.util.List;

public class Vendor {
    private String vendorId;
    private String name;
    private List<Product> products = new ArrayList<>();

    public Vendor(String vendorId, String name) {
        this.vendorId = vendorId;
        this.name = name;
    }

    public String getVendorId() {
    	return vendorId; 
    	}
    public String getName() { 
    	return name; 
    	}
    public List<Product> getProducts() {
    	return products; 
    	}

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayInventory() {
        System.out.println("Inventory for Vendor: " + name);
        for (Product p : products) {
            System.out.println(p);
        }
    }

    @Override
    public String toString() {
        return vendorId + " - " + name;
    }
}
