package com.victor.kotlintest.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.victor.kotlintest.R
import com.victor.kotlintest.bean.RemindBean
import kotlinx.android.synthetic.main.list_items.view.*

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
            title.text = remind.title
            timecreated.text = remind.creat_time.toString()
            reminder.text = remind.desc

        }

        fun setOnclickListenner(remind: RemindBean) = with(itemView) {
            card_view.setOnClickListener {
                val intent = Intent(context, AddRemindActivity::class.java)
                intent.putExtra("id", remind.id)
                context.startActivity(intent)
            }
        }

    }
}