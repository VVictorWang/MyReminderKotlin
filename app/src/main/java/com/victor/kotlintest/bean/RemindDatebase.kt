package com.victor.kotlintest.bean

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by victor on 10/26/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

@Database(entities = arrayOf(RemindBean::class), version = 1)
abstract class RemindDatebase : RoomDatabase() {

    abstract fun remindDao(): RemindDao

    companion object {
        @Volatile private var INSTANCE: RemindDatebase? = null

        fun getInstance(context: Context): RemindDatebase =
                INSTANCE ?: synchronized(this) { INSTANCE ?: buildDatabase(context).also { INSTANCE = it } }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
                RemindDatebase::class.java, "MyReminds.db").build()

    }

}