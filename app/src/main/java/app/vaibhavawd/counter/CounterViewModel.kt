package app.vaibhavawd.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.vaibhavawd.counter.data.CounterData

class CounterViewModel : ViewModel() {

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> = _counter

    private val _cycles = MutableLiveData(0)
    val cycles: LiveData<Int> = _cycles

    private val _frequency = MutableLiveData(10)
    val frequency: LiveData<Int> = _frequency

    init {
        loadCounter()
    }

    private fun loadCounter() {
        val counterData = CounterData.getCounter()
        counterData?.let {
            _counter.value = it.counter
            _cycles.value = it.cycles
            _frequency.value = it.frequency
        }
    }

    fun onCount() {
        if (_counter.value!! == frequency.value) {
            _counter.value = 0 // restart counting from 0
        }
        _counter.value = (_counter.value ?: 0) + 1
        if (_counter.value!! == frequency.value) {
            // cycle frequency reached, increment cycle count
            _cycles.value = (_cycles.value ?: 0) + 1
        }
    }

    fun reset() {
        _counter.value = 0
        _cycles.value = 0
        CounterData.resetCounter()
    }

    fun setFrequency(value: String) {
        _frequency.value = Integer.parseInt(value)
        saveCounter()
        // as the frequency has been changed so
        // it is a good idea to reset the counter
        reset()
    }

    fun saveCounter() {
        val currentCounter = _counter.value ?: 0
        val currentCycles = _cycles.value ?: 0
        val currentFrequency = _frequency.value ?: 10
        CounterData.setCounter(currentCounter, currentCycles, currentFrequency)
    }
}