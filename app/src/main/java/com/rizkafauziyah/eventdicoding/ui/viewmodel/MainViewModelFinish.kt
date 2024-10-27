package com.rizkafauziyah.eventdicoding.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizkafauziyah.eventdicoding.data.response.EventResponse
import com.rizkafauziyah.eventdicoding.data.retrofit.ApiConfig
import com.rizkafauziyah.eventdicoding.model.DetailData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModelFinish : ViewModel() {
    private val _listEvents = MutableLiveData<List<DetailData>>()
    val listEvent: LiveData<List<DetailData>> = _listEvents

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "MainViewModel"
    }

    init {
        findEventVertical()
    }

    fun searchFinishedEvents(active: Int, limit: Int, query: String?) {
        _isLoading.value = true
        ApiConfig.getApiService().getEvents(active = active, limit = limit, query = query)
            .enqueue(object : Callback<EventResponse> {
                override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _listEvents.value = response.body()?.listEvents?.map {
                            DetailData(
                                summary = it.summary,
                                mediaCover = it.mediaCover,
                                registrants = it.registrants,
                                imageLogo = it.imageLogo,
                                link = it.link,
                                description = it.description,
                                ownerName = it.ownerName,
                                cityName = it.cityName,
                                quota = it.quota,
                                name = it.name,
                                id = it.id,
                                beginTime = it.beginTime,
                                endTime = it.endTime,
                                category = it.category
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
    }

    private fun findEventVertical() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getEvents(active = 0, limit = 5)
        client.enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listEvents.value = response.body()?.listEvents?.map {
                        DetailData(
                            summary = it.summary,
                            mediaCover = it.mediaCover,
                            registrants = it.registrants,
                            imageLogo = it.imageLogo,
                            link = it.link,
                            description = it.description,
                            ownerName = it.ownerName,
                            cityName = it.cityName,
                            quota = it.quota,
                            name = it.name,
                            id = it.id,
                            beginTime = it.beginTime,
                            endTime = it.endTime,
                            category = it.category
                        )
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}
