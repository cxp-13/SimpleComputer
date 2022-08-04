package com.example.simplecomputer.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.simplecomputer.entity.OperationEntity
/**
 * @Author:cxp
 * @Date: 2022/8/4 10:14
 * @Description:运算记录数据访问对象
*/

@Dao
interface OperationDao {
    //    suspend 报错，会导致多了一个参数  ---》  kotlin.coroutines.Continuation<? super kotlin.Unit> continuation
//    插入一条运算记录
    @Insert
    fun insert(operationEntity: OperationEntity)
//   查询全部的运算记录(数据存储器类)
    @Query("select * from operation ")
    fun queryLiveData(): LiveData<List<OperationEntity>>
//   查询全部的运算记录
    @Query("select * from operation ")
    fun query(): List<OperationEntity>
//  查询指定id的运算记录
    @Query("select * from operation where id = :id")
    fun queryList(id: Int): LiveData<List<OperationEntity>>
//  更新单个运算记录
    @Update(entity = OperationEntity::class)
    fun update(operationEntity: OperationEntity)
}