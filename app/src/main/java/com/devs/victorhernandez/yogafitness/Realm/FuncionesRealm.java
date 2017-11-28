package com.devs.victorhernandez.yogafitness.Realm;

import java.util.WeakHashMap;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by victorhernandez on 17/9/17.
 */

public class FuncionesRealm {
    //Método para agrega
    public void EstablecerModo(Realm realm, Integer Modo) {
            try {
                realm.beginTransaction();
                YogaDB mode = new YogaDB();
                mode.setMode(Modo);
                realm.copyToRealmOrUpdate(mode);
                System.out.println("se guardo en BD");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                realm.cancelTransaction();
                System.out.println("error al guardar catch"+e);

            } finally {
                realm.commitTransaction();
            }
    }

    //Método para recuperar todas las categorias
    public RealmResults<YogaDB> recuperadatos(Realm realm) {
        RealmResults<YogaDB> homes = null;
        try {
            homes = realm.where(YogaDB.class).equalTo("key","mode").findAllSorted("Mode",Sort.DESCENDING);
        } catch (Exception e) {
        }
        return homes;
    }


    //Método para recuperar los dias
    public RealmResults<WorkoutDays> recuperadatosWork(Realm realm) {
        RealmResults<WorkoutDays> homes = null;
        try {
            homes = realm.where(WorkoutDays.class).findAllSorted("Day",Sort.DESCENDING);
        } catch (Exception e) {
        }
        return homes;
    }

    public void EstablecerDias(Realm realm, String day) {
        try {

            realm.beginTransaction();
            WorkoutDays mode = new WorkoutDays();
            mode.setID(getNextUserId(realm));
            mode.setDay(day);
            realm.copyToRealmOrUpdate(mode);
            System.out.println("se guardo en BD");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            realm.cancelTransaction();
            System.out.println("error al guardar catch"+e);

        } finally {
            realm.commitTransaction();
        }
    }

    public Integer getNextUserId(Realm realm) {
        // 初期化
        Integer nextUserId = 1;
        // userIdの最大値を取得
        Number maxUserId = realm.where(WorkoutDays.class).max("ID");
        // 1度もデータが作成されていない場合はNULLが返ってくるため、NULLチェックをする
        if(maxUserId != null) {
            nextUserId = maxUserId.intValue() + 1;
        }
        return nextUserId;
    }

}
