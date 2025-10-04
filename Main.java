package farmersmarket;

import java.util.*;

public class Main {
    private static Map<String, Vendor> vendors = new HashMap<>();
    private static Map<String, Product> products = new HashMap<>();
    private static Map<String, Order> orders = new HashMap<>();
    private static int orderCounter = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Farmers' Market Menu ---");
            System.out.println("1. Add Vendor");
            System.out.println("2. Add Product");
            System.out.println("3. Place Order");
            System.out.println("4. Record Payment");
            System.out.println("5. Schedule Delivery");
            System.out.println("6. Request Return");
            System.out.println("7. Display Inventory");
            System.out.println("8. Display Orders");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addVendor(sc);
                case 2 -> addProduct(sc);
                case 3 -> placeOrder(sc);
                case 4 -> recordPayment(sc);
                case 5 -> scheduleDelivery(sc);
                case 6 -> requestReturn(sc);
                case 7 -> displayInventory();
                case 8 -> displayOrders();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }

    // ---- Menu Methods ----

    private static void addVendor(Scanner sc) {
        System.out.print("Enter Vendor ID: ");
        String id = sc.next();
        System.out.print("Enter Vendor Name: ");
        String name = sc.next();
        vendors.put(id, new Vendor(id, name));
        System.out.println("Vendor added.");
    }

    private static void addProduct(Scanner sc) {
        System.out.print("Enter Product ID: ");
        String id = sc.next();
        System.out.print("Enter Product Name: ");
        String name = sc.next();
        System.out.print("Enter Stock: ");
        int stock = sc.nextInt();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Vendor ID: ");
        String vendorId = sc.next();

        Vendor vendor = vendors.get(vendorId);
        if (vendor != null) {
            Product p = new Product(id, name, stock, price, vendor);
            products.put(id, p);
            vendor.addProduct(p);
            System.out.println("Product added.");
        } else {
            System.out.println("Vendor not found.");
        }
    }

    private static void placeOrder(Scanner sc) {
        String orderId = "O" + orderCounter++;
        Order order = new Order(orderId);

        while (true) {
            System.out.print("Enter Product ID (or 'done'): ");
            String pid = sc.next();
            if (pid.equalsIgnoreCase("done")) break;

            Product p = products.get(pid);
            if (p != null) {
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                if (qty <= p.getStock()) {
                    order.addItem(new OrderItem(p, qty));
                } else {
                    System.out.println("Not enough stock.");
                }
            } else {
                System.out.println("Product not found.");
            }
        }

        orders.put(orderId, order);
        System.out.println("Order placed:\n" + order.invoice());
    }

    private static void recordPayment(Scanner sc) {
        System.out.print("Enter Order ID: ");
        String oid = sc.next();
        Order order = orders.get(oid);
        if (order != null) {
            if (order.isPaid()) {
                System.out.println("Order already paid.");
                return;
            }
            System.out.print("Enter payment amount: ");
            double amt = sc.nextDouble();
            Payment payment = new Payment(order, amt);
            if (payment.processPayment())
                System.out.println("Payment successful.");
            else
                System.out.println("Payment failed.");
        } else {
            System.out.println("Order not found.");
        }
    }

    private static void scheduleDelivery(Scanner sc) {
        System.out.print("Enter Order ID: ");
        String oid = sc.next();
        Order order = orders.get(oid);
        if (order != null) {
            Delivery d = new Delivery(order);
            d.scheduleDelivery();
            System.out.println(d);
        } else {
            System.out.println("Order not found.");
        }
    }

    private static void requestReturn(Scanner sc) {
        System.out.print("Enter Order ID: ");
        String oid = sc.next();
        Order order = orders.get(oid);
        if (order != null) {
            System.out.print("Enter Product Name to return: ");
            String pname = sc.next();
            for (OrderItem item : order.getItems()) {
                if (item.getProduct().getName().equalsIgnoreCase(pname)) {
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    ReturnRequest rr = new ReturnRequest(item, qty);
                    rr.processReturn();
                    System.out.println(rr);
                    return;
                }
            }
            System.out.println("Product not found in order.");
        } else {
            System.out.println("Order not found.");
        }
    }

    private static void displayInventory() {
        for (Vendor v : vendors.values()) {
            v.displayInventory();
        }
    }

    private static void displayOrders() {
        for (Order o : orders.values()) {
            System.out.println(o.invoice());
        }
    }
}
