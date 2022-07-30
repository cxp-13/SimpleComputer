package com.example.simplecomputer.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecomputer.R
import com.example.simplecomputer.databinding.ItemBinding
import com.example.simplecomputer.databinding.Record
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.viewmodel.OperationViewModel
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(var datas: List<OperationEntity>, var myViewModel: OperationViewModel) :
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

//记忆功能，选择历史记录编辑
        val textViewId: TextView = holder.dataBinding.textViewId
//        val aimId: CharSequence = textViewId.text


        textViewId.setOnClickListener {
            val aimId: CharSequence? = textViewId.text
            val str = aimId.toString()

            myViewModel.setOperationEntity(str.toInt())

            val controller = holder.itemView.findNavController()
            controller.navigate(R.id.action_historyFragment_to_computerFragment)
        }

    }

    override fun getItemCount(): Int {
        return datas.size
    }
}