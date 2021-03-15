package com.example.lckvappjudanten;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM product")
    List<Product> getAll();

//    @Query("SELECT * FROM camper WHERE uuid IN (:userIds)")
//    List<Camper> loadAllByIds(int[] userIds);

//    @Query("SELECT * FROM product WHERE user_id LIKE :id")
//    Product[] findByUserId(int );

    @Insert
    void insertAll(Product... products);

    @Delete
    void delete(Product product);
}
