package com.rizkafauziyah.eventdicoding.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.activity.enableEdgeToEdge
import com.google.android.material.navigation.NavigationBarView
import com.rizkafauziyah.eventdicoding.R
import com.rizkafauziyah.eventdicoding.databinding.ActivityMainBinding
import com.rizkafauziyah.eventdicoding.fragment.FinishedFragment
import com.rizkafauziyah.eventdicoding.fragment.HomeFragment
import com.rizkafauziyah.eventdicoding.fragment.UpcomingFragment

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationBar: NavigationBarView

    private val finishedFragment = FinishedFragment()
    private val homeFragment = HomeFragment()
    private val upcomingFragment = UpcomingFragment()
    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationBar = binding.bottomNavigation

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(binding.frameContainer.id, homeFragment, HomeFragment::class.java.simpleName).show(homeFragment)
                add(binding.frameContainer.id, upcomingFragment, UpcomingFragment::class.java.simpleName).hide(upcomingFragment)
                add(binding.frameContainer.id, finishedFragment, FinishedFragment::class.java.simpleName).hide(finishedFragment)
                commit()
            }
        }

        navBarClick()
    }

    private fun navBarClick() {
        navigationBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.upcoming -> {
                    switchFragment(upcomingFragment)
                    true
                }
                R.id.finished -> {
                    switchFragment(finishedFragment)
                    true
                }
                R.id.home -> {
                    switchFragment(homeFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun switchFragment(targetFragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(targetFragment).commit()
        activeFragment = targetFragment
    }
}
