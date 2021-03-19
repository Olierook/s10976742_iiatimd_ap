package com.example.lckvappjudanten;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CamperDao {

    @Query("SELECT * FROM camper")
    List<Camper> getAll();

//    @Query("SELECT * FROM camper WHERE uuid IN (:userIds)")
//    List<Camper> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM camper WHERE user_id LIKE :uid")
    Camper[] findByUserId(int uid);

    @Query("SELECT * FROM camper WHERE id LIKE :id")
    Camper[] getCamper(String id);

    @Query("UPDATE camper SET currentBalance = :newBalance WHERE id LIKE :id")
    void makeSale(Double newBalance, String id);

    @Query("DELETE FROM camper WHERE user_id LIKE :uid")
    void deleteAll(int uid);

    @Insert
    void insertAll(Camper... campers);

    @Delete
    void delete(Camper camper);
}
