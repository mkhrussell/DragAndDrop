package com.kamrul.draganddrop.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class TouchDragLayer: FrameLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int): super(context, attrs, defStyle)

    // Need implementation
}