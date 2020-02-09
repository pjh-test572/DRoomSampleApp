package com.sample.pjh.gitusersearch.view.activity

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import com.sample.pjh.gitusersearch.common.type.ActType
import com.sample.pjh.gitusersearch.view.activity.base.ContentActivity
import com.sample.pjh.gitusersearch.view.adapter.SectionsPagerAdapter
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.sample.pjh.gitusersearch.view.fragment.base.MyFragmentNavigator


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
        val navController = findNavController(R.id.nav_host_fragment)

        // get fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!

        // setup custom navigator
        val navigator = MyFragmentNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host_fragment)
        navController.navigatorProvider.addNavigator(navigator)

        // set navigation graph
        navController.setGraph(R.navigation.nav_graph)
    }

   /* override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }*/

    ////////////////////////////////////////////////

}
