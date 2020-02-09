package com.sample.pjh.gitusersearch.view.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.type.ActType
import com.sample.pjh.gitusersearch.data.retrofit.ServerResponseCallback
import com.sample.pjh.gitusersearch.data.retrofit.server.GitServer
import com.sample.pjh.gitusersearch.view.activity.base.ContentActivity
import com.sample.pjh.gitusersearch.view.adapter.SectionsPagerAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_scrolling.*

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
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(sectionsPagerAdapter)
    }


    ////////////////////////////////////////////////

}
