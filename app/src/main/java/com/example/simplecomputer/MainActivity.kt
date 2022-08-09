package com.example.simplecomputer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.simplecomputer.navigation.ComputerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @Author:cxp
 * @Date: 2022/8/4 14:15
 * @Description:计算器
*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        fragment的动画跳转失败，可能是因为跳转已经用了导航
//        var fragment = ComputerFragment()
//        supportFragmentManager.commit {
//            setCustomAnimations(
//                R.anim.slide_in,
//                R.anim.fade_out,
//                R.anim.fade_in,
//                R.anim.slide_out
//            )
//            replace(R.id.fragmentContainerView, fragment)
//            addToBackStack(null)
//        }

        lifecycleScope.launch (Dispatchers.Main){
            lifecycleScope.launch (Dispatchers.Main){
                Log.e("test==","111111");
            }
            Log.e("test==","222222");
        }

    }
}