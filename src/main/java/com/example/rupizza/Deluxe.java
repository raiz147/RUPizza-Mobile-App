package com.example.rupizza;

import java.util.ArrayList;

/**
 * this is a deluxe pizza object
 * @author Ramazan Azimov
 */
public class Deluxe extends Pizza{

    /**
     * default constructor
     */
    public Deluxe(){
        super.initialize();
        super.addToppings(new Topping("Sausage"));
        super.addToppings(new Topping("Pepperoni"));
        super.addToppings(new Topping("Green Pepper"));
        super.addToppings(new Topping("Onion"));
        super.addToppings(new Topping("Mushroom"));
    }
    /**
     * gets price of pizza
     * @return price
     */
    public double price() {
        if (super.getSize().getSize().compareTo("Small") == 0)
            return 14.99;
        else if (super.getSize().getSize().compareTo("Medium") == 0)
            return 16.99;
        else
            return 18.99;
    }
    /**
     * adds a topping
     * @param obj object
     * @return boolean
     */
    public boolean add(Object obj){
        if (obj instanceof Topping)
        {
            super.addToppings((Topping)obj);
            return true;
        }
        return false;
    }
    /**
     * removes a topping
     * @param obj object
     * @return boolean
     */
    public boolean remove(Object obj){
        if (obj instanceof Topping)
        {
            super.removeToppings((Topping)obj);
            return true;
        }
        return false;
    }

    /**
     * tostring
     * @return string
     */
    public String toString1()
    {
        StringBuilder toppings = new StringBuilder();
        ArrayList<Topping> t = getToppings();
        String type = "";
        if (getCrust().getCrust().equals("Deep Dish") || getCrust().getCrust().equals("Pan")
                || getCrust().getCrust().equals("Stuffed"))
            type = "Chicago Style";
        else if (getCrust().getCrust().equals("Brooklyn") || getCrust().getCrust().equals("Thin")
                || getCrust().getCrust().equals("Hand Tossed"))
            type = "New York Style";
        for (Topping topping : t) {
            toppings.append(topping.getTopping()).append(", ");
        }
        return "Deluxe (" + type +" - " + getCrust().getCrust() + ") " + toppings + "$" + price();
    }
}
