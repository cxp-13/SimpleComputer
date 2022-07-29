package com.example.simplecomputer.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.simplecomputer.R
import com.example.simplecomputer.dao.OperationDao
import com.example.simplecomputer.databinding.FragmentComputerBinding
import com.example.simplecomputer.databinding.Record
import com.example.simplecomputer.db.OperationDataBase
import com.example.simplecomputer.entity.OperationEntity
import com.example.simplecomputer.repository.OperationRepository
import com.example.simplecomputer.viewmodel.OperationViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

private const val TAG = "record"

class ComputerFragment : Fragment() {
    /*
    1.将每次的运算记录成字符串
    2.在进行解析提取出运算符和数据
    3.放入数据库
    */
//    private var test:Int = 0
//        set(value) {
//            Log.d("test", ": ${value}")
//
//        }


    private lateinit var record: Record
//    private lateinit var recordStr: ObservableField<StringBuffer>

    //    private lateinit var operationDao: OperationDao
    private lateinit var operationRepository: OperationRepository

//    private lateinit var dataBinding: FragmentComputerBinding

    private lateinit var binding: FragmentComputerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//        recordStr = ObservableField<StringBuffer>()
        record = Record(StringBuffer("0"))
        operationRepository = OperationRepository(context!!)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        binding = FragmentComputerBinding.inflate(inflater, container, false)

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_computer, container, false)
        binding.record = record

//        return inflater.inflate(R.layout.fragment_computer, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoHistory.setOnClickListener {
            val controller = view.findNavController()
            controller.navigate(R.id.action_computerFragment_to_historyFragment)
        }
// 运算处理
        binding.add.setOnClickListener {
            record.value.apply {
                this.set(StringBuffer(this.get()?.append("+")))
            }
        }
        binding.reduce.setOnClickListener {
            record.value.apply {
                this.set(StringBuffer(this.get()?.append("-")))
            }
        }
        binding.multiply.setOnClickListener {
            record.value.apply {
                this.set(StringBuffer(this.get()?.append("*")))
            }
        }
        binding.divide.setOnClickListener {
            record.value.apply {
                this.set(StringBuffer(this.get()?.append("/")))
            }
        }
//        数字处理
        binding.number0.setOnClickListener {
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append("0")))
            }
        }
        binding.number1.setOnClickListener {
//tips:可观察对象必须创一个新对象重新赋值才能在界面上更新，不能在原来的对象上更改再赋值界面无响应
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append("1")))
//                                record.value = StringBuffer(it.get()?.append("1"))
            }
//            test = 2
//            Log.d("test", "onViewCreated: ${test}")
        }
        binding.number2.setOnClickListener {
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append("2")))
            }

        }
        binding.number3.setOnClickListener {
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append("3")))
            }

        }
        binding.number4.setOnClickListener {
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append("4")))
            }

        }
        binding.number5.setOnClickListener {
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append("5")))
            }

        }
        binding.number6.setOnClickListener {
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append("6")))
            }

        }
        binding.number7.setOnClickListener {
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append("7")))
            }

        }
        binding.number8.setOnClickListener {
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append("8")))
            }

        }
        binding.number9.setOnClickListener {
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append("9")))
            }
        }
        binding.clear.setOnClickListener {
            record.value.set(StringBuffer())
        }
        binding.back.setOnClickListener {
            record.value.also {
                var len = it.get()?.length
                if (len != null && len > 0) {
                    record.value.set(StringBuffer(it.get()?.substring(0, len - 1)))
                }
            }
        }
        binding.point.setOnClickListener {
            record.value.let {
                record.value.set(StringBuffer(it.get()?.append(".")))
            }
        }
//        查询全部数据
        binding.button14.setOnClickListener {
                operationRepository.getAllLiveData()?.value?.forEach {
                    Log.e(TAG, "onViewCreated: $it",)
                }
        }
        binding.equalSign.setOnClickListener {
            var pattern: Pattern =
                Pattern.compile("^(?<p1>(\\-|\\+)?\\d+(\\.\\d+)?)(?<t>\\D)(?<p2>(\\-|\\+)?\\d+(\\.\\d+)?)\$")
            var matcher: Matcher = pattern.matcher(record.value.get())
            var type = '+'
            var params1 = 0.0
            var params2 = 0.0
            var result = 0.0
            var operationEntity: OperationEntity

            if (matcher.find()) {
                params1 = matcher.group("p1").toDouble()
                params2 = matcher.group("p2").toDouble()
                var charArray: CharArray = matcher.group("t").toCharArray()
                type = charArray[0]
//                Log.d(TAG, "onViewCreated: $params1 $params2 $type")
                when (type) {
                    '+' -> {
                        result = params1 + params2
                    }
                    '-' -> {
                        result = params1 - params2
                    }
                    '*' -> {
                        result = params1 * params2
                    }
                    '/' -> {
                        result = params1 / params2
                    }
                    else -> {
                        Toast.makeText(this.context, "运算符错误 正确格式：数字+[+/×/÷/-]+数字", 2).show()
                    }
                }

                record.value.let {
                    record.value.set(StringBuffer(result.toString()))
                }

                operationEntity = OperationEntity(
                    firstArg = params1,
                    secondArg = params2,
                    result = result,
                    type = type,
                    date = System.currentTimeMillis()
                )
                operationRepository.insert(operationEntity)
            } else {
                Toast.makeText(this.context, "格式错误 正确格式：数字+运算符+数字", 2).show()
            }
        }

    }


}