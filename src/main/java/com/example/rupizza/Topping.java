package com.example.rupizza;

/**
 * pizza topping class
 * @author Ramazan Azimov
 */
public class Topping {
    String topping;

    /**
     * default constructor
     * @param t
     */
    public Topping(String t)
    {
        this.topping = t;
    }

    /**
     * get topping
     * @return topping
     */
    public String getTopping()
    {
        return this.topping;
    }
}
