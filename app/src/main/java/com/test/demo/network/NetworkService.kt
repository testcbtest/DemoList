package com.example.testandroidapplication.network
import com.example.testandroidapplication.model.Album
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {

    @GET("/photos")
    fun getAlbumList() : Single<List<Album>>
    
}