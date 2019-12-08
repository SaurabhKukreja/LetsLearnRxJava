package com.kukroid.letslearnrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kukroid.letslearnrxjava.operators.Create
import com.kukroid.letslearnrxjava.operators.From
import com.kukroid.letslearnrxjava.operators.Just
import com.kukroid.letslearnrxjava.operators.Range
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

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

            // Todo Later

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

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}

private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
     apply { compositeDisposable.add(this) }
}


