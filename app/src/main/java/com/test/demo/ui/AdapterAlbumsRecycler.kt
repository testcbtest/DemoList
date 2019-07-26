package com.test.demo.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.demo.R
import com.test.demo.databinding.SingleItemAlbumLayoutBinding
import com.test.demo.model.Album
import com.test.demo.ui.vm.PostSingleAlbumViewModel

class AdapterAlbumsRecycler : RecyclerView.Adapter<AdapterAlbumsRecycler.ViewHolder>() {
    private lateinit var listAlbum: List<Album>


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val itemAlbumBinding: SingleItemAlbumLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.single_item_album_layout,
            parent,
            false
        );
        return ViewHolder(itemAlbumBinding)
    }

    override fun getItemCount(): Int {
        return if (::listAlbum.isInitialized) listAlbum.size else 0
    }

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {
        viewholder.bind(listAlbum[position])
    }

    fun updateChanged(notifiedListAlbum: List<Album>) {
        listAlbum = notifiedListAlbum
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: SingleItemAlbumLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostSingleAlbumViewModel()

        fun bind(album: Album) {
            viewModel.bind(album)
            binding.viewModel = viewModel
        }
    }
}
