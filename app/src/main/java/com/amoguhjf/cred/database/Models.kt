package com.amoguhjf.cred.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Credit(
     @PrimaryKey(autoGenerate = true)
    val id: Int,
    val currency: String,
    val creditamount: String,
    val percentagerate: String,
    val creditperiod: String,
    val permonthpay: String,
    val pereplata: String,
    val allamount: String,
    val finorg: String,
    val creditdate: String
)
