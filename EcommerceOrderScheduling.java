import java.util.*;

class Order {
    int orderId;
    String customerType; // "regular" or "premium"
    boolean isExpress;
    double amount;

    public Order(int orderId, String customerType, boolean isExpress, double amount) {
        this.orderId = orderId;
        this.customerType = customerType;
        this.isExpress = isExpress;
        this.amount = amount;
    }

    // Priority logic
    public int getPriority() {
        if (isExpress) return 1;
        if (customerType.equals("premium")) return 2;
        return 3;
    }

    public String toString() {
        return "OrderID: " + orderId + ", Type: " + customerType + ", Express: " + isExpress + ", Amount: " + amount;
    }
}

public class EcommerceOrderScheduling {
    public static void main(String[] args) {
        PriorityQueue<Order> orderQueue = new PriorityQueue<>(new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return Integer.compare(o1.getPriority(), o2.getPriority());
            }
        });

        // Sample orders
        orderQueue.add(new Order(101, "regular", false, 700));
        orderQueue.add(new Order(102, "premium", false, 2000));
        orderQueue.add(new Order(103, "regular", true, 300));
        orderQueue.add(new Order(104, "premium", true, 3000));

        // Processing orders
        while (!orderQueue.isEmpty()) {
            System.out.println("Processing -> " + orderQueue.poll());
        }
    }
}
