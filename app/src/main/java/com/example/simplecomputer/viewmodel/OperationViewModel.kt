package com.example.simplecomputer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.repository.OperationRepository

class OperationViewModel : ViewModel() {

    var repository: OperationRepository? = null

    var operationEntityListForId: LiveData<List<OperationEntity>>? = null

    var liveId: MutableLiveData<Int> = MutableLiveData(800)


    fun getOperations(): LiveData<List<OperationEntity>>? {
        return repository?.getAllLiveData()
    }

    fun setOperationEntity(id: Int) {
        operationEntityListForId = repository?.getList(id)
        Log.e("record", "setOperationEntity: ${operationEntityListForId?.value}", )

    }




}