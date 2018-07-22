package com.hmomeni.rxjavatricks

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.hmomeni.rxjavatricks.pojos.Post
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.addTo

class MainActivity : AppCompatActivity() {
    val compositeDisposable = CompositeDisposable()

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val api = (application as App).api

        api.getAllPosts()
                .zipWith(api.getMyPosts(), BiFunction<List<Post>, List<Post>, List<Post>> { t1, t2 ->
                    mutableListOf<Post>().apply {
                        addAll(t1)
                        addAll(t2)
                    }
                })
                .iomain()
                .subscribe({
                    // We now have all the posts as a single result
                    Log.d("RxJavaTricks", "items size: ${it.size}")
                }, {
                    // log error just in case
                    Log.d("RxJavaTricks", "error", it)
                }).addTo(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
