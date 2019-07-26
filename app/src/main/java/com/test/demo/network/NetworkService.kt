package com.test.demo.network

import com.test.demo.model.Album
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {

    @GET("/photos")
    fun getAlbumList() : Single<List<Album>>
    
}