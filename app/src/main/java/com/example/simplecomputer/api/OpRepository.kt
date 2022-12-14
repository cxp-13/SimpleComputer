package com.example.simplecomputer.api

import android.content.Context
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.paging.OpPagingSource
import com.example.simplecomputer.repository.OperationRepository
import kotlinx.coroutines.flow.Flow
/**
 * @Author:cxp
 * @Date: 2022/8/6 20:06
 * @Description:显示来自 PagingSource 的 PagingData 对象的响应式流
*/

object OpRepository {
    private const val PAGE_SIZE = 10
    var context: Context? = null
    private lateinit var operationRepository: OperationRepository


    fun getPagingData(): Flow<PagingData<OperationEntity>> {
        operationRepository = OperationRepository(context!!)

        return Pager(config = PagingConfig(PAGE_SIZE), pagingSourceFactory = {
            OpPagingSource(operationRepository)
        }).flow
    }
}