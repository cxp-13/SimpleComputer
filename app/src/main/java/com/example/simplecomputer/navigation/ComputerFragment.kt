package com.example.simplecomputer.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
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

/**
 * @Auther:cxp
 * @Date: 2022/8/4 9:42
 * @Description:计算器片段
 */

private const val TAG = "record"

class ComputerFragment : Fragment() {
    /*
    1.将每次的运算记录成字符串
    2.进行正则解析提取出运算符和两个参数
    3.调用数据库api放入数据库
    */
//    private var test:Int = 0
//        set(value) {
//            Log.d("test", ": ${value}")
//
//        }
    //    请注意，这两个 Fragment 都会检索包含它们的 Activity。这样，当这两个 Fragment 各自获取 ViewModelProvider 时，它们会收到相同的 SharedViewModel 实例（其范围限定为该 Activity）
    private val myViewModel: OperationViewModel by activityViewModels()

    //记录计算的过程
    private lateinit var record: Record
//    private lateinit var recordStr: ObservableField<StringBuffer>
    //    private lateinit var operationDao: OperationDao

    //    操作数据库的接口
    private lateinit var operationRepository: OperationRepository

    //    private lateinit var dataBinding: FragmentComputerBinding
//    计算片段的绑定类
    private lateinit var binding: FragmentComputerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//        recordStr = ObservableField<StringBuffer>()
//       初始化记录计算过程的类
        record = Record(StringBuffer("0"))
//        初始化数据库类
        operationRepository = OperationRepository(context!!)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 膨胀这个片段的布局
//        binding = FragmentComputerBinding.inflate(inflater, container, false)
//        给ViewModel中的数据库对象赋值
        myViewModel.repository = operationRepository
//        记忆功能，监听历史记录是否有点击
        myViewModel.operationEntityListForId?.observe(viewLifecycleOwner) {
            Log.e("record", "computer观察者:${it} ")
            //            val entity = it[0]
            //            val str: String = entity.firstArg.toString() + entity.type + entity.secondArg.toString()
            //            record.value.set(StringBuffer(str))
//        要操作的记录在列表的第一条
            val oe = it?.get(0)
//            合成需要展示的记录
            val newRecord: String = oe?.firstArg.toString() + oe?.type + oe?.secondArg.toString()
//            把需要操作的记录赋值给record
            record.str.value = StringBuffer(newRecord)
        }
//        初始化数据绑定
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_computer, container, false)
        binding.lifecycleOwner = this
//        在计算器界面上绑定记录,由于record是ObservableField可以实时更新在界面上
        binding.record = record
//        return inflater.inflate(R.layout.fragment_computer, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//      跳转至历史记录界面
        binding.btnGoHistory.setOnClickListener {
            val controller = view.findNavController()
            controller.navigate(R.id.action_computerFragment_to_historyFragment)
        }
//        运算处理
//        加法
        binding.add.setOnClickListener {
            record.str.apply {
                this.value = this.value?.append("+")
            }
        }
//        减法
        binding.reduce.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("-"))
            }
        }
//        乘法
        binding.multiply.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("*"))
            }
        }
//        除法
        binding.divide.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("/"))
            }
        }
//        数字处理
        binding.number0.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("0"))
            }
        }
        binding.number1.setOnClickListener {
//tips:可观察对象必须创一个新对象重新赋值才能在界面上更新，不能在原来的对象上更改再赋值界面无响应
            record.str.apply {
                this.value = StringBuffer(this.value?.append("1"))
            }
        }
        binding.number2.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("2"))
            }

        }
        binding.number3.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("3"))
            }

        }
        binding.number4.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("4"))
            }

        }
        binding.number5.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("5"))
            }

        }
        binding.number6.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("6"))
            }

        }
        binding.number7.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("7"))
            }

        }
        binding.number8.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("8"))
            }

        }
        binding.number9.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append("9"))
            }
        }
//        清空编辑框
        binding.clear.setOnClickListener {
            record.str.apply {
                this.value = StringBuffer(this.value?.append(""))
            }
        }
//        退格
        binding.back.setOnClickListener {
            record.str.also {
                var len = it.value?.length
                if (len != null && len > 0) {
                    it.value = StringBuffer(it.value?.substring(0, len - 1))
                }
            }


        }
//        小数点
        binding.point.setOnClickListener {
            record.str.let {
                it.value = StringBuffer(it.value?.append("."))
            }
        }
//        查询全部数据
        binding.button14.setOnClickListener {
            operationRepository.getAllLiveData()?.value?.forEach {
                Log.e(TAG, "onViewCreated: $it")
            }
        }
//        当点击等于号
        binding.equalSign.setOnClickListener {
//      p1:第一个数字  t:运算符  p2:第二个数字
            var pattern: Pattern =
                Pattern.compile("^(?<p1>(\\-|\\+)?\\d+(\\.\\d+)?)(?<t>\\D)(?<p2>(\\-|\\+)?\\d+(\\.\\d+)?)\$")
            var matcher: Matcher = pattern.matcher(record.str.value)
            var type = '+'
            var params1 = 0.0
            var params2 = 0.0
            var result = 0.0
            var operationEntity: OperationEntity
//      如果匹配上
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
//              将结果赋值给record
                record.str.let {
                    it.value = (StringBuffer(result.toString()))
                }
//              生成需要插入数据库的记录
                operationEntity = OperationEntity(
                    firstArg = params1,
                    secondArg = params2,
                    result = result,
                    type = type,
                    date = System.currentTimeMillis()
                )
//               插入数据库
                operationRepository.insert(operationEntity)
            } else {
                Toast.makeText(this.context, "格式错误 正确格式：数字+运算符+数字", Toast.LENGTH_SHORT).show()
            }
        }
    }
}