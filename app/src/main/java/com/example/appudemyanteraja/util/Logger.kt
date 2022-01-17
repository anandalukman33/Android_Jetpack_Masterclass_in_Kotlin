package com.example.appudemyanteraja.util

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.Printer

object Logger  {
    fun init() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    val VERBOSE = 2
    val DEBUG = 3
    val INFO = 4
    val WARN = 5
    val ERROR = 6
    val ASSERT = 7

    fun printer(printer: Printer) {
        Logger.printer(printer)
    }

    fun addLogAdapter(adapter: LogAdapter) {
        Logger.addLogAdapter(checkNotNull(adapter))
    }

    fun clearLogAdapters() {
        Logger.clearLogAdapters()
    }

    fun t(tag: String?): Printer {
        return Logger.t(tag)
    }

    fun log(priority: Int, tag: String?, message: String?, throwable: Throwable?) {
        Logger.log(priority, tag, message, throwable)
    }

    fun d(message: String, vararg args: Any) {
        Logger.d(message, *args)
    }

    fun d(`object`: Any?) {
        Logger.d(`object`)
    }

    fun e(message: String, vararg args: Any) {
        Logger.e(null, message, *args)
    }

    fun e(throwable: Throwable?, message: String, vararg args: Any) {
        Logger.e(throwable, message, *args)
    }

    fun i(message: String, vararg args: Any) {
        Logger.i(message, *args)
    }

    fun v(message: String, vararg args: Any) {
        Logger.v(message, *args)
    }

    fun w(message: String, vararg args: Any) {
        Logger.w(message, *args)
    }

    fun wtf(message: String, vararg args: Any) {
        Logger.wtf(message, *args)
    }

    fun json(json: String?) {
        Logger.json(json)
    }

    fun xml(xml: String?) {
        Logger.xml(xml)
    }
}