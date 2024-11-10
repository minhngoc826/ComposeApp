package com.example.composeapp.common

import android.util.Log

class Logger {
    companion object {
        private val TAG = "ComposeApp---"
        fun d(tag: String, message: String) {
            Log.d(TAG + tag, message)
        }

        fun i(tag: String, message: String) {
            Log.i(TAG + tag, message)
        }

        fun v(tag: String, message: String) {
            Log.v(TAG + tag, message)
        }

        fun e(tag: String, message: String) {
            Log.e(TAG + tag, message)
        }
    }
}