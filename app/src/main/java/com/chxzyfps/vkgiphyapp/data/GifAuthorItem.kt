package com.chxzyfps.vkgiphyapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifAuthorItem(
    val avatar_url: String,
    val username: String,
    val profile_url: String
) : Parcelable