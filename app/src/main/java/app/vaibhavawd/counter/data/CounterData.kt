package app.vaibhavawd.counter.data

import io.paperdb.Paper

object CounterData {

    private const val KEY_COUNTER = "app.vaibhavawd.counter.data.KEY_COUNTER"

    fun setCounter(counter: Int, cycles: Int, frequency: Int) {
        val newCounter = Counter(counter, cycles, frequency)
        Paper.book().write(KEY_COUNTER, newCounter)
    }

    fun getCounter(): Counter? {
        return Paper.book().read(KEY_COUNTER)
    }

    fun resetCounter() {
        val currentFrequency = getCounter()?.frequency ?: 10
        val newCounter = Counter(0, 0, currentFrequency)
        Paper.book().write(KEY_COUNTER, newCounter)
    }
}