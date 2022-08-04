package com.example.simplecomputer.databinding

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @Author:cxp
 * @Date: 2022/8/4 10:18
 * @Description:显示计算公式区域的字符串
*/
class Record(tempValue: StringBuffer) {
    var str = MutableLiveData<StringBuffer>()
//    无效，可能是已经有同名的方法
//        set(value) {
//            Log.d("record", ": $value")
//            field = value
//        }
    init {
        str.value = tempValue
    }


}