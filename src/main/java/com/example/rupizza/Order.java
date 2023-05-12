package com.example.rupizza;

import java.util.ArrayList;

/**
 * this is the order object holding all the pizzas
 * @author Ramazan Azimov
 */
public class Order implements Customizable {

    private int orderNum;
    private ArrayList<Pizza> currentOrder;
    /**
     * default constructor
     */
    public Order(){
        currentOrder = new ArrayList<>();
    }

    /**
     * add pizza
     * @param obj
     * @return boolean
     */
    public boolean add(Object obj){
        if (obj instanceof Pizza)
        {
            return currentOrder.add((Pizza) obj);
        }
        return false;
    }
    /**
     * remove pizza
     * @param obj
     * @return boolean
     */
    public boolean remove(Object obj){
        if (obj instanceof Pizza)
        {
            return currentOrder.remove(obj);
        }
        return false;
    }

    /**
     * remove pizza
     * @param i
     */
    public void removePizza(int i)
    {
        currentOrder.remove(i);
    }

    /**
     * remove all pizzas
     */
    public void removeAll()
    {
        this.currentOrder.removeAll(this.currentOrder);
    }

    /**
     * get order list
     * @return
     */
    public ArrayList<String> getOrderList()
    {
        ArrayList<String> newOrderList = new ArrayList<>();
        for (int i = 0; i < currentOrder.size(); i++) {
            if (currentOrder.get(i) instanceof BuildYourOwn) {
                newOrderList.add(((BuildYourOwn) currentOrder.get(i)).toString1());
            }
            else if (currentOrder.get(i) instanceof BBQChicken) {
                newOrderList.add(((BBQChicken) currentOrder.get(i)).toString1());
            }
            else if (currentOrder.get(i) instanceof Meatzza) {
                newOrderList.add(((Meatzza) currentOrder.get(i)).toString1());
            }
            else if (currentOrder.get(i) instanceof Deluxe) {
                newOrderList.add(((Deluxe) currentOrder.get(i)).toString1());
            }
        }
        return newOrderList;
    }

    /**
     * get order num
     * @return
     */
    public int getOrderNum()
    {
        return this.orderNum;
    }

    public void setOrderNum(int n)
    {
        this.orderNum = n;
    }

    /**
     * get subtotal
     * @return
     */
    public double getSubtotal()
    {
        double p = 0;
        for (int i = 0; i <currentOrder.size(); i++)
        {
            if (currentOrder.get(i) instanceof Pizza)
            {
                p += ((Pizza) currentOrder.get(i)).price();
            }
        }
        return p;
    }

    /**
     * get subtotal
     * @return
     */
    public double getTotalPrice()
    {
        double p = 0;
        for (int i = 0; i <currentOrder.size(); i++)
        {
            if (currentOrder.get(i) instanceof Deluxe)
            {
                p += ((Deluxe) currentOrder.get(i)).price();
            }
            if (currentOrder.get(i) instanceof Meatzza)
            {
                p += ((Meatzza) currentOrder.get(i)).price();
            }
            if (currentOrder.get(i) instanceof BBQChicken)
            {
                p += ((BBQChicken) currentOrder.get(i)).price();
            }
            if (currentOrder.get(i) instanceof BuildYourOwn)
            {
                p += ((BuildYourOwn) currentOrder.get(i)).price();
            }
        }
        p = p + (p * 0.06625);
        return p;
    }
}