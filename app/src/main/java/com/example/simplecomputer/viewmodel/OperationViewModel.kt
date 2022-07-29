package com.example.simplecomputer.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.repository.OperationRepository

class OperationViewModel:ViewModel() {

    var repository: OperationRepository? = null

    fun getUsers(): LiveData<List<OperationEntity>>? {
        return repository?.getAllLiveData()
    }

}