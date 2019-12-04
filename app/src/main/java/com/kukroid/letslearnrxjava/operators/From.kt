package com.kukroid.letslearnrxjava.operators

import io.reactivex.Observable


class From {

    fun operateFrom(items: Array<String>): Observable<Array<String>> {
        return Observable.fromArray(items)
    }
}