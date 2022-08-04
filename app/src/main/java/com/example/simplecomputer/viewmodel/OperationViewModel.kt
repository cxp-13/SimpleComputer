package com.example.simplecomputer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.repository.OperationRepository
/**
 * @Author:cxp
 * @Date: 2022/8/4 14:16
 * @Description:ViewModel 类旨在以注重生命周期的方式存储和管理界面相关的数据
*/

class OperationViewModel : ViewModel() {

    var repository: OperationRepository? = null
//存放点击单个历史记录过后的列表
    var operationEntityListForId: LiveData<List<OperationEntity>>? = null

    var liveId: MutableLiveData<Int> = MutableLiveData(800)

//直接返回全部的数据
    fun getOperations(): LiveData<List<OperationEntity>>? {
        return repository?.getAllLiveData()
    }
//返回包含要操作的历史记录的列表
    fun setOperationEntityListForId(id: Int) {
        operationEntityListForId = repository?.getList(id)
        Log.e("record", "setOperationEntity: ${operationEntityListForId?.value}", )
    }




}