package com.example.code_challenge

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.code_challenge.fragment.ResultFragment
import com.example.code_challenge.repository.Repository
import com.example.code_challenge.repository.RepositoryImpl


class MainActivity : AppCompatActivity(), Abs {

    private lateinit var repositoryImpl: RepositoryImpl
    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoryImpl = RepositoryImpl(this)

        viewPager = findViewById(R.id.view_pager)
        pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

        viewPager.setCurrentItem(1, false)

        viewPager.addOnPageChangeListener(onPageSelectedListener)

    }

    override fun getRepository(): Repository {
        return repositoryImpl
    }

    override fun onDestroy() {
        viewPager.removeOnPageChangeListener(onPageSelectedListener)
        super.onDestroy()
    }

    private val onPageSelectedListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {
            //Not implemented
        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            //Not implemented
        }

        override fun onPageSelected(p0: Int) {
            if (p0 == 0) {
                (pagerAdapter.getItem(p0) as ResultFragment).update()
            }
        }

    }
}
