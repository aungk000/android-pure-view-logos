package me.ako.logos.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import me.ako.logos.R

class NetflixLogo(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val linePaint = getPaint(R.color.netflix_red_dark)
    private val midLinePaint = getPaint(R.color.netflix_red)

    private val path = Path()

    private fun getPaint(@ColorRes colorRes: Int): Paint {
        return Paint().apply {
            style = Paint.Style.FILL
            color = ContextCompat.getColor(context, colorRes)
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas) {
        // calculate paddings to view size
        var startX = paddingStart.toFloat()
        var startY = paddingTop.toFloat()
        var stopX = (width - paddingEnd).toFloat()
        var stopY = (height - paddingBottom).toFloat()

        /**
        Discard paddings if they are greater than view size.
        In such case, shadow will also be discarded because it is based on padding to avoid clipping.
        **/
        if(width <= paddingStart + paddingEnd || height <= paddingTop + paddingBottom) {
            startX = 0f
            startY = 0f
            stopX = width.toFloat()
            stopY = height.toFloat()
        }

        // width of logo line
        val lineWidth = (stopX - startX) / 3f

        // left line
        canvas.drawRect(startX, startY, lineWidth + startX, stopY, linePaint)

        // right line
        canvas.drawRect((lineWidth * 2) + startX, startY, stopX, stopY, linePaint)

        // locate middle line paths
        path.moveTo(startX, startY)
        path.lineTo(lineWidth + startX, startY)
        path.lineTo(stopX, stopY)
        path.lineTo((lineWidth * 2) + startX, stopY)

        // set shadow to middle line
        if(paddingStart == paddingTop || paddingStart == paddingEnd || paddingStart == paddingBottom) {
            midLinePaint.setShadowLayer(paddingStart / 2f, 0f, 0f, Color.BLACK)
        }

        canvas.drawPath(path, midLinePaint)
    }
}