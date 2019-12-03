package com.kukroid.letslearnrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kukroid.letslearnrxjava.operators.Just
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}

private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
     apply { compositeDisposable.add(this) }
}


