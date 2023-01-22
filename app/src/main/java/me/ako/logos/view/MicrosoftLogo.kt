package me.ako.logos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import me.ako.logos.R

class MicrosoftLogo(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val redPaint = getPaint(R.color.ms_red)
    private val greenPaint = getPaint(R.color.ms_green)
    private val bluePaint = getPaint(R.color.ms_blue)
    private val yellowPaint = getPaint(R.color.ms_yellow)

    private fun getPaint(@ColorRes colorRes: Int): Paint {
        return Paint().apply {
            style = Paint.Style.FILL
            color = ContextCompat.getColor(context, colorRes)
            isAntiAlias = true
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val square = Math.min(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(square, square)
    }

    override fun onDraw(canvas: Canvas) {
        // calculate paddings to view size
        var startX = paddingStart.toFloat()
        var startY = paddingTop.toFloat()
        var stopX = (width - paddingEnd).toFloat()
        var stopY = (height - paddingBottom).toFloat()

        // discard paddings if they are greater than view size
        if (width <= paddingStart + paddingEnd || height <= paddingTop + paddingBottom) {
            startX = 0f
            startY = 0f
            stopX = width.toFloat()
            stopY = height.toFloat()
        }

        val rectSquare = (stopX - startX) / 2f
        val gap = rectSquare / 10f

        canvas.drawRect(
            startX,
            startY,
            startX + rectSquare - (gap / 2),
            startY + rectSquare - (gap / 2),
            redPaint
        )

        canvas.drawRect(
            startX + rectSquare + (gap / 2),
            startY,
            stopX,
            startY + rectSquare - (gap / 2),
            greenPaint
        )

        canvas.drawRect(
            startX,
            startY + rectSquare + (gap / 2),
            startX + rectSquare - (gap / 2),
            stopY,
            bluePaint
        )

        canvas.drawRect(
            startX + rectSquare + (gap / 2),
            startY + rectSquare + (gap / 2),
            stopX,
            stopY,
            yellowPaint
        )
    }
}