package com.example.myapplicationkotlin.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplicationkotlin.model.Note
import com.example.myapplicationkotlin.view.fragment.PagerFragment

class PagerAdapter(fragment: Fragment, var position2: Int, var size: Int, var notes: MutableList<Note>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment {
        return PagerFragment(notes[(position + position2) % size])
    }
}