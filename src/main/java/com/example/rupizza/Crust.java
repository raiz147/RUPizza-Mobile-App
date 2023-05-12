package com.example.rupizza;

/**
 * this is a pizza Crust object
 * @author Ramazan Azimov
 */
public class Crust {
    String crust;

    /**
     * defualt constructor
     * @param c
     */
    public Crust(String c)
    {
        this.crust = c;
    }

    /**
     * gets crust
     * @return
     */
    public String getCrust(){
        return this.crust;
    }
}
