package com.devs.victorhernandez.yogafitness.Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by victorhernandez on 17/9/17.
 */

public class YogaDB extends RealmObject {
    @PrimaryKey
    private String key = "mode";
    private  Integer Mode;

    public Integer getMode() {
        return Mode;
    }

    public void setMode(Integer mode) {
        Mode = mode;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
