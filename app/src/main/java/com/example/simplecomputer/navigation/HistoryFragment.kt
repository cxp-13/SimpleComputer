package com.example.simplecomputer.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import com.example.simplecomputer.R
import com.example.simplecomputer.adapter.HistoryAdapter
import com.example.simplecomputer.databinding.FragmentComputerBinding
import com.example.simplecomputer.databinding.FragmentHistoryBinding
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.repository.OperationRepository
import com.example.simplecomputer.viewmodel.OperationViewModel


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    private val operationRepository: OperationRepository by lazy {
        OperationRepository(this.context!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val historyAdapter =
            HistoryAdapter(operationRepository.getAllLiveData()?.value ?: ArrayList())
        binding.recyclerView.adapter = historyAdapter

//        val myViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
//            .get(OperationViewModel::class.java)
        val myViewModel:OperationViewModel by viewModels()

        myViewModel.repository = operationRepository

        myViewModel.getUsers()?.observe(this.viewLifecycleOwner){
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