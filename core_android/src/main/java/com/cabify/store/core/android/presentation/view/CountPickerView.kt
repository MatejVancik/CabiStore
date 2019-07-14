package com.cabify.store.core.android.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cabify.store.core.android.R
import kotlinx.android.synthetic.main.view_count_picker.view.*

class CountPickerView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    var countChangeListener: ((Int) -> Unit)? = null

    var count: Int = 1
        set(value) {
            decreaseButton.isEnabled = value > 0
            if (value <= 0) return
            field = value
            countChangeListener?.invoke(value)
            countText.text = value.toString()
        }

    init {
        View.inflate(context, R.layout.view_count_picker, this)

        count = 1
        increaseButton.setOnClickListener { count++ }
        decreaseButton.setOnClickListener { count-- }
    }

}