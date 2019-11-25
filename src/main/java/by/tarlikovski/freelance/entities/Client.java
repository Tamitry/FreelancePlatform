package by.tarlikovski.freelance.entities;

import java.util.ArrayList;
import java.util.Calendar;

public class Client extends User {
    private ArrayList<Order> orders;

    public Client() {
    }

    public Client(final String first, final String last, final Calendar reg,
                  final String e, final String login, final int password,
                  final short role, final ArrayList<Order> ord) {
        super(first, last, reg, e, login, password, role);
        this.orders = ord;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(final ArrayList<Order> ord) {
        this.orders = ord;
    }
}
