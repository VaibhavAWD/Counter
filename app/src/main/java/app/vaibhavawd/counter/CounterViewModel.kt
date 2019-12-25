package app.vaibhavawd.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private companion object {
        const val FREQUENCY = 3
    }

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> = _counter

    private val _cycles = MutableLiveData(0)
    val cycles: LiveData<Int> = _cycles

    fun onCount() {
        if (_counter.value!! == FREQUENCY) {
            _counter.value = 0 // restart counting from 0
        }
        _counter.value = (_counter.value ?: 0) + 1
        if (_counter.value!! == FREQUENCY) {
            // cycle frequency reached, increment cycle count
            _cycles.value = (_cycles.value ?: 0) + 1
        }
    }
}