package com.example.rupizza;

import java.util.ArrayList;

/**
 * this class stores all the orders placed
 * @author Ramazan Azimov
 */
public class StoreOrder implements Customizable {

    private ArrayList<Order> storeList;

    /**
     * constructor
     */
    public StoreOrder() {
        this.storeList = new ArrayList<>();
    }

    /**
     * gets number of orders placed
     * @return size
     */
    public int getSize(){
        return storeList.size();
    }

    /**
     * returns total price of an order
     * @param p position
     * @return price
     */
    public double getTotalPrice(int p){
        return storeList.get(p).getTotalPrice();
    }

    /**
     * removes order based on order number
     * @param orderNum order number
     */
    public void removeThisOrder(int orderNum)
    {
        for (int i = 0; i< storeList.size(); i++)
        {
            if (orderNum == storeList.get(i).getOrderNum()){
                storeList.remove(storeList.get(i));
            }
        }
    }

    /**
     * get order list
     * @return
     */
    public ArrayList<String> getOrderList(int orderNum)
    {
        return storeList.get(orderNum).getOrderList();
    }

    /**
     * returns arraylist of all the order numbers
     * @return order numbers
     */
    public ArrayList<String> getOrderNums()
    {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < storeList.size(); i++)
        {
            temp.add(Integer.toString(storeList.get(i).getOrderNum()));
        }
        return temp;
    }

    /**
     * add order
     * @param obj
     * @return boolean
     */
    public boolean add(Object obj){
        if (obj instanceof Order)
        {
            return storeList.add((Order) obj);
        }
        return false;
    }

    /**
     * remove order
     * @param obj
     * @return boolean
     */
    public boolean remove(Object obj){
        if (obj instanceof Order)
        {
            return storeList.remove(obj);
        }
        return false;
    }
}
