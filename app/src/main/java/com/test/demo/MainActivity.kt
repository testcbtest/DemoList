package com.test.demo

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.test.demo.databinding.ActivityMainBinding
import com.test.demo.ui.vm.PostAlbumsViewModel
import com.test.demo.ui.vm.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PostAlbumsViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(PostAlbumsViewModel::class.java)
        binding.viewModel = viewModel
    }
}
