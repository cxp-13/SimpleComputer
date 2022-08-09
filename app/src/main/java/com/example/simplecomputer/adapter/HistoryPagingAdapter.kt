package com.example.simplecomputer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecomputer.R
import com.example.simplecomputer.databinding.ItemBinding
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.viewmodel.OperationViewModel
import java.text.SimpleDateFormat
import java.util.*
/**
 * @Author:cxp
 * @Date: 2022/8/9 17:36
 * @Description: paging3的适配器
*/

class HistoryPagingAdapter(var myViewModel: OperationViewModel) :
    PagingDataAdapter<OperationEntity, HistoryPagingAdapter.HistoryPagingHolder>(
        COMPARATOR
    ) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<OperationEntity>() {
            override fun areItemsTheSame(oldItem: OperationEntity, newItem: OperationEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: OperationEntity, newItem: OperationEntity): Boolean {
                return oldItem == newItem
            }
        }
    }


    inner class HistoryPagingHolder(var dataBinding: ItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryPagingHolder {
        var dataBinding: ItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item,
            parent,
            false
        )
        return HistoryPagingHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: HistoryPagingHolder, position: Int) {
//        获取历史记录的每行对象
        val entity: OperationEntity? = getItem(position)
//        xml文件的运算对象赋值
        holder.dataBinding.operation = entity

//        获取对象的时间戳
        val timeStamp = entity?.date
        val date = Date(timeStamp!!)
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        xml文件的时间参数赋值
        holder.dataBinding.operationDate = format.format(date)
//记忆功能，选择一条历史记录进行编辑，点击区域是ID
        val textViewId: TextView = holder.dataBinding.textViewId
//        val aimId: CharSequence = textViewId.text
        textViewId.setOnClickListener {
//            获取id的文本
            val aimId: CharSequence? = textViewId.text
            val str = aimId.toString()
//            给OperationViewModel的operationEntityListForId赋值，就可以激活ComputerFragment中的观察者去改变record的值
            myViewModel.setOperationEntityListForId(str.toInt())
//            跳转回计算器界面
            val controller = holder.itemView.findNavController()
            controller.navigate(R.id.action_historyFragment_to_computerFragment)
        }
    }
}