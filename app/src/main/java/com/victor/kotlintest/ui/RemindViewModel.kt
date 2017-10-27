package com.victor.kotlintest.ui

import android.arch.lifecycle.ViewModel
import com.victor.kotlintest.bean.RemindBean
import com.victor.kotlintest.bean.RemindDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.Action
import io.reactivex.internal.operators.completable.CompletableFromAction

/**
 * Created by victor on 10/26/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

class RemindViewModel(private val dataSource: RemindDao) : ViewModel() {


    fun getRemindById(id: String): Flowable<RemindBean> = dataSource.getRemindById(id)

    fun getAllReminds(): Flowable<List<RemindBean>> = dataSource.getAllReminds()


    fun insertRemind(remind: RemindBean): Completable {
        return CompletableFromAction(Action { dataSource.insertRemind(remind) })
    }

    fun deleteAllReminds(): Completable = CompletableFromAction(Action { dataSource.deleteAllRemind() })

}