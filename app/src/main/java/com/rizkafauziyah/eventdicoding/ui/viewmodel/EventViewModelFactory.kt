package com.rizkafauziyah.eventdicoding.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rizkafauziyah.eventdicoding.data.repository.EventRepository

class EventViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
            val repository = EventRepository(application) // Create the repository instance
            return EventViewModel(repository) as T        // Pass repository to ViewModel
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
