package com.example.timerkt.common

import androidx.recyclerview.widget.RecyclerView

/**
 * @author ivangolovacev
 */
interface OnDragListener {

    fun onDrag(viewHolder: RecyclerView.ViewHolder)
}

interface ItemTouchHelper {
    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(position: Int)
}