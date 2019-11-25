package by.tarlikovski.freelance.entities;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Tarlikovski
 * @version 1.0
 * @since 16.11.2019
 */
public class Order extends Entity {
    private String name;
    private String description;
    private int clientId;
    private Calendar dateOfStart;
    private Calendar dateOfEnd;
    private ArrayList<OrderCategory> orderCategories;

    public Order() {
    }

    public Order(final int id, final String nm, final String desc,
                 final Calendar dos, final Calendar doe,
                 final int clid) {
        setId(id);
        this.name = nm;
        this.description = desc;
        this.dateOfStart = dos;
        this.dateOfEnd = doe;
        this.clientId = clid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String n) {
        this.name = n;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String desc) {
        this.description = desc;
    }

    public Calendar getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(final Calendar dos) {
        this.dateOfStart = dos;
    }

    public Calendar getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(final Calendar doe) {
        this.dateOfEnd = doe;
    }

    public int getClient() {
        return clientId;
    }

    public void setClient(final int cl) {
        this.clientId = cl;
    }

    public ArrayList<OrderCategory> getOrderCategories() {
        return orderCategories;
    }

    public void setOrderCategories(final ArrayList<OrderCategory> l) {
        this.orderCategories = l;
    }
}
