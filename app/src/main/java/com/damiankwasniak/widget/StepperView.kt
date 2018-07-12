package com.damiankwasniak.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.damiankwasniak.ryanair.R
import com.nomtek.utils.inflate
import kotlinx.android.synthetic.main.stepper_view_layout.view.*

class StepperView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    var onValueChanged = fun(value: Int) {}

    var value = 0
        set(value) {
            field = value
            onValueChanged()
            stepperValueTextView.text = value.toString()
            onValueChanged(value)
        }

    init {
        inflate(R.layout.stepper_view_layout)
        stepperValueTextView.text = value.toString()
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.StepperView, 0, 0)
        labelTextView.text = attributes.getString(R.styleable.StepperView_label)
        attributes.recycle()
        initClickListeners()
    }

    private fun initClickListeners() {
        stepperMinusImageView.setOnClickListener {
            value--
        }
        stepperPlusImageView.setOnClickListener {
            value++
        }
    }


    private fun onValueChanged() {
        if (value == 0) {
            stepperMinusImageView.isEnabled = false
            stepperMinusImageView.setImageResource(R.drawable.stepper_notactive_minus)
        } else {
            stepperMinusImageView.isEnabled = true
            stepperMinusImageView.setImageResource(R.drawable.stepper_active_minus)
        }

        if (value == 10) {
            stepperPlusImageView.isEnabled = false
            stepperPlusImageView.setImageResource(R.drawable.stepper_notactive_plus)
        } else {
            stepperPlusImageView.isEnabled = true
            stepperPlusImageView.setImageResource(R.drawable.stepper_active_plus)
        }
    }
}