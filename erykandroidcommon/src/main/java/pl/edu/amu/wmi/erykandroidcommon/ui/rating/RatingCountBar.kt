package pl.edu.amu.wmi.erykandroidcommon.ui.rating;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import pl.edu.amu.wmi.erykandroidcommon.R;
import pl.edu.amu.wmi.erykandroidcommon.R2;
import pl.edu.amu.wmi.erykandroidcommon.adapter.LoginSuccessAdapter;
import pl.edu.amu.wmi.erykandroidcommon.exception.AdapterLackException;

public class RatingCountBar extends Fragment {

    @BindView(R2.id.rating)
    RatingBar ratingBar;

    @BindView(R2.id.rating_count)
    TextView ratingCountTextView;

    private RatingCountBarAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating_count_bar, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initialize(Context context) {
        if (context instanceof LoginSuccessAdapter) {
            adapter = (RatingCountBarAdapter) context;
        } else {
            throw new AdapterLackException(getActivity(), RatingCountBarAdapter.class);
        }
    }

    public void setData(RateCountPair pair) {
        if (pair != null) {
            ratingBar.setRating(pair.rating());
            ratingCountTextView.setText("(" + pair.count() + ")");
        }
    }

    @OnTouch({R2.id.rating})
    public boolean onTouchGymRatePreview(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            adapter.onClick();
        }
        return true;
    }

    public interface RatingCountBarAdapter {
        void onClick();
    }

}
