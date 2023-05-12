package com.example.rupizza;
import java.util.ArrayList;

/**
 * this is a Pizza object
 * @author Ramazan Azimov
 */
public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;
    public abstract double price();

    /**
     * sets crust
     * @param c
     */
    public void setCrust(String c) {
        this.crust = new Crust(c);
    }

    /**
     * gets crust
     * @return crust
     */
    public Crust getCrust() {
        return this.crust;
    }

    /**
     * sets size
     * @param s
     */
    public void setSize(String s) {
        this.size = new Size(s);
    }

    /**
     * gets size
     * @return
     */
    public Size getSize() {
        return this.size;
    }

    /**
     * adds topping
     * @param t
     * @return boolean
     */
    public boolean addToppings(Topping t) {
        return this.toppings.add(t);
    }

    /**
     * initializes toppings
     */
    public void initialize(){
        this.toppings = new ArrayList<>();
    }

    /**
     * gets all toppings
     * @return
     */
    public ArrayList<Topping> getToppings(){
        return this.toppings;
    }

    /**
     * gets number of toppings
     * @return num
     */
    public int getNumToppings(){
        return this.toppings.size();
    }

    /**
     * removes a topping
     * @param t
     * @return boolean
     */
    public boolean removeToppings(Topping t) {
        return this.toppings.remove(t);
    }

}
