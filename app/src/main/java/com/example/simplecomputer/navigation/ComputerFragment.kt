package com.example.simplecomputer.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.simplecomputer.R
import com.example.simplecomputer.dao.OperationDao
import com.example.simplecomputer.databinding.FragmentComputerBinding
import com.example.simplecomputer.db.OperationDataBase

class ComputerFragment : Fragment() {

    private lateinit var record:StringBuffer
    private val operationDao:OperationDao by lazy {
        OperationDataBase.getInstance(this.context!!).getOperationDao()
    }


    private lateinit var binding: FragmentComputerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentComputerBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.fragment_computer, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = binding.btnGoHistory
//        val button:Button = view.findViewById<Button>(R.id.btn_go_history)

        button.setOnClickListener {
            val controller = view.findNavController()
            controller.navigate(R.id.action_computerFragment_to_historyFragment)
        }
// 运算处理
        binding.add.setOnClickListener {
            record.append("+")
        }
        binding.reduce.setOnClickListener {
            record.append("-")
        }
    }


}