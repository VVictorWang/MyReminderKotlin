package com.victor.kotlintest.bean

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by victor on 10/26/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

@Dao
interface RemindDao {

    @Query("select * from reminds where id = :id")
    fun getRemindById(id: String): Flowable<RemindBean>

    @Query("select * from reminds")
    fun getAllReminds(): Flowable<List<RemindBean>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRemind(remind: RemindBean)

}