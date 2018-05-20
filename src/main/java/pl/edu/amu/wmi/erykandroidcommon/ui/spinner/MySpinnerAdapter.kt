package pl.edu.amu.wmi.erykandroidcommon.ui.spinner

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import pl.edu.amu.wmi.erykandroidcommon.R
import pl.edu.amu.wmi.erykandroidcommon.recycler.UniqueItem
import java.util.ArrayList

open class MySpinnerAdapter<T : UniqueItem>(context: Context) : ArrayAdapter<T>(context, R.layout.spinner_item) {

    var values: List<T> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount(): Int = values.size

    override fun getItem(position: Int): T? = if (values.size > position && position > -1) {
        values[position]
    } else null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        setLabelForView(position, super.getView(position, convertView, parent))

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        setLabelForView(position, super.getView(position, convertView, parent))

    private fun setLabelForView(position: Int, view: View): View {
        val label = view.findViewById<TextView>(android.R.id.text1)
        label.text = getLabel(values[position])
        return view
    }

    open fun getLabel(item: T?): String = item!!.name

    fun getItemLabel(position: Int): String = getLabel(getItem(position))

    fun setSelection(spinner: Spinner, item: T): Int = setSelection(spinner, getLabel(item))

    private fun setSelection(spinner: Spinner?, label: String): Int {
        if (spinner != null && !TextUtils.isEmpty(label))
            (0 until spinner.count)
                .filter { spinner.getItemAtPosition(it) == label }
                .forEach(spinner::setSelection)
        return -1
    }
}
