package com.test.demo.ui.vm

import android.arch.lifecycle.MutableLiveData
import com.test.demo.injection.BaseViewModel
import com.test.demo.model.Album

class PostSingleAlbumViewModel : BaseViewModel() {
    private val title = MutableLiveData<String>()
    private val thumbnailUrl = MutableLiveData<String>()

    fun bind(album: Album) {
        title.value = album.title
        thumbnailUrl.value = album.thumbnailUrl
    }

    fun getUrl(): MutableLiveData<String> {
        return thumbnailUrl
    }


    fun getValueTitle(): MutableLiveData<String> {
        return title
    }

}