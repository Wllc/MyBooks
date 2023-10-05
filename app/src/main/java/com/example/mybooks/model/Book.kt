package com.example.mybooks.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    var title:String,
    var author:String,
    var note: Float,
    var year:Int
){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}