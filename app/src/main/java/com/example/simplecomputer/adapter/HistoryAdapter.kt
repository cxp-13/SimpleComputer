package com.example.simplecomputer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecomputer.R
import com.example.simplecomputer.databinding.ItemBinding
import com.example.simplecomputer.entity.OperationEntity
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(var datas: List<OperationEntity>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    fun setData(datas: List<OperationEntity>) {
        this.datas = datas
    }

    inner class HistoryHolder(var dataBinding: ItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {

        var dataBinding: ItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item,
            parent,
            false
        )
        return HistoryHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.dataBinding.operation = datas[position]
        val entity = datas[position]
        val timeStamp = entity.date
        val date = Date(timeStamp)
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        holder.dataBinding.operationDate = format.format(date)

    }

    override fun getItemCount(): Int {
        return datas.size
    }
}