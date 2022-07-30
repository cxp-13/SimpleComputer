package com.example.simplecomputer.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.simplecomputer.dao.OperationDao
import com.example.simplecomputer.db.OperationDataBase
import com.example.simplecomputer.entity.OperationEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OperationRepository(var context: Context) {

    private var operationDao: OperationDao? = null

    init {
        operationDao = OperationDataBase.getInstance(context).getOperationDao()
    }

    fun insert(operationEntity: OperationEntity) {
        GlobalScope.launch {
            operationDao?.insert(operationEntity)
        }
    }

    //    room返回livedata或者rxjava相关类型时，会使用异步查询，所以一开始会返回null值。
    fun getAllLiveData(): LiveData<List<OperationEntity>>? {
        return operationDao?.queryLiveData()
    }

    fun getAll(): List<OperationEntity>? {
        var temp: List<OperationEntity>? = null
        GlobalScope.launch {
            temp = operationDao!!.query()
        }
        return temp
    }

    fun getList(id: Int): LiveData<List<OperationEntity>>? {
       return operationDao?.queryList(id)
    }

}