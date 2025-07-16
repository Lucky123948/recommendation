import java.util.*;

class Item {
    String name;
    double price;
    int quantity;

    Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return name + " | Price: " + price + " | Quantity: " + quantity;
    }
}

class Node {
    Item item;
    Node next;

    Node(Item item) {
        this.item = item;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    void add(Item item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
    }

    void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }

    void clear() {
        head = null;
    }

    boolean isEmpty() {
        return head == null;
    }
}

public class Linkedlist_Cart_order {
    LinkedList cart = new LinkedList();
    LinkedList orderHistory = new LinkedList();
    Scanner sc = new Scanner(System.in);

    void addItemToCart() {
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine(); // consume newline
        cart.add(new Item(name, price, quantity));
        System.out.println("Item added to cart.\n");
    }

    void viewCart() {
        System.out.println("🛒 Cart Items:");
        cart.display();
    }

    void placeOrder() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Add items first!");
            return;
        }
        System.out.println("✅ Placing Order...");
        Node temp = cart.head;
        while (temp != null) {
            orderHistory.add(temp.item);
            temp = temp.next;
        }
        cart.clear();
        System.out.println("Order placed successfully. Cart is now empty.");
    }

    void viewOrderHistory() {
        System.out.println("📦 Order History:");
        orderHistory.display();
    }

    public static void main(String[] args) {
    	Linkedlist_Cart_order  system= new Linkedlist_Cart_order();
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add item to cart");
            System.out.println("2. View cart");
            System.out.println("3. Place order");
            System.out.println("4. View order history");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine(); // consume newline
            switch (choice) {
                case 1: system.addItemToCart(); break;
                case 2: system.viewCart(); break;
                case 3: system.placeOrder(); break;
                case 4: system.viewOrderHistory(); break;
                case 5: System.out.println("Thank you for shopping!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        input.close();
    }
}
