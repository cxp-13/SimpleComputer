package com.example.simplecomputer.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.repository.OperationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
/**
 * @Author:cxp
 * @Date: 2022/8/6 20:06
 * @Description:按页码加载各页对象
*/

class OpPagingSource(private val operationRepository: OperationRepository) :
    PagingSource<Int, OperationEntity>() {

    override fun getRefreshKey(state: PagingState<Int, OperationEntity>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OperationEntity> {
        return try {
            val key = params.key ?: 1
            val loadSize = params.loadSize

            var items: List<OperationEntity>? = operationRepository.getAll()

//            GlobalScope.launch {
//                val temp = async(Dispatchers.IO) {
//                    operationRepository.getAll()
//                }
//                items = temp.await()
//            }.join()
            val preKey = if (key > 1) key - 1 else null
            val nextKey = if (items?.isNotEmpty()!!) key + 1 else null
            LoadResult.Page(items!!, preKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}