package lab.maxb.currency_converter.presentation.view.bindings

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

object SpinnerBindings {
    @JvmStatic @BindingAdapter("entries")
    fun Spinner.setEntries(entries: List<Any>?) {
        ArrayAdapter(context, android.R.layout.simple_spinner_item, entries ?: return).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = this
        }
    }

    @JvmStatic @BindingAdapter("selectedValueAttrChanged")
    fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        onItemSelectedListener = inverseBindingListener?.let {
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapter: AdapterView<*>, view: View,
                                            position: Int, id: Long) {
                    if (tag != position) it.onChange()
                }

                override fun onNothingSelected(adapter: AdapterView<*>) {}
            }
        }
    }

    @JvmStatic
    var Spinner.selectedValue: Any?
        @InverseBindingAdapter(attribute = "selectedValue")
        get() = selectedItem
        @BindingAdapter("selectedValue")
        set(value) {
            (adapter as? ArrayAdapter<Any?>)?.getPosition(value)?.let {
                setSelection(it, false)
                tag = it
            }
        }
}