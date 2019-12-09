package com.kukroid.letslearnrxjava.subjects

import com.kukroid.letslearnrxjava.Util
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.AsyncSubject


class AsyncSubjectDemo {

    fun testAsyncSubject() {

        val myObservable  = Observable.just("Java","Kotlin")
        val subjects = AsyncSubject.create<String>()

        myObservable.subscribe(subjects)
        subjects.subscribe(getObserver())

    }

    private fun getObserver(): Observer<String> {

        val myObserver = object : Observer<String> {
            override fun onComplete() {
                Util.d("observer Complete")
            }

            override fun onSubscribe(d: Disposable) {
                Util.d("observer onSubscribe")
            }

            override fun onNext(t: String) {
                Util.d("observer onNext" + t)
            }

            override fun onError(e: Throwable) {
                Util.d("observer onError")
            }
        }
        return myObserver
    }
}