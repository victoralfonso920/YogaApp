package com.devs.victorhernandez.yogafitness.Realm;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by victorhernandez on 29/10/17.
 */

public class WorkoutDays extends RealmObject  {

    @PrimaryKey
    private Integer ID ;
    private String Day;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }


}
