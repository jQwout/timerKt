package com.example.timerkt.rounds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.timerkt.R
import com.example.timerkt.main.MainFabListener
import com.example.timerkt.main.MainMenuListener
import com.example.timerkt.main.MainMenuType
import kotlinx.android.synthetic.main.fragment_times_list.*

/**
 * @author ivangolovacev
 */
class RoundsFragment : Fragment() {

    private val timesAdapter = RoundsAdapter()

    private val fabListener: MainFabListener by lazy {
        activity as MainFabListener
    }

    private val menuListener: MainMenuListener by lazy {
        activity as MainMenuListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_times_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tlRecycler.adapter = timesAdapter

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                timesAdapter.remoteAt(viewHolder.adapterPosition)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                timesAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(tlRecycler)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fabListener.setListener {
            timesAdapter.addNewTime()
        }

        menuListener.changeType(MainMenuType.Edit)
    }

}