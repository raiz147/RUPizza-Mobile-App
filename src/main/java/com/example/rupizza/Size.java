package com.example.rupizza;

/**
 * Pizza size class
 * @author Ramazan Azimov
 */
public class Size {
    String size;

    /**
     * default constructor
     * @param s
     */
    public Size(String s)
    {
        this.size = s;
    }

    /**
     * get size
     * @return size
     */
    public String getSize(){
        return this.size;
    }

}
