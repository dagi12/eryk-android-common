package pl.edu.amu.wmi.erykandroidcommon.ui.spinner

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

import java.util.ArrayList

import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.recycler.UniqueItem


internal class MySpinnerAdapter<T : UniqueItem>(context: Context) : ArrayAdapter<T>(context, R.layout.spinner_item) {

    private var values: List<T> = ArrayList()

    fun setValues(values: List<T>) {
        this.values = values
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(position: Int): T? {
        return if (values.size > position && position > -1) {
            values[position]
        } else null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return setLabelForView(position, super.getView(position, convertView, parent))
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return setLabelForView(position, super.getView(position, convertView, parent))
    }

    private fun setLabelForView(position: Int, view: View): View {
        val label = view.findViewById<TextView>(android.R.id.text1)
        label.text = getLabel(values[position])
        return view
    }

    private fun getLabel(item: T?): String {
        return item!!.name
    }

    fun getItemLabel(position: Int): String {
        return getLabel(getItem(position))
    }

    fun setSelection(spinner: Spinner, item: T): Int {
        return setSelection(spinner, getLabel(item))
    }

    private fun setSelection(spinner: Spinner?, label: String): Int {
        if (spinner != null && !TextUtils.isEmpty(label))
            for (i in 0 until spinner.count) {
                if (spinner.getItemAtPosition(i) == label) {
                    spinner.setSelection(i)
                }
            }
        return -1
    }

}
