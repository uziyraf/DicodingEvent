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

class MainViewModel : ViewModel() {

    private val _listEvents = MutableLiveData<List<DetailData>>()
    val listEvent: LiveData<List<DetailData>> = _listEvents

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "MainViewModel"
    }

    init {
        findEventHorizontal()
    }

    private fun findEventHorizontal() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getEvents(1, 5)
        client.enqueue(object : Callback<EventResponse> {
            override fun onResponse(
                call: Call<EventResponse>,
                response: Response<EventResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listEvents.value = response.body()?.listEvents?.map { eventItem ->
                        DetailData(
                            id = eventItem.id,
                            name = eventItem.name,
                            summary = eventItem.summary,
                            mediaCover = eventItem.mediaCover,
                            registrants = eventItem.registrants,
                            imageLogo = eventItem.imageLogo,
                            link = eventItem.link,
                            description = eventItem.description,
                            ownerName = eventItem.ownerName,
                            cityName = eventItem.cityName,
                            quota = eventItem.quota,
                            beginTime = eventItem.beginTime,
                            endTime = eventItem.endTime,
                            category = eventItem.category
                        )
                    }
                    Log.e(TAG, "$response\n${response.body()?.listEvents}")
                } else {
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, throwable: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure : ${throwable.message}")
            }
        })
    }
}
