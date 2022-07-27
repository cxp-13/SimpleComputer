package com.example.simplecomputer.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operation")

data class OperationEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "first_arg") var firstArg: Int,
    @ColumnInfo(name = "second_arg") var secondArg: Int,
    @ColumnInfo(name = "result") var result: Int,

    @ColumnInfo(name = "type") var type: Char,
    @ColumnInfo(name = "date") var date: Long = System.currentTimeMillis()
)