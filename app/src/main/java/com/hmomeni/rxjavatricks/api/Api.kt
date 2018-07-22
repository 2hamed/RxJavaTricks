package com.hmomeni.rxjavatricks.api

import com.hmomeni.rxjavatricks.pojos.Post
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET("posts/mine")
    fun getMyPosts(): Single<List<Post>>

    @GET("posts")
    fun getAllPosts(): Single<List<Post>>
}