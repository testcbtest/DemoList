package com.test.demo.ui.vm

import android.databinding.ObservableField
import android.view.View
import com.google.gson.Gson
import com.test.demo.injection.BaseViewModel
import com.test.demo.model.Album
import com.test.demo.network.NetworkService
import com.test.demo.ui.AdapterAlbumsRecycler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostAlbumsViewModel : BaseViewModel() {
    @Inject
    lateinit var networkService: NetworkService


    val gson = Gson()
    var adapterAlbumsRecycler: AdapterAlbumsRecycler = AdapterAlbumsRecycler();
    var progressBarState = ObservableField<Boolean>()
    var layoutErrorState = ObservableField<Boolean>()
    var textViewableListener = ObservableField<String>()

    init {
        loadAllAlbums()
    }


    private fun loadAllAlbums() {
        OnFetchData()

        networkService.getAlbumList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            { result -> onRetrievePostListSuccess(result) },
            {
                OnDataErrorLoaded()
            }
        )
    }


    private fun onRetrievePostListSuccess(postList: List<Album>) {
        progressBarState.set(false)
        layoutErrorState.set(false)
        adapterAlbumsRecycler.updateChanged(postList)
    }

    private fun OnFetchData() {
        progressBarState.set(true)
        layoutErrorState.set(false)
    }

    private fun OnDataErrorLoaded() {
        progressBarState.set(false)
        layoutErrorState.set(true)

    }

    fun OnRetryClick(v: View) {
        loadAllAlbums()
    }

}