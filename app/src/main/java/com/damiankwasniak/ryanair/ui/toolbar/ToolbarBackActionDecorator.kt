package com.damiankwasniak.ryanair.ui.toolbar

import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import com.damiankwasniak.ryanair.R
import com.nomtek.utils.toolbar.decorators.ToolbarDecorator
import com.nomtek.utils.visible

class ToolbarBackActionDecorator(private val action: ((View) -> Unit)) : ToolbarDecorator {

    override fun build(toolbar: Toolbar) {
        val cancelIconView = toolbar.findViewById<ImageView>(R.id.leftIcon)
        cancelIconView.visible()
        cancelIconView.setOnClickListener(action)
        cancelIconView.setImageResource(R.drawable.ic_arrow_left)
    }

}