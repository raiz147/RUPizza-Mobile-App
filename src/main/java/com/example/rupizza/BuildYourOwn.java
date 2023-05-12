package com.example.rupizza;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * this is a Build Your Own pizza Object
 * @author Ramazan Azimov
 */
public class BuildYourOwn extends Pizza{


    /**
     * default constructor
     */
    public BuildYourOwn(){
        super.initialize();
    }

    /**
     * gets price of pizza
     * @return pizza price
     */
    public double price() {
        ArrayList<Topping> t = super.getToppings();
        int count = 0;
        for (int i = 0; i < t.size(); i++)
        {
            if (t.get(i) != null){
                count ++;
            }
        }
        if (super.getSize().getSize().compareTo("Small") == 0)
        {
            double num = (8.99 + (1.59 * count));
            BigDecimal bd = new BigDecimal(num).setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
        else if (super.getSize().getSize().compareTo("Medium") == 0)
        {
            double num = (10.99 + (1.59 * count));
            BigDecimal bd = new BigDecimal(num).setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
        else
        {
            double num = (12.99 + (1.59 * count));
            BigDecimal bd = new BigDecimal(num).setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
    }

    /**
     * adds a topping
     * @param obj object
     * @return boolean
     */
    public boolean add(Object obj){
        if (super.getNumToppings() == 7)
            return false;
        if (obj instanceof Topping)
        {
            return super.addToppings((Topping)obj);
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
            return super.removeToppings((Topping) obj);
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
        return "Build Your Own (" + type +" - " + getCrust().getCrust() + ") " + toppings + "$" + price();
    }
}
