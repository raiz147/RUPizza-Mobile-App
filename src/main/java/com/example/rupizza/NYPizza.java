package com.example.rupizza;

/**
 * this is the New York Pizza implementation
 * @author Ramazan Azimov
 */
public class NYPizza implements PizzaFactory {
    /**
     * creates deluxe pizza
     * @return pizza
     */
    public Pizza createDeluxe(){
        Pizza p = new Deluxe();
        p.setCrust("Brooklyn");

        return p;
    }
    /**
     * creates BBQChicken Pizza
     * @return pizza
     */
    public Pizza createBBQChicken(){
        Pizza p = new BBQChicken();
        p.setCrust("Thin");

        return p;
    }
    /**
     * creates Meatzza
     * @return pizza
     */
    public Pizza createMeatzza(){
        Pizza p = new Meatzza();
        p.setCrust("Hand Tossed");

        return p;
    }
    /**
     * creates BYO Pizza
     * @return pizza
     */
    public Pizza createBuildYourOwn(){
        Pizza p = new BuildYourOwn();
        p.setCrust("Hand Tossed");

        return p;
    }
}
