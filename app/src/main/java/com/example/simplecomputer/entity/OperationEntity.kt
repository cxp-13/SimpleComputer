package com.example.simplecomputer.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operation")
data class OperationEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "first_arg") var firstArg: Double,
    @ColumnInfo(name = "second_arg") var secondArg: Double,
    var result: Double,
    var type: Char,
    var date: Long
)