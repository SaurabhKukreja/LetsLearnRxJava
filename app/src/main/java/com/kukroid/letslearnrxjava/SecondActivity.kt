package com.kukroid.letslearnrxjava

import android.os.Bundle
import android.transition.Visibility
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        RxCompoundButton.checkedChanges(radioButtonShow).subscribe(Consumer {
            if(it) textView.visibility = View.VISIBLE
            else textView.visibility = View.INVISIBLE
        })

    }
}