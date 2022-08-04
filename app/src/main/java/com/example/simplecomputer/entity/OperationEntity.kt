package com.example.simplecomputer.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * @Author:cxp
 * @Date: 2022/8/4 11:55
 * @Description:数据实体
*/

@Entity(tableName = "operation")
data class OperationEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "first_arg") var firstArg: Double,
    @ColumnInfo(name = "second_arg") var secondArg: Double,
    var result: Double,
    var type: Char,
    var date: Long
)