package org.example.message.transactions;

public class Main {

    public static void main(String[] args) {

        Order order = new Order(1,"Milk",1000);

        OrderService orderService = new OrderService();
        orderService.sendOrder(order);
        System.out.println("Order sent from Main.");

        Order order1 = new Order(2,"Chai", 20);
        orderService.sendOrder(order1);

        InventoryService inventoryService = new InventoryService();
        inventoryService.processOrders();
        System.out.println("Processed Orders in Main.");

    }

}
