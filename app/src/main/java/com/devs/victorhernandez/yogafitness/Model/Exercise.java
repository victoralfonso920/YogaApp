package com.devs.victorhernandez.yogafitness.Model;

/**
 * Created by victorhernandez on 10/9/17.
 */

public class Exercise {
    //variables
    private int image_id;
    private String name;
    //constructor
    public Exercise(int image_id, String name) {
        this.image_id = image_id;
        this.name = name;
    }
    //get y set
    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
