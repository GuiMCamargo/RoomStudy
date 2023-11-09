package com.example.phonelist.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.phonelist.database.models.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT COUNT(uid) FROM user")
    suspend fun getTotalItems(): Long

}