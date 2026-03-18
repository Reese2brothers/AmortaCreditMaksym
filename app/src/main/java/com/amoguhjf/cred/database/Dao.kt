package com.amoguhjf.cred.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CreditDao {
    @Query("SELECT * FROM Credit")
    fun getAll(): Flow<List<Credit>>

    @Insert
    suspend fun insert(credit: Credit)

    @Delete
    suspend fun delete(credit: Credit)

    @Query("DELETE FROM Credit")
    suspend fun deleteAll()

     @Upsert
    suspend fun upsert(credit: Credit)

}