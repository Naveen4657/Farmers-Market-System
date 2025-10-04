package farmersmarket;

public class Payment {
    private Order order;
    private double amount;

    public Payment(Order order, double amount) {
        this.order = order;
        this.amount = amount;
    }

    public boolean processPayment() {
        if (amount >= order.getTotal()) {
            order.markPaid();   // <-- this must run
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Payment for " + order + " | Amount: " + amount;
    }
}
