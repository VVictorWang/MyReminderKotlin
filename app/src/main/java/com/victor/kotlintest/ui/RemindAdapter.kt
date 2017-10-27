package com.victor.kotlintest.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.victor.kotlintest.R
import com.victor.kotlintest.bean.RemindBean
import kotlinx.android.synthetic.main.list_items.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by victor on 10/26/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

class RemindAdapter(var reminds: List<RemindBean>, val context: Context) : RecyclerView.Adapter<RemindAdapter.RemindViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RemindViewHolder {
        return RemindViewHolder(parent)
    }

    override fun getItemCount(): Int = reminds.size

    override fun onBindViewHolder(holder: RemindViewHolder?, position: Int) {
        holder?.bindData(reminds[position])
        holder?.setOnclickListenner(reminds[position])

    }

    inner class RemindViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(LayoutInflater.from(parent?.context).
            inflate(R.layout.list_items, parent, false)) {
        fun bindData(remind: RemindBean) = with(itemView) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = remind.time
            timecreated.text = formate.format(calendar.time)
            reminder.text = remind.desc
            repeate.text = handerRepeat(remind.repeat)
        }

        private fun handerRepeat(repeat: Int): String {
            val stringBuilder = StringBuilder()
            val repeatString = repeat.toString()
            if (repeatString.contains("0")) {
                stringBuilder.append("日 ")
            }
            if (repeatString.contains("1")) {
                stringBuilder.append("一 ")
            }
            if (repeatString.contains("2")) {
                stringBuilder.append("二 ")
            }
            if (repeatString.contains("3")) {
                stringBuilder.append("三 ")
            }
            if (repeatString.contains("4")) {
                stringBuilder.append("四 ")
            }
            if (repeatString.contains("5")) {
                stringBuilder.append("五 ")
            }
            if (repeatString.contains("6")) {
                stringBuilder.append("六 ")
            }
            return stringBuilder.toString()
        }


        fun setOnclickListenner(remind: RemindBean) = with(itemView) {
            card_view.setOnClickListener {
                val intent = Intent(context, AddRemindActivity::class.java)
                intent.putExtra("id", remind.id)
                context.startActivity(intent)
            }
        }

    }

    companion object {
        @SuppressLint("SimpleDateFormat")
        val formate = SimpleDateFormat("HH:mm")
    }
}