package com.example.simplecomputer.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.simplecomputer.R
import com.example.simplecomputer.adapter.HistoryAdapter
import com.example.simplecomputer.async_task.AsyncTask
import com.example.simplecomputer.databinding.FragmentHistoryBinding
import com.example.simplecomputer.repository.OperationRepository
import com.example.simplecomputer.viewmodel.OperationViewModel

/**
 * @Author:cxp
 * @Date: 2022/8/4 11:56
 * @Description:历史记录
 */

class HistoryFragment : Fragment() {
    //数据绑定
    private lateinit var binding: FragmentHistoryBinding

    private lateinit var operationRepository: OperationRepository

    private lateinit var asyncTask: AsyncTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        asyncTask = AsyncTask(binding)

        asyncTask.execute(10)

        operationRepository = OperationRepository(context!!)
//        val myViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
//            .get(OperationViewModel::class.java)
        val myViewModel: OperationViewModel by activityViewModels()
        //初始viewModel里面的control层对象
        myViewModel.repository = operationRepository
//初始化recycleView适配器
        val historyAdapter =
            HistoryAdapter(operationRepository.getAllLiveData()?.value ?: ArrayList(), myViewModel)
        binding.recyclerView.adapter = historyAdapter
//当数据库数据改动时，更新历史记录的数据
        myViewModel.getOperations()?.observe(viewLifecycleOwner) {
            historyAdapter.datas = it
            historyAdapter.notifyDataSetChanged()
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = binding.btnBackComputer
        button.setOnClickListener {
            val controller = view.findNavController()
            controller.navigate(R.id.action_historyFragment_to_computerFragment)
        }
    }
}