package farmersmarket;

public class Delivery {
    private Order order;
    private String status;

    public Delivery(Order order) {
        this.order = order;
        this.status = "Pending";
    }

    public boolean scheduleDelivery() {
        if (order.isPaid()) {
            status = "Scheduled";
            order.markDelivered();
        } else {
            status = "Payment Pending";
        }
		return false;
    }

    @Override
    public String toString() {
        return "Delivery for Order: " + order + " | Status: " + status;
    }
}
