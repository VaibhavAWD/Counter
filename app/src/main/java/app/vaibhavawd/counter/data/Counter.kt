package app.vaibhavawd.counter.data

data class Counter(
    val counter: Int,
    val cycles: Int,
    val frequency: Int = 10
)