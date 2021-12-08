package com.afanasyeva656.onlinecinema.features.player_screen.data.local

import androidx.room.*
import retrofit2.http.DELETE

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWatchedMovie(entity: MovieEntity)

    @Update
    suspend fun updateWatchedMovie(entity: MovieEntity)

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME}")
    suspend fun getAllWatchedMovies(): List<MovieEntity>

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getWatchedMovieById(id: Int): MovieEntity

    @Query("SELECT playback_position FROM ${MovieEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getPlaybackPositionOfWatchedMovie(id: Int) : Long

    @DELETE
    suspend fun deleteWatchedMovie(entity: MovieEntity)
}