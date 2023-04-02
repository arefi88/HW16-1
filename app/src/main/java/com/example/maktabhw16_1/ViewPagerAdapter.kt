package com.example.maktabhw16_1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TodoFragment()
            }
            1 -> {
                DoingFragment()
            }
            2 -> {
                DoneFragment()
            }
            else -> {
                TodoFragment()
            }
        }
    }
}