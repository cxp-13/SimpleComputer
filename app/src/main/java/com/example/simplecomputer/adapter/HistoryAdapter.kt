package com.example.simplecomputer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecomputer.R
import com.example.simplecomputer.databinding.ItemBinding
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.viewmodel.OperationViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author:cxp
 * @Date: 2022/8/4 9:29
 * @Description:历史记录RecyclerView的适配器
 */
@Deprecated("采用paging3之前的做法: 直接实现RecyclerView.Adapter")
class HistoryAdapter(var datas: List<OperationEntity>, var myViewModel: OperationViewModel) :
    RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {
    //貌似没什么用
    fun setData(datas: List<OperationEntity>) {
        this.datas = datas
    }

    //    ViewHolder 是包含列表中各列表项的布局的 View 的封装容器
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
//        xml文件的运算对象赋值
        holder.dataBinding.operation = datas[position]
//        获取历史记录的每行对象
        val entity = datas[position]
//        获取对象的时间戳
        val timeStamp = entity.date
        val date = Date(timeStamp)
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

    override fun getItemCount(): Int {
        return datas.size
    }
}