package pl.edu.amu.wmi.erykandroidcommon.fragment

import android.support.annotation.DimenRes
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.View

import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractFragmentGrid
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder
import pl.edu.amu.wmi.erykandroidcommon.recycler.UniqueItem

abstract class FullScreenFragmentGrid<T : UniqueItem, S : AbstractViewHolder<T>> : AbstractFragmentGrid<T, S>() {

    private var hiddenViewList: List<View>? = null

    override fun onStart() {
        super.onStart()
        (activity as FullScreenListener).onComplete()
    }

    override fun onDetach() {
        super.onDetach()
        (activity as FullScreenListener).onDetach()
    }

    private fun setLayoutDimen(barLayout: AppBarLayout, @DimenRes id: Int) {
        val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, activity.resources.getDimension(id), resources.displayMetrics).toInt()
        val lp = barLayout.layoutParams as CoordinatorLayout.LayoutParams
        lp.height = px
        barLayout.layoutParams = lp
    }

    fun onComplete(barLayout: AppBarLayout, toolbarLayout: CollapsingToolbarLayout, toolbar: Toolbar, hiddenViewListViewList: List<View>) {
        this.hiddenViewList = hiddenViewListViewList
        barLayout.setExpanded(false, false)
        setLayoutDimen(barLayout, R.dimen.app_bar_height_collapsed)
        toolbarLayout.isTitleEnabled = false
        toolbar.title = getString(R.string.comments)
        for (view in hiddenViewListViewList) {
            view.visibility = View.GONE
        }
    }

    fun postDetach(barLayout: AppBarLayout, toolbarLayout: CollapsingToolbarLayout) {
        barLayout.setExpanded(true, true)
        if (android.os.Build.VERSION.SDK_INT >= 22) {
            setLayoutDimen(barLayout, R.dimen.app_bar_height_api22)
        } else {
            setLayoutDimen(barLayout, R.dimen.app_bar_height)
        }

        toolbarLayout.isTitleEnabled = true
        for (view in hiddenViewList!!) {
            view.visibility = View.VISIBLE
        }
    }

    interface FullScreenListener {
        fun onComplete()

        fun onDetach()
    }
}
