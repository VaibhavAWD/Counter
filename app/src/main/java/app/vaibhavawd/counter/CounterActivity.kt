package app.vaibhavawd.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.vaibhavawd.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var viewModel: CounterViewModel
    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
    }

    private fun setupDataBinding() {
        viewModel = ViewModelProvider(this)[CounterViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@CounterActivity
        }
    }
}
