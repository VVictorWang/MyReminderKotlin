package com.victor.kotlintest.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.victor.kotlintest.Injection
import com.victor.kotlintest.R
import com.victor.kotlintest.bean.RemindBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_remind.*
import java.util.*

class AddRemindActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: RemindViewModel

    private val disposable = CompositeDisposable()

    private val repeatTime = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_remind)
        initEvent()
        val id = intent?.getStringExtra("id")
        viewModelFactory = Injection.proviewViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RemindViewModel::class.java)
        initData(id)
        confirm.setOnClickListener({ addData(id) })
        showTimePick()
    }

    private fun initEvent() {
        val repeats = mutableListOf(sunday, monday, tuesday, wendesday, thursday, friday, saturday)
        for (item in repeats) {
            item.tag = "unchoosen"
            item.setOnClickListener {
                if (item.tag.equals("unchoosen")) {
                    item.background = resources.getDrawable(R.drawable.circle_bule)
                    item.tag = "choosen"
                    repeatTime.add(repeats.indexOf(item))
                } else {
                    item.background = resources.getDrawable(R.drawable.cirle_white)
                    item.tag = "unchoosen"
                    repeatTime.remove(repeats.indexOf(item))
                }
            }
        }


    }

    private fun initData(id: String?) {
        id?.let {
            viewModel.getRemindById(id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        alertContent.setText(it.desc)
                    })
        }
    }

    private fun addData(id: String?) {
        var result: Int = 0
        for (item in repeatTime) {
            result += item * 10
        }
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, pickerHour.getSelectData().toInt())
        calendar.set(Calendar.MINUTE, pickerMinute.getSelectData().toInt())
        val remind = RemindBean(desc = alertContent.text.toString(),
                creat_time = System.currentTimeMillis(), time = calendar.time.time, repeat = result)
        id?.let { remind.id = id }
        disposable.add(viewModel.insertRemind(remind).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    finish()
                }))
    }

    private fun showTimePick() {
        val hourData = mutableListOf<String>()
        for (i in 0..23)
            hourData.add(String.format("%02d", i))
        val minuteData = mutableListOf<String>()
        for (i in 0..59)
            minuteData.add(String.format("%02d", i))

        pickerHour.setData(hourData)
        pickerMinute.setData(minuteData)

    }
}
