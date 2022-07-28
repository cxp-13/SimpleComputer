package com.example.simplecomputer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simplecomputer.entity.OperationEntity

@Dao
interface OperationDao {
//    suspend 报错，会导致多了一个参数  ---》  kotlin.coroutines.Continuation<? super kotlin.Unit> continuation
    @Insert
   fun insert(operationEntity: OperationEntity)

//    @Query("select * from operation ")
//    fun query() : List<OperationEntity>

}