package com.example.code_challenge

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.code_challenge.fragment.QuestionFragment
import com.example.code_challenge.fragment.ResultFragment

class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val childFragments: Array<Fragment> = arrayOf(
        ResultFragment(),
        QuestionFragment()
    )

    override fun getItem(p0: Int): Fragment {
        return childFragments[p0]
    }

    override fun getCount(): Int {
        return childFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return getItem(position).javaClass.simpleName
    }

}