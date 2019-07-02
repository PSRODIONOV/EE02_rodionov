package be.entity;

import java.util.HashMap;


public class Order {

    private HashMap<String, Integer>  listItems;
    private double total_price;

    public HashMap<String, Integer> getListItems() {
        return listItems;
    }

    public void setListItems(HashMap<String, Integer> listItems) {
        this.listItems = listItems;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
