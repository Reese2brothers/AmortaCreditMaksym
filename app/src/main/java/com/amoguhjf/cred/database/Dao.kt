package com.amoguhjf.cred.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAll(): Flow<List<Note>>

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM Note")
    suspend fun deleteAll()

     @Upsert
    suspend fun upsert(note: Note)

}

@Dao
interface TeamsDao {
    @Query("SELECT * FROM Teams")
    fun getAll(): Flow<List<Teams>>

    @Insert
    suspend fun insert(teams: Teams)

    @Delete
    suspend fun delete(teams: Teams)

    @Query("DELETE FROM Teams WHERE name = :t1")
    suspend fun deleteByName(t1: String)

    @Query("DELETE FROM Teams")
    suspend fun deleteAll()

     @Upsert
    suspend fun upsert(teams: Teams)

    @Query("SELECT * FROM teams WHERE name = :t1 LIMIT 1")
    suspend fun getFavoriteTeam(t1: String): Teams?

}

@Dao
interface MatchesDao {
    @Query("SELECT * FROM Matches")
    fun getAll(): Flow<List<Matches>>

    @Insert
    suspend fun insert(matches: Matches)

    @Delete
    suspend fun delete(matches: Matches)

    @Query("DELETE FROM Matches WHERE namefirst = :t1 AND namesecond = :t2")
    suspend fun deleteByNames(t1: String, t2: String)

    @Query("DELETE FROM Matches")
    suspend fun deleteAll()

     @Upsert
    suspend fun upsert(matches: Matches)

    @Query("SELECT * FROM matches WHERE namefirst = :t1 AND namesecond = :t2 LIMIT 1")
    suspend fun getFavoriteMatch(t1: String, t2: String): Matches?

}

@Dao
interface PredictionsDao {
    @Query("SELECT * FROM Predictions")
    fun getAll(): Flow<List<Predictions>>

    @Insert
    suspend fun insert(predictions: Predictions)

    @Delete
    suspend fun delete(predictions: Predictions)

    @Query("DELETE FROM Predictions")
    suspend fun deleteAll()

     @Upsert
    suspend fun upsert(predictions: Predictions)

}