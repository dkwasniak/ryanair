package com.damiankwasniak.ryanair.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.damiankwasniak.emptyString
import com.damiankwasniak.ryanair.R
import com.nomtek.utils.inflate
import kotlinx.android.synthetic.main.price_view_layout.view.*

class DetailedLabelViewLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(R.layout.price_view_layout)
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.DetailedLabelViewLayout, 0, 0)
        priceNameTextView.text = attributes.getString(R.styleable.DetailedLabelViewLayout_label)
        attributes.recycle()
    }

    fun setDetailText(text: String) {
        detailTextView.text = text
    }
}