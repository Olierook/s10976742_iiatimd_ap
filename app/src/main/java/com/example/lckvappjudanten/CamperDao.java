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

    @Query("SELECT * FROM camper WHERE tentNumber LIKE :tent")
    Camper[] findByTentNumber(int tent);

    @Insert
    void insertAll(Camper... campers);

    @Delete
    void delete(Camper camper);
}
