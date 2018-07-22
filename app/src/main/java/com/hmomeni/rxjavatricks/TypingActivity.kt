package com.hmomeni.rxjavatricks

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class TypingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_typing)

        Flowable.create<String>({ e ->
            findViewById<TextInputEditText>(R.id.inputText).addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    e.onNext(s.toString())
                }
            })

        }, BackpressureStrategy.DROP)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    // 500 miliseconds is passed
                }
    }
}
