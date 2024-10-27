package com.rizkafauziyah.eventdicoding.data.retrofit

import com.rizkafauziyah.eventdicoding.data.response.EventResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("events")
    fun getEvents(
        @Query("active") active: Int = 1,
        @Query("limit") limit : Int = 40,
        @Query("q") query : String? = null
    ): Call<EventResponse>
}