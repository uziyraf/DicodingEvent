package com.rizkafauziyah.eventdicoding.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizkafauziyah.eventdicoding.data.database.entity.DetailDataEntity
import com.rizkafauziyah.eventdicoding.data.repository.EventRepository
import kotlinx.coroutines.launch

class EventViewModel(private val repository: EventRepository) : ViewModel() {

    // Observing all event data from the repository
    val allEvents: LiveData<List<DetailDataEntity>> = repository.getAllEvent()

    // Function to insert event data into the database
    fun insert(detailData: DetailDataEntity) {
        viewModelScope.launch {
            repository.insert(detailData)
        }
    }

    // Function to delete event data from the database
    fun delete(detailData: DetailDataEntity) {
        viewModelScope.launch {
            repository.delete(detailData)
        }
    }
}
