package com.rizkafauziyah.eventdicoding.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: String,
    val name: String,
    val summary: String,
    val pictureUrl: String,
    val quota: Int,
    val registrant: Int
) : Parcelable
