package core.logger

import com.areeb.weatherunion.core.logger.CoreLogger

class TestLogger : CoreLogger {
    override fun debug(tag: String, message: String, throwable: Throwable?) {
        println("$tag: $message")
    }

    override fun assert(tag: String, message: String, throwable: Throwable?) {
        println("$tag: $message")
    }

    override fun info(tag: String, message: String, throwable: Throwable?) {
        println("$tag: $message")
    }

    override fun error(tag: String, message: String?, throwable: Throwable?) {
        println("$tag: $message")
    }

    override fun warn(tag: String, message: String, throwable: Throwable?) {
        println("$tag: $message")
    }
}
