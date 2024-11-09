package com.ikajihan.app.animals

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animals(
    val name: String,
    val description: String,
    val habitat: String,
    val characteristics: String,
    val photo: Int
) : Parcelable