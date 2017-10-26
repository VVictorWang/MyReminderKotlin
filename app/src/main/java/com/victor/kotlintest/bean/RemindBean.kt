package com.victor.kotlintest.bean

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by victor on 10/26/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

@Entity(tableName = "reminds")
data class RemindBean(@PrimaryKey @ColumnInfo(name = "id") val id: String = UUID.randomUUID().toString(),
                      @ColumnInfo(name = "title") var title: String
                      , @ColumnInfo(name = "description") var desc: String,
                      @ColumnInfo(name = "create_time") val creat_time: Long)