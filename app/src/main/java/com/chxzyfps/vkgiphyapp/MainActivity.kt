package com.chxzyfps.vkgiphyapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chxzyfps.vkgiphyapp.adapter.GifAdapter
import com.chxzyfps.vkgiphyapp.data.GifItem
import com.chxzyfps.vkgiphyapp.data.MainAPI
import com.chxzyfps.vkgiphyapp.databinding.ActivityMainBinding
import com.chxzyfps.vkgiphyapp.utils.Constants.Companion.BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), GifAdapter.OnItemClickListener {


    lateinit var binding: ActivityMainBinding
    lateinit var adapter: GifAdapter
    lateinit var gifList: List<GifItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(MainAPI::class.java)

        binding.apply {
            rcView.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = GifAdapter(this@MainActivity)
            rcView.adapter = adapter
        }

        CoroutineScope(Dispatchers.IO).launch {
            gifList = mainApi.getTrendingGifs().data
            runOnUiThread {
                adapter.submitList(gifList)
            }
        }

        binding.apply {
            searchButton.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val searchQuery = binding.editTextSearchQuery.text
                    gifList = mainApi.getSearchGifs(query = searchQuery.toString()).data
                    runOnUiThread {
                        adapter.submitList(gifList)
                        tvGifsCategory.text = searchQuery
                        hideKeyboard(it)
                    }
                }
            }
        }

    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, GifDetailsActivity::class.java)
        intent.putExtra("gifId", gifList[position].id)
        intent.putExtra("gifTitle", gifList[position].title)
        intent.putExtra("gifAuthorInfo", gifList[position].user)
        startActivity(intent)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}