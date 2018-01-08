package pl.edu.amu.wmi.erykandroidcommon.verify

import android.content.Intent
import android.os.Bundle
import android.support.annotation.MenuRes
import android.view.Menu
import android.view.MenuItem

import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.base.BaseActivity
import pl.edu.amu.wmi.erykandroidcommon.helper.WindowUtil

abstract class AbstractEditorResultActivity(fieldVerifier: FieldVerifier) : BaseActivity() {

    private val formVerificationManager = FormVerificationManager(fieldVerifier)

    @get:MenuRes
    internal abstract val menuRes: Int

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(menuRes, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_send) {
            if (formVerificationManager.verify()) {
                setResult(1, putExtraResult(Intent()))
                finish()
            }
        } else if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    protected abstract fun putExtraResult(resultIntent: Intent): Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowUtil.enableActionBar(this)
    }
}
