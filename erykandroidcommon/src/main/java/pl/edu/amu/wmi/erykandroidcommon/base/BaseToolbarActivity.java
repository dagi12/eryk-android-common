package pl.edu.amu.wmi.erykandroidcommon.base;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.edu.amu.wmi.erykandroidcommon.R;
import pl.edu.amu.wmi.erykandroidcommon.R2;

/**
 * Extends the {@link BaseActivity} with a {@link android.widget.Toolbar}.
 *
 * @author Cezary Krzyżanowski<cezary.krzyzanowski@247.codes>on04.05.2017.
 */

public abstract class BaseToolbarActivity extends BaseActivity {

    @BindView(R2.id.title)
    protected TextView titleTextView;

    @Override
    protected void onStart() {
        super.onStart();
        ButterKnife.bind(this);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        titleTextView.setText(title);
    }
}
