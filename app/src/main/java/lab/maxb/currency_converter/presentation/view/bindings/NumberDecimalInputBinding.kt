package lab.maxb.currency_converter.presentation.view.bindings

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

object NumberDecimalInputBinding {
    @JvmStatic
    var EditText.value: Double
        @InverseBindingAdapter(attribute = "value")
        get() = text.toString().toDouble()
        @BindingAdapter("value")
        set(value) = setText(value.toString())

    @JvmStatic @BindingAdapter("valueAttrChanged")
    fun EditText.setListener(listener: InverseBindingListener?) {
        onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                try {
                    listener?.onChange()
                } catch (e: NumberFormatException) {
                    error = e.localizedMessage
                }
        }
    }
}