package com.damiankwasniak.ryanair.ui.decorator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.damiankwasniak.ryanair.R

class DividerRecyclerViewItemDecorator(context: Context) : RecyclerView.ItemDecoration() {

    private val divider: Drawable? = ContextCompat.getDrawable(context, R.drawable.recycler_view_divider)

    private val spacing = context.resources.getDimensionPixelSize(R.dimen.material_baseline_grid_0_5x)

    private val margin = context.resources.getDimensionPixelSize(R.dimen.material_baseline_grid_0_5x)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        setMargins(outRect, margin, margin, 4 * margin, 4 * margin)
    }

    private fun setMargins(outRect: Rect, top: Int, bottom: Int, left: Int, right: Int) {
        outRect.top = top
        outRect.bottom = bottom
        outRect.left = left
        outRect.right = right
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        val left = parent.paddingLeft + margin
        val right = parent.width - parent.paddingRight - margin

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin + spacing

            divider?.let {
                val bottom = top + divider.intrinsicHeight

                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
        }
    }

}