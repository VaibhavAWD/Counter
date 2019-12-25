package app.vaibhavawd.counter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object CounterBindings {

    @JvmStatic
    @BindingAdapter("app:cycles")
    fun setCycles(tv: TextView, cycles: Int) {
        tv.text = tv.context.getString(R.string.display_cycles, cycles)
    }
}