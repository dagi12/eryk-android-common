package pl.edu.amu.wmi.erykandroidcommon.fragment;

import android.support.annotation.DimenRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

import pl.edu.amu.wmi.erykandroidcommon.R;
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractFragmentGrid;
import pl.edu.amu.wmi.erykandroidcommon.recycler.AbstractViewHolder;
import pl.edu.amu.wmi.erykandroidcommon.recycler.UniqueItem;

public abstract class FullScreenFragmentGrid<T extends UniqueItem, S extends AbstractViewHolder<T>> extends AbstractFragmentGrid<T, S> {


    private List<? extends View> hiddenViewList;

    @Override
    public void onStart() {
        super.onStart();
        ((FullScreenListener) getActivity()).onComplete();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((FullScreenListener) getActivity()).onDetach();
    }

    private void setLayoutDimen(AppBarLayout barLayout, @DimenRes int id) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, getActivity().getResources().getDimension(id), getResources().getDisplayMetrics());
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) barLayout.getLayoutParams();
        lp.height = px;
        barLayout.setLayoutParams(lp);
    }

    public void onComplete(AppBarLayout barLayout, CollapsingToolbarLayout toolbarLayout, Toolbar toolbar, List<? extends View> hiddenViewListViewList) {
        this.hiddenViewList = hiddenViewListViewList;
        barLayout.setExpanded(false, false);
        setLayoutDimen(barLayout, R.dimen.app_bar_height_collapsed);
        toolbarLayout.setTitleEnabled(false);
        toolbar.setTitle(getString(R.string.comments));
        for (View view : hiddenViewListViewList) {
            view.setVisibility(View.GONE);
        }
    }

    public void postDetach(AppBarLayout barLayout, CollapsingToolbarLayout toolbarLayout) {
        barLayout.setExpanded(true, true);
        if (android.os.Build.VERSION.SDK_INT >= 22) {
            setLayoutDimen(barLayout, R.dimen.app_bar_height_api22);
        } else {
            setLayoutDimen(barLayout, R.dimen.app_bar_height);
        }

        toolbarLayout.setTitleEnabled(true);
        for (View view : hiddenViewList) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public interface FullScreenListener {
        void onComplete();

        void onDetach();
    }

}
