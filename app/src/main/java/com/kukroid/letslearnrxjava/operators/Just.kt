package com.kukroid.letslearnrxjava.operators

import io.reactivex.Observable


class Just {

    fun operateJust(value : Any): Observable<Any> {

        return Observable.just(value)
    }
}