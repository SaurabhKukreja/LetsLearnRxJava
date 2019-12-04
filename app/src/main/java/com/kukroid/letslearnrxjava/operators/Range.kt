package com.kukroid.letslearnrxjava.operators

import io.reactivex.Observable


class Range {

    fun operateRange(start: Int, end: Int): Observable<Int>? {
        return Observable.range(start, end)
    }
}