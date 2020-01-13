package app.vaibhavawd.counter.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import io.paperdb.Paper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation tests for implementation of [CounterData] that uses [Paper].
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class CounterDataTest {

    // Use to initialize Paper
    private val context = ApplicationProvider.getApplicationContext<Context>()

    companion object {
        // Paper book name
        private const val KEY_COUNTER = "app.vaibhavawd.counter.data.KEY_COUNTER"
    }

    // test counter data
    private val testCounter = Counter(1, 2, 3)

    @Before
    fun initPaper() {
        Paper.init(context)
    }

    @After
    fun cleanUp() {
        reset()
    }

    @Test
    fun counterData_setCounter_counterSaved() {
        // WHEN - saving counter
        CounterData.setCounter(testCounter.counter, testCounter.cycles, testCounter.frequency)

        // THEN - verify that counter was saved
        val counter = CounterData.getCounter()!!
        assertThat(counter.counter).isEqualTo(testCounter.counter)
        assertThat(counter.cycles).isEqualTo(testCounter.cycles)
        assertThat(counter.frequency).isEqualTo(testCounter.frequency)
    }

    @Test
    fun counterData_getCounter_empty_counterIsNotRetrieved() {
        // GIVEN - counter is not present
        // make sure counter is absent
        reset()

        // WHEN - getting counter
        val counter = CounterData.getCounter()

        // THEN - verify that the counter is null
        assertThat(counter).isNull()
    }

    @Test
    fun counterData_getCounter_counterIsRetrieved() {
        // GIVEN - counter is present
        CounterData.setCounter(testCounter.counter, testCounter.cycles, testCounter.frequency)

        // WHEN - getting counter
        val counter = CounterData.getCounter()

        // THEN - verify that counter has expected values
        assertThat(counter).isNotNull()
        assertThat(counter).isEqualTo(testCounter)
    }

    @Test
    fun counterData_resetCounter_counterHasExpectedValues() {
        // GIVEN - counter is present
        CounterData.setCounter(testCounter.counter, testCounter.cycles, testCounter.frequency)

        // WHEN - resetting counter
        CounterData.resetCounter()

        // THEN - verify that the counter has expected values
        val counter = CounterData.getCounter()
        assertThat(counter).isNotNull()
        assertThat(counter!!.counter).isEqualTo(0)
        assertThat(counter.cycles).isEqualTo(0)
        assertThat(counter.frequency).isEqualTo(testCounter.frequency)
    }

    /**
     * Helper method to cleans up the counter data to avoid test pollution.
     */
    private fun reset() {
        Paper.book().delete(KEY_COUNTER)
    }
}