package app.vaibhavawd.counter.data

import app.vaibhavawd.counter.DEFAULT_FREQUENCY

data class Counter(
    val counter: Int,
    val cycles: Int,
    val frequency: Int = DEFAULT_FREQUENCY
)