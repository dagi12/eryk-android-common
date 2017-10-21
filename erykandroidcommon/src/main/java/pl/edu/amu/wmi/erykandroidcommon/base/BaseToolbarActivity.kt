package pl.edu.amu.wmi.erykandroidcommon.base

import android.widget.TextView

import butterknife.BindView
import butterknife.ButterKnife
import pl.edu.amu.wmi.erykandroidcommon.R2

/**
 * Extends the [BaseActivity] with a [android.widget.Toolbar].
 *
 * @author Cezary Krzyżanowski<cezary.krzyzanowski></cezary.krzyzanowski>@247.codes>on04.05.2017.
 */

abstract class BaseToolbarActivity : BaseActivity() {

    @BindView(R2.id.title)
    var titleTextView: TextView? = null

    override fun onStart() {
        super.onStart()
        ButterKnife.bind(this)

        //        final Toolbar toolbar = findViewById(R.id.toolbar);
        //        setSupportActionBar(toolbar);

        //        final ActionBar actionBar = getSupportActionBar();
        //        assert actionBar != null;
        //        actionBar.setDisplayHomeAsUpEnabled(true);
        //        actionBar.setDisplayShowTitleEnabled(false);

    }

    override fun setTitle(title: CharSequence) {
        super.setTitle(title)
        titleTextView!!.text = title
    }
}
