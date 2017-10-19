package pl.edu.amu.wmi.erykandroidcommon.verify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.view.Menu;
import android.view.MenuItem;

import pl.edu.amu.wmi.erykandroidcommon.R;
import pl.edu.amu.wmi.erykandroidcommon.base.BaseActivity;
import pl.edu.amu.wmi.erykandroidcommon.util.WindowUtil;


public abstract class AbstractEditorResultActivity extends BaseActivity implements FieldVerifier {

    private FormVerificationManager formVerificationManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(getMenuRes(), menu);
        return true;
    }

    @MenuRes
    abstract int getMenuRes();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_send) {
            if (formVerificationManager.verify()) {
                setResult(1, putExtraResult(new Intent()));
                finish();
            }
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract Intent putExtraResult(Intent resultIntent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        formVerificationManager = new FormVerificationManager(this, this);
        WindowUtil.enableActionBar(this);
        changeNotificationBarColor(this);
    }

}
