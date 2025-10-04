package farmersmarket;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private List<OrderItem> items = new ArrayList<>();
    private boolean paid;
    private boolean delivered;

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.getProduct().reduceStock(item.getQuantity());
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }

    public void markPaid() { this.paid = true; }
    public boolean isPaid() { return paid; }

    public void markDelivered() { this.delivered = true; }
    public boolean isDelivered() { return delivered; }

    public List<OrderItem> getItems() { return items; }

    @Override
    public String toString() {
        return "Order " + orderId + " | Total: " + getTotal() + " | Paid: " + paid + " | Delivered: " + delivered;
    }

    
    public String invoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice for Order ").append(orderId).append("\n");
        sb.append("--------------------------------\n");
        for (OrderItem item : items) {
            sb.append(item).append("\n");
        }
        sb.append("--------------------------------\n");
        sb.append("Total: ").append(getTotal()).append("\n");
        sb.append("Paid: ").append(paid).append("\n");
        sb.append("Delivered: ").append(delivered).append("\n");
        return sb.toString();
    }
}
