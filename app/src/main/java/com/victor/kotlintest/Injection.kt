package com.victor.kotlintest

import android.content.Context
import com.victor.kotlintest.bean.RemindDao
import com.victor.kotlintest.bean.RemindDatebase
import com.victor.kotlintest.ui.ViewModelFactory

/**
 * Created by victor on 10/26/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

object Injection {
    fun provideRemindDao(context: Context): RemindDao = RemindDatebase.getInstance(context).remindDao()

    fun proviewViewModelFactory(context: Context): ViewModelFactory = ViewModelFactory(provideRemindDao(context))
}