package pl.edu.amu.wmi.erykandroidcommon.ui.progress

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.view.animation.Interpolator

/**
 * Procedurally-drawn version of a horizontal indeterminate progress bar. Draws
 * faster and more frequently (by making use of the animation timer), requires
 * minimal memory overhead, and allows some configuration via attributes:
 *
 *  * barColor (color attribute for the bar's solid color)
 *  * barHeight (dimension attribute for the height of the solid progress bar)
 *  * detentWidth (dimension attribute for the width of each transparent detent
 * in the bar)
 *
 *
 *
 * This progress bar has no intrinsic height, so you must declare it with one
 * explicitly. (It will use the given height as the bar's shadow height.)
 */
class ButteryProgressBar @JvmOverloads constructor(c: Context, attrs: AttributeSet? = null, @ColorRes mBarColor: Int = android.R.color.black) : View(c, attrs) {


    private val mShadow: GradientDrawable
    private val mAnimator = ValueAnimator()

    private val mPaint = Paint()
    private val mDensity: Float = c.resources.displayMetrics.density
    private val mSolidBarHeight: Int
    private val mSolidBarDetentWidth: Int

    private var mSegmentCount: Int = 0

    init {

        mSolidBarHeight = Math.round(DEFAULT_BAR_HEIGHT_DP * mDensity)
        mSolidBarDetentWidth = Math.round(DEFAULT_DETENT_WIDTH_DP * mDensity)

        mAnimator.setFloatValues(1.0f, 2.0f)
        mAnimator.repeatCount = ValueAnimator.INFINITE
        mAnimator.interpolator = ExponentialInterpolator()
        mAnimator.addUpdateListener { invalidate() }

        mPaint.color = ContextCompat.getColor(context, mBarColor)

        mShadow = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(mBarColor and 0x00ffffff or 0x22000000, 0))
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (changed) {
            val w = width

            mShadow.setBounds(0, mSolidBarHeight, w, height - mSolidBarHeight)

            val widthMultiplier = w.toFloat() / mDensity / BASE_WIDTH_DP.toFloat()
            // simple scaling by width is too aggressive, so dampen it first
            val durationMult = 0.3f * (widthMultiplier - 1) + 1
            val segmentMult = 0.1f * (widthMultiplier - 1) + 1
            mAnimator.duration = (BASE_DURATION_MS * durationMult).toInt().toLong()
            mSegmentCount = (BASE_SEGMENT_COUNT * segmentMult).toInt()
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (!mAnimator.isStarted) {
            return
        }

        mShadow.draw(canvas)

        val value = mAnimator.animatedValue as Float

        val w = width
        // Because the left-most segment doesn't start all the way on the left,
        // and because it moves
        // towards the right as it animates, we need to offset all drawing
        // towards the left. This
        // ensures that the left-most detent starts at the left origin, and that
        // the left portion
        // is never blank as the animation progresses towards the right.
        val offset = w shr mSegmentCount - 1
        // segments are spaced at half-width, quarter, eighth (powers-of-two).
        // to maintain a smooth
        // transition between segments, we used a power-of-two interpolator.
        for (i in 0 until mSegmentCount) {
            val l: Float = value * (w shr i + 1)
            val r: Float = if (i == 0) (w + offset).toFloat() else l * 2
            canvas.drawRect(l + mSolidBarDetentWidth - offset, 0f, r - offset,
                mSolidBarHeight.toFloat(), mPaint)
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)

        if (visibility == View.VISIBLE) {
            mAnimator.start()
        } else {
            mAnimator.cancel()
        }
    }

    private class ExponentialInterpolator : Interpolator {

        override fun getInterpolation(input: Float): Float =
            Math.pow(2.0, input.toDouble()).toFloat() - 1
    }

    companion object {

        /**
         * The baseline width that the other constants below are optimized for.
         */
        private val BASE_WIDTH_DP = 300
        /**
         * A reasonable animation duration for the given width above. It will be
         * weakly scaled up and down for wider and narrower widths, respectively--
         * the goal is to provide a relatively constant detent velocity.
         */
        private val BASE_DURATION_MS = 500
        /**
         * A reasonable number of detents for the given width above. It will be
         * weakly scaled up and down for wider and narrower widths, respectively.
         */
        private val BASE_SEGMENT_COUNT = 5

        private val DEFAULT_BAR_HEIGHT_DP = 4
        private val DEFAULT_DETENT_WIDTH_DP = 4
    }
}