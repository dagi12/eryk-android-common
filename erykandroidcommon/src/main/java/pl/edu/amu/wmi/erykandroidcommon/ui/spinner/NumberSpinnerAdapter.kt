package pl.edu.amu.wmi.erykandroidcommon.ui.spinner

import android.content.Context
import android.widget.ArrayAdapter

import java.util.ArrayList

import pl.edu.amu.wmi.erykandroidcommon.R


class NumberSpinnerAdapter(context: Context, count: Int) : ArrayAdapter<Int>(context, R.layout.spinner_dropdown_item, getItems(count)) {

    private fun getItems(count: Int): List<Int> {
        val integers = ArrayList<Int>()
        for (i in 1..count) {
            integers.add(i)
        }
        return integers
    }

}
