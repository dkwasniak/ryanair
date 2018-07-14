package com.damiankwasniak.ryanair.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.damiankwasniak.emptyString
import com.damiankwasniak.ryanair.R
import com.nomtek.utils.inflate
import kotlinx.android.synthetic.main.price_view_layout.view.*

class PriceViewLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(R.layout.price_view_layout)
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.PriceViewLayout, 0, 0)
        priceNameTextView.text = attributes.getString(R.styleable.PriceViewLayout_label)
        attributes.recycle()
    }

    fun setPrice(priceValue: Double = 0.0, currency: String = emptyString, noDataProvided: Boolean = false) {
        if (noDataProvided) {
            priceValueTextView.setText(R.string.no_data)
        } else {
            priceValueTextView.text = String.format(context.getString(R.string.price), priceValue, currency)
        }

    }
}