package com.example.myapplicationkotlin.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplicationkotlin.view.fragment.PagerFragment


class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    var size : Int = 0
    var position2: Int = 0
    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment {
        var fragment = PagerFragment()
        fragment.position = (position + position2) % size
        return fragment
    }


}