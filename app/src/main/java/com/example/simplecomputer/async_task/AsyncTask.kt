package com.example.simplecomputer.async_task

import android.os.AsyncTask
import android.os.Looper
import android.view.View
import com.example.simplecomputer.databinding.FragmentHistoryBinding

/**
 * @Author:cxp
 * @Date: 2022/8/5 15:40
 * @Description:历史记录出来前的进度条
*/

class AsyncTask(var historyBinding: FragmentHistoryBinding) : AsyncTask<Int, Int, String>() {
//    执行中
    override fun doInBackground(vararg params: Int?): String {
        for (i in 0..params[0]!!) {
            publishProgress(i * 10)
            Thread.sleep(1000)
        }
        return "onPostExecute"
    }
//执行之前
    override fun onPreExecute() {
        super.onPreExecute()
        historyBinding.recyclerView.visibility = View.INVISIBLE
        historyBinding.progressBarText.text = "onPreExecute"
    }
//执行完成后
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        historyBinding.progressBarText.text = "onPostExecute"
        historyBinding.recyclerView.visibility = View.VISIBLE
        historyBinding.progressBar.visibility = View.GONE
        historyBinding.progressBarText.visibility = View.GONE
    }
    //      划分成每个阶段
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        historyBinding.progressBar.progress = values[0]!!
        historyBinding.progressBarText.text = "onProgressUpdate"

    }
}