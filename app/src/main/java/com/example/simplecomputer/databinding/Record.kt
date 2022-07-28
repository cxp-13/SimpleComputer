package com.example.simplecomputer.databinding

import android.util.Log
import androidx.databinding.ObservableField

class Record(tempValue: StringBuffer) {
    var value = ObservableField<StringBuffer>()
//    无效，可能是已经有同名的方法
//        set(value) {
//            Log.d("record", ": $value")
//            field = value
//        }


    init {
        value.set(tempValue)
    }


}