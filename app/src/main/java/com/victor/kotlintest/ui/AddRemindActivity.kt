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

class AddRemindActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: RemindViewModel

    private val disposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_remind)
        val id = intent?.getStringExtra("id")
        viewModelFactory = Injection.proviewViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RemindViewModel::class.java)
        initData(id)
        confirm.setOnClickListener({ addData() })
    }

    private fun initData(id: String?) {
        if (id != null) {
            viewModel.getRemindById(id = id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        alertTitle.setText(it.title)
                        alertContent.setText(it.desc)
                    })
        }
    }

    private fun addData() {
        val remind = RemindBean(title = alertTitle.text.toString(), desc = alertContent.text.toString(),
                creat_time = System.currentTimeMillis())
        disposable.add(viewModel.insertRemind(remind).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    finish()
                }))
    }
}
