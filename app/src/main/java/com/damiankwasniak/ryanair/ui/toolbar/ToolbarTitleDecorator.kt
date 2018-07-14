package com.damiankwasniak.ryanair.ui.toolbar

import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.damiankwasniak.emptyString
import com.damiankwasniak.ryanair.R
import com.nomtek.utils.toolbar.decorators.ToolbarDecorator


class ToolbarTitleDecorator(private val titleString: String = emptyString) :
        ToolbarDecorator {

    override fun build(toolbar: Toolbar) {
        val toolbarTitleView: TextView = toolbar.findViewById(R.id.titleTextView)
        toolbarTitleView.text = titleString
    }
}