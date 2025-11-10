package com.yimmy.app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface LawyerDao {
    @Query("SELECT * FROM lawyers")
    List<Lawyer> getAll();

    @Insert
    void insert(Lawyer lawyer);

    @Delete
    void delete(Lawyer lawyer);
}
