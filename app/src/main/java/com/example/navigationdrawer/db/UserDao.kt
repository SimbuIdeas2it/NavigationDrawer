package com.example.navigationdrawer.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("select * from user where username = :username and password = :password")
    fun checkCredentail(username: String, password: String): LiveData<List<User>>

    @Query("select * from user")
    fun getAllUsers(): LiveData<List<User>>
}