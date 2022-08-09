package com.example.simplecomputer.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.example.simplecomputer.dao.OperationDao
import com.example.simplecomputer.db.OperationDataBase
import com.example.simplecomputer.entity.OperationEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import java.util.*


/**
 * @Author:cxp
 * @Date: 2022/8/4 14:09
 * @Description:数据库的Control层
 */

class OperationRepository(var context: Context) {
    //获取Dao层对象
    private var operationDao: OperationDao? = null

    init {
        operationDao = OperationDataBase.getInstance(context)?.getOperationDao()
    }

     suspend fun insert(operationEntity: OperationEntity) {
            operationDao?.insert(operationEntity)
    }

    //    room返回livedata或者rxjava相关类型时，会使用异步查询，所以一开始会返回null值。
    fun getAllLiveData(): LiveData<List<OperationEntity>>? {
        return operationDao?.queryLiveData()
    }

    suspend fun getAll(): List<OperationEntity>? {
        var items: List<OperationEntity> = LinkedList<OperationEntity>()
//        val job: Job = CoroutineScope(Dispatchers.IO).launch {
//            val temp: Deferred<List<OperationEntity>> = async(context = Dispatchers.IO) {
//                operationDao!!.query()
//            }
//            items = temp.await()
//        }
//        job.join()

//        val job: Job = CoroutineScope(currentCoroutineContext()).launch {
//                items = operationDao!!.query()
//        }
//        val job = CoroutineScope(currentCoroutineContext()).launch(Dispatchers.IO) {
//            items = operationDao!!.query()
//        }
//        Log.i("record", "getAll: ${items}")
////        items = job.await()
//        job.join()
        items = withContext(Dispatchers.IO) {
            operationDao!!.query()
        }
        return items
    }

    //获取指定id的历史记录
    fun getList(id: Int): LiveData<List<OperationEntity>>? {
        return operationDao?.queryList(id)
    }


    fun getAllPaging(): PagingSource<Int, OperationEntity>? {
        return operationDao?.queryPaging()
    }
}