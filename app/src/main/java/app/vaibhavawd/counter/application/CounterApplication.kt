package app.vaibhavawd.counter.application

import android.app.Application
import io.paperdb.Paper

class CounterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initPaperDb()
    }

    private fun initPaperDb() {
        Paper.init(this.applicationContext)
    }
}