package pl.edu.amu.wmi.erykandroidcommon.ui.spinner

import android.content.Context
import android.widget.ArrayAdapter
import pl.edu.amu.wmi.erykandroidcommon.R

class NumberSpinnerAdapter(context: Context, count: Int) : ArrayAdapter<Int>(context, R.layout.spinner_dropdown_item, getItems(count)) {

    companion object {
        private fun getItems(count: Int): List<Int> = (1..count).toList()
    }
}
