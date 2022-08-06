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