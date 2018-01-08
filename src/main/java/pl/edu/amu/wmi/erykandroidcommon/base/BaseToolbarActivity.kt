package pl.edu.amu.wmi.erykandroidcommon.base

/**
 * Extends the [BaseActivity] with a [android.widget.Toolbar].
 *
 * @author Cezary Krzyżanowski<cezary.krzyzanowski></cezary.krzyzanowski>@247.codes>on04.05.2017.
 */

abstract class BaseToolbarActivity : BaseActivity() {

    override fun onStart() {
        super.onStart()
        //        final Toolbar toolbar = findViewById(R.id.toolbar);
        //        setSupportActionBar(toolbar);

        //        final ActionBar actionBar = getSupportActionBar();
        //        assert actionBar != null;
        //        actionBar.setDisplayHomeAsUpEnabled(true);
        //        actionBar.setDisplayShowTitleEnabled(false);
    }

    override fun setTitle(title: CharSequence) {
        super.setTitle(title)
//        title_text_view!!.text = title
    }
}
