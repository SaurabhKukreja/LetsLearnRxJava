package com.kukroid.letslearnrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.kukroid.letslearnrxjava.operators.Create
import com.kukroid.letslearnrxjava.operators.From
import com.kukroid.letslearnrxjava.operators.Just
import com.kukroid.letslearnrxjava.operators.Range
import com.kukroid.letslearnrxjava.subjects.AsyncSubjectDemo
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        justButton.setOnClickListener(View.OnClickListener {

            Util.d("Converting Integer to Observable")
            lateinit var justObservable: Observable<Any>

            justObservable = Just().operateJust(10)
            justObservable.subscribe({
                justResult.setText(it.toString())
            }).addTo(compositeDisposable)
        })

        fromButton.setOnClickListener(View.OnClickListener {

            /*var observer: Observer<Int> = object :Observer<Int> {
                override fun onComplete() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSubscribe(d: Disposable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onNext(t: Int) {
                    Util.d("Output from - fromArray: "+ t)
                }

                override fun onError(e: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }*/

            var numbers = arrayOf<Int>(1, 2, 3)
            Observable.fromIterable(listOf(1,2,3))
                .subscribe({
                    Util.d("Emit Integers from list: "+ it)
                })

        })

        rangeButton.setOnClickListener(View.OnClickListener {
            Observable.range(10, 10).subscribe({
                Util.d(it.toString())
            })

            var rangeObservable: Observable<Int>? = Range().operateRange(50,10)
            rangeObservable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                Util.d(it.toString())
                rangeResult.append(it.toString()+",")
            })


        })

        createButton.setOnClickListener(View.OnClickListener {
            val createObservable = Create().operateCreate()
            createObservable.subscribe({
                Util.d("Student Name : " +it.name)
            })
        })

        mapButton.setOnClickListener(View.OnClickListener {
            val mapObservableData = Create().operateCreate()
            val mapObservable = mapObservableData.map { "" + it.age + "years" }

            mapObservable.subscribe({
                rangeResult.setText(it)
            })


        })

        flatMapButton.setOnClickListener(View.OnClickListener {
            val flatMapObservableData = Create().operateCreate()
            val flatMapsObservable = flatMapObservableData.flatMap { it -> Observable.just(Student("New Member", "nMember@gmail.com", 29))}

            flatMapsObservable.subscribe({
                Util.d("Student Name with FlatMap: "+it.name)
            })

        })

        testAsyncSubject.setOnClickListener(View.OnClickListener {
            AsyncSubjectDemo().testAsyncSubject()
        })

        RxView.clicks(clearButton).subscribe(io.reactivex.functions.Consumer {
            justResult.setText("")
            rangeResult.setText("")
        }).addTo(compositeDisposable)


    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}

private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
     apply { compositeDisposable.add(this) }
}


