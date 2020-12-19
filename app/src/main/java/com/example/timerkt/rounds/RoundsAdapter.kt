package com.example.timerkt.rounds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timerkt.R
import com.example.timerkt.common.ItemTouchHelper
import com.example.timerkt.common.toMinutesAndSeconds
import com.example.timerkt.common.toTwoDigitShow
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.tl_picker_item.view.*
import java.util.*


/**
 * @author ivangolovacev
 */
class RoundsAdapter : RecyclerView.Adapter<RoundsAdapter.Label>(), ItemTouchHelper {

    private val timesList = mutableListOf<Long>()
    private var pickerPosition = -1

    fun addSet(list: List<Long>) {
        timesList.clear()
        timesList.addAll(list)
        notifyDataSetChanged()
    }

    fun addTime(time: Long) {
        timesList.add(time)
        notifyItemInserted(timesList.lastIndex)
    }

    fun addNewTime() {
        timesList.add(0L)
        pickerPosition = timesList.lastIndex
        notifyItemInserted(timesList.lastIndex)
    }

    fun changeType(position: Int) {
        pickerPosition = position
        notifyItemChanged(position)
    }

    fun remoteAt(position: Int) {
        timesList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return timesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundsAdapter.Label {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tl_picker_item, parent, false)
        return Label(view)
    }

    override fun onBindViewHolder(holder: RoundsAdapter.Label, position: Int) {
        holder.bind(timesList[position])
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(timesList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(timesList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        remoteAt(position)
    }

    inner class Label(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        var time = -1L

        val starter = containerView.rlStarterView

        fun bind(time: Long) {
            this.time = time

            val minAndSec = time.toMinutesAndSeconds()
            val min = minAndSec.first.toTwoDigitShow()
            val sec = minAndSec.second.toTwoDigitShow()

            starter.time = time
            //containerView.rlTime.text = "$min : $sec"
        }
    }
}