package com.example.rupizza;

/**
 * this is a Chicago Pizza implementation
 * @author Ramazan Azimov
 */
public class ChicagoPizza implements PizzaFactory{

    /**
     * creates deluxe pizza
     * @return pizza
     */
    public Pizza createDeluxe(){
        Pizza p = new Deluxe();
        p.setCrust("Deep Dish");

        return p;
    }
    /**
     * creates BBQChicken Pizza
     * @return pizza
     */
    public Pizza createBBQChicken(){
        Pizza p = new BBQChicken();
        p.setCrust("Pan");

        return p;
    }
    /**
     * creates Meatzza
     * @return pizza
     */
    public Pizza createMeatzza(){
        Pizza p = new Meatzza();
        p.setCrust("Stuffed");

        return p;
    }
    /**
     * creates BYO Pizza
     * @return pizza
     */
    public Pizza createBuildYourOwn(){
        Pizza p = new BuildYourOwn();
        p.setCrust("Pan");

        return p;
    }
}
