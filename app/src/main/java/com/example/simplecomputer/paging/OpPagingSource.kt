package com.example.simplecomputer.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.repository.OperationRepository
import java.lang.Exception

class OpPagingSource(private val operationRepository: OperationRepository):PagingSource<Int, OperationEntity>() {

    override fun getRefreshKey(state: PagingState<Int, OperationEntity>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OperationEntity> {
        return try {
            val key = params.key ?: 1
            val loadSize = params.loadSize

            val items = operationRepository.getAllPaging()
            val preKey = if (key > 1) key - 1 else null
            val nextKey = if (items?.isNotEmpty()!!) key + 1 else null
            LoadResult.Page(items, preKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}