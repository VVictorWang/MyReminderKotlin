package com.victor.kotlintest.ui

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.victor.kotlintest.Injection
import com.victor.kotlintest.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LifecycleActivity() {


    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: RemindViewModel

    private val disposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelFactory = Injection.proviewViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RemindViewModel::class.java)

        reminderList?.layoutManager = LinearLayoutManager(this)
        reminderList?.adapter = RemindAdapter(emptyList(), this)
        initData()

        add_alert.setOnClickListener({
            val intent = Intent(this, AddRemindActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private fun initData() {

        viewModel.getAllReminds().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isEmpty()) {
                        empty.visibility = View.VISIBLE
                    } else {
                        reminderList.adapter = RemindAdapter(it, this)
                    }
                })
    }


}
