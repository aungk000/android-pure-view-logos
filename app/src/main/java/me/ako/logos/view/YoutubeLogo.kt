package me.ako.logos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class YoutubeLogo(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val rectPaint = getPaint(Color.RED)
    private val btnPaint = getPaint(Color.WHITE)

    private val path = Path()

    private var cornerRadius = 0f

    private fun getPaint(colorInt: Int): Paint {
        return Paint().apply {
            style = Paint.Style.FILL
            color = colorInt
            isAntiAlias = true
        }
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

        val btnWidth = (stopX - startX) / 5f
        val btnHeight = (stopY - startY) / 4f

        canvas.drawRoundRect(startX, startY, stopX, stopY, cornerRadius, cornerRadius, rectPaint)

        path.moveTo(startX + btnWidth * 2f, startY + btnHeight)
        path.lineTo(startX + btnWidth * 2f, startY + btnHeight * 3f)
        path.lineTo(startX + btnWidth * 3f, startY + btnHeight * 2f)

        canvas.drawPath(path, btnPaint)
    }

    fun setCornerRadius(radius: Int) {
        cornerRadius = radius.toFloat()
        invalidate()
    }
}