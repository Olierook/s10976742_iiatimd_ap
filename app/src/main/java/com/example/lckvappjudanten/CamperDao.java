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

//    @Query("SELECT * FROM camper WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert
    void insertAll(Camper... campers);

    @Delete
    void delete(Camper camper);
}
