package com.sample.pjh.gitusersearch.view.activity

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import com.sample.pjh.gitusersearch.common.type.ActType
import com.sample.pjh.gitusersearch.view.activity.base.ContentActivity
import com.sample.pjh.gitusersearch.view.adapter.SectionsPagerAdapter

class MainActivity : ContentActivity() {



    // -------- LOCAL VALUE --------
    // -----------------------------

    ////////////////////////////////////////////////
    // ABSTRACT
    ////////////////////////////////////////////////

    override fun getViewType(): ActType = ActType.MAIN
    override fun getBaseTag(): String = getViewType().tag
    override fun getLayoutId(): Int = R.layout.activity_main

    ////////////////////////////////////////////////


    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////

    override fun init() {
        mLoadingIndicatorUtil = LoadingIndicatorUtil(this)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager).apply {
            this@apply.mLoadingIndicatorUtil = this@MainActivity.mLoadingIndicatorUtil
        }
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(sectionsPagerAdapter)
    }


    ////////////////////////////////////////////////

}
