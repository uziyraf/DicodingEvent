package com.rizkafauziyah.eventdicoding.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizkafauziyah.eventdicoding.databinding.FragmentFavoriteBinding
import com.rizkafauziyah.eventdicoding.ui.adapter.FavoriteListAdapter
import com.rizkafauziyah.eventdicoding.ui.viewmodel.EventViewModel
import com.rizkafauziyah.eventdicoding.ui.viewmodel.EventViewModelFactory

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val eventViewModel: EventViewModel by viewModels { EventViewModelFactory(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView
        val adapter = FavoriteListAdapter { detailData ->
            // Tindakan ketika item diklik, misalnya menampilkan detail
        }
        binding.favoriteListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.favoriteListRecyclerView.adapter = adapter

        // Mengamati data events dari ViewModel
        eventViewModel.allEvents.observe(viewLifecycleOwner, Observer { events ->
            events?.let { adapter.submitList(it) }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
