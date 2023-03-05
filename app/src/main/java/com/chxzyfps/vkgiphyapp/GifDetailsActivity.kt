package com.chxzyfps.vkgiphyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.chxzyfps.vkgiphyapp.data.GifAuthorItem
import com.chxzyfps.vkgiphyapp.databinding.ActivityGifDetailsBinding

class GifDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityGifDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGifDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gifId = intent.getStringExtra("gifId")
        val gifTitle = intent.getStringExtra("gifTitle")
        val authorInfo = intent.getParcelableExtra<GifAuthorItem>("gifAuthorInfo")

        binding.apply {
            val gifUrl = "https://media4.giphy.com/media/${gifId}/giphy.gif"
            Glide.with(this@GifDetailsActivity).load(gifUrl).into(ivGif)
            tvGifTitle.text = gifTitle

        }
    }
}