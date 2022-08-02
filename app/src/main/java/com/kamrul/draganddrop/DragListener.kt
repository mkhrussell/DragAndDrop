package com.kamrul.draganddrop

import android.util.Log
import android.view.DragEvent
import android.view.View

class DragListener(private val itemMove: (from: Int, to: Int) -> Unit) : View.OnDragListener {

    private var isDropped = false

    override fun onDrag(view: View, event: DragEvent): Boolean {
        when(event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {}
            DragEvent.ACTION_DRAG_ENTERED -> {}
            DragEvent.ACTION_DRAG_EXITED -> {}
            DragEvent.ACTION_DROP -> {
                isDropped = true
                var positionSource = -1
                var positionTarget = -1

                val viewSource = event.localState as View
                positionSource = viewSource.tag as Int

                if(view.id == R.id.itemRoot) {
                    positionTarget = view.tag as Int
                }

                Log.d("DragListener", "positionSource = $positionSource, positionTarget = $positionTarget")
                itemMove(positionSource, positionTarget)

                view.visibility = View.VISIBLE
            }
            DragEvent.ACTION_DRAG_ENDED -> {}
        }

        if(!isDropped) {
            val droppedView = event.localState as View
            droppedView.visibility = View.VISIBLE
        }

        return true
    }
}