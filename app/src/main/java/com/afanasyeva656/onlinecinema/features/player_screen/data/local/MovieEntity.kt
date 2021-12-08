package com.afanasyeva656.onlinecinema.features.player_screen.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afanasyeva656.onlinecinema.features.player_screen.data.local.MovieEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "originalTitle")
    val originalTitle: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "video")
    val video: String,

    @ColumnInfo(name = "playback_position")
    val playbackPosition: Long
) {
    companion object {
        const val TABLE_NAME = "WATCHED_MOVIE_TABLE"
    }
}