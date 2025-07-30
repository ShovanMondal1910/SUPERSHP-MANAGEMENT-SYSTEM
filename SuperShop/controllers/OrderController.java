package controllers;
import models.*;

public class OrderController {

    public void insertOrder(Order o) {
        Order orders[] = this.getAllOrders();

        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                orders[i] = o;
                break;
            }
        }

        this.write(orders);
    }

    public void updateOrder(Order o) {
        Order orders[] = this.getAllOrders();

        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                if (orders[i].getOrderId().equals(o.getOrderId())) {
                    orders[i] = o;
                }
            }
        }

        this.write(orders);
    }

    public void deleteOrder(String orderId) {
        Order orders[] = this.getAllOrders();

        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                if (orders[i].getOrderId().equals(orderId)) {
                    orders[i] = null;
                }
            }
        }

        this.write(orders);
    }

    public Order searchOrderById(String orderId) {
        Order orders[] = this.getAllOrders();
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                if (orders[i].getOrderId().equals(orderId)) {
                    return orders[i];
                }
            }
        }
        return null;
    }

    public Order[] searchOrdersByUserId(String userId) {
        Order orders[] = this.getAllOrders();
        Order result[] = new Order[100];

        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                if (orders[i].getUserId().equals(userId)) {
                    result[i] = orders[i];
                }
            }
        }

        return result;
    }

    public Order[] getAllOrders() {
        String fileName = "controllers/data/Orders.txt";

        FileIO fio = new FileIO();
        String data[] = fio.readFile(fileName);

        Order orders[] = new Order[100];

        for (int i = 0; i < data.length; i++) {
            if(data[i] != null && !data[i].trim().isEmpty()){
                Order o = new Order();
                orders[i] = o.formOrder(data[i]);
            }
        }

        return orders;
    }

    public void write(Order orders[]) {
        String data[] = new String[100];

        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                data[i] = orders[i].toStringOrder();
            }
        }

        String fileName = "controllers/data/Orders.txt";
        FileIO fio = new FileIO();
        fio.writeFile(fileName, data);
    }
}
