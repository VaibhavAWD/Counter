package app.vaibhavawd.counter.data

import io.paperdb.Paper

object CounterData {

    private const val KEY_COUNTER = "app.vaibhavawd.counter.data.KEY_COUNTER"

    fun setCounter(counter: Int, cycles: Int) {
        val counterData = Counter(counter, cycles)
        Paper.book().write(KEY_COUNTER, counterData)
    }

    fun getCounter(): Counter? {
        return Paper.book().read(KEY_COUNTER)
    }

    fun resetCounter() {
        Paper.book().delete(KEY_COUNTER)
    }
}