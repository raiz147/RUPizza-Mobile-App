package com.example.rupizza;

/**
 * recycler view layout
 * @author Ramazan Azimov
 */
public class PizzaItem {

    private String name;
    private int imageUrl;

    /**
     * constructor
     * @param name pizza name
     * @param imageUrl image
     */
    public PizzaItem(String name, int imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    /**
     * gets pizza name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * gets image url
     * @return image
     */
    public int getImageUrl() {
        return imageUrl;
    }

    /**
     * sets name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }
}
