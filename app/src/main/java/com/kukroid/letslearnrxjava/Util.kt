package com.kukroid.letslearnrxjava

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class Util {
    companion object {

        val TAG = "LetsLearnRxJava"

        fun d(msg: String) {
            Log.d(TAG, msg)
        }
    }
}