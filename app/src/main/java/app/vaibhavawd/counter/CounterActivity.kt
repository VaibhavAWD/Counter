package app.vaibhavawd.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_counter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reset -> {
                resetCounter()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun resetCounter() {
        binding.viewmodel?.reset()
    }
}
