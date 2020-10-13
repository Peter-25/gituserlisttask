package com.example.userdetail.commentdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

//    @Query("SELECT * FROM dbuser WHERE ((task LIKE :fl) || (`desc` LIKE :fl))")
//    List<DBUser> getAll(String fl);
//
//    @Query("SELECT * FROM dbuser WHERE date BETWEEN :dayst AND :dayet")
//    List<DBUser> getFromTable(long dayst, long dayet);
//
//    @Query("SELECT * FROM dbuser WHERE date IN (:dayst)")
//    List<DBUser> getData(long dayst);

    @Query("SELECT * FROM dbuser WHERE ((userId == :id))")
    List<DBUser> getAll(int id);

    @Insert
    void insert(DBUser task);

//    @Delete
//    void delete(ToDo task);
//
//    @Update
//    void update(ToDo task);

}
