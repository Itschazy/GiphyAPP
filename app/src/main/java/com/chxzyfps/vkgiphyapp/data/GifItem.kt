package com.chxzyfps.vkgiphyapp.data

import kotlinx.parcelize.RawValue

data class GifItem(
    val id: String,
    val title: String,
    val username: String,
    val user: @RawValue GifAuthorItem
)
