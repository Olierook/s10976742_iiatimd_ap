package com.example.lckvappjudanten;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ActiveUserDao {

    @Query("SELECT * FROM activeUser")
    ActiveUser[] getAll();

//    @Query("SELECT * FROM camper WHERE uuid IN (:userIds)")
//    List<Camper> loadAllByIds(int[] userIds);

//    @Query("SELECT * FROM product WHERE user_id LIKE :id")
//    Product[] findByUserId(int );

    @Insert
    void insertAll(ActiveUser... activeUsers);

    @Delete
    void delete(ActiveUser activeUser);
}
