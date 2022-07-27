package com.example.simplecomputer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simplecomputer.entity.OperationEntity

@Dao
interface OperationDao {
    @Insert
    fun insert(operationDao: OperationDao)

    @Query("select * from operation limit 1 order by date desc")
    fun query():OperationEntity

}