package com.example.rupizza;

/**
 * pizza factory interface
 * @author Ramazan Azimov
 */
public interface PizzaFactory {
    /**
     * creates deluxe pizza
     * @return pizza
     */
    Pizza createDeluxe();
    /**
     * creates Meatzza pizza
     * @return pizza
     */
    Pizza createMeatzza();
    /**
     * creates BBQ Chicken pizza
     * @return pizza
     */
    Pizza createBBQChicken();
    /**
     * creates BYO pizza
     * @return pizza
     */
    Pizza createBuildYourOwn();
}
