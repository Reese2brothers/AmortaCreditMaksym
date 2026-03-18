package com.amoguhjf.cred.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
     @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val category: String,
    val notes: String,
    val tags: String,
    val date: String,
    val time: String
)

@Entity
data class Teams(
     @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: Int,
    val name: String,
    val league: String,
    val isfavorite: Boolean
)

@Entity
data class Matches(
     @PrimaryKey(autoGenerate = true)
    val id: Int,
    val imagefirst: Int,
    val imagesecond: Int,
    val namefirst: String,
    val namesecond: String,
    val league: String,
    val countfirst: String,
    val countsecond: String,
    val isfavorite: Boolean,
    val date: String,
    val time: String
)

@Entity
data class Predictions(
     @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameone: String,
    val nametwo: String,
    val date: String,
    val time: String,
    val predict: String
)
