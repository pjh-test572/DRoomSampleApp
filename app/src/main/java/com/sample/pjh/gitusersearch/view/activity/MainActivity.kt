package com.sample.pjh.gitusersearch.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.Info
import com.sample.pjh.gitusersearch.common.type.ActType
import com.sample.pjh.gitusersearch.common.type.BuildType
import com.sample.pjh.gitusersearch.common.util.CustomLog
import com.sample.pjh.gitusersearch.view.activity.base.ContentActivity
import com.sample.pjh.gitusersearch.view.fragment.base.MyFragmentNavigator
import com.sample.pjh.gitusersearch.view.navigation.setupWithNavController


class MainActivity : ContentActivity() {

    // -------- LOCAL VALUE --------
    private var currentNavController: LiveData<NavController>? = null
    // -----------------------------

    ////////////////////////////////////////////////
    // ABSTRACT
    ////////////////////////////////////////////////

    override fun getViewType(): ActType = ActType.MAIN
    override fun getBaseTag(): String = getViewType().tag
    override fun getLayoutId(): Int = when(Info.BUILD_TYPE){
        BuildType.NAV -> R.layout.activity_navimain
        else -> R.layout.activity_main
    }

    ////////////////////////////////////////////////


    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////

    override fun init(saveInstanceState: Bundle?) {
        when(Info.BUILD_TYPE){
            BuildType.NAV ->{
                if (saveInstanceState == null) {
                    setupBottomNavigationBar()
                } // Else, need to wait for onRestoreInstanceState
            }
            else->{
                (this as AppCompatActivity).supportActionBar!!.hide()
                val navController = findNavController(R.id.nav_host_fragment)
                // get fragment
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!
                // setup custom navigator
                val navigator = MyFragmentNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host_fragment)
                navController.navigatorProvider.addNavigator(navigator)
                // set navigation graph
                navController.setGraph(R.navigation.nav_graph)
            }
        }
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        if(CustomLog.flag)CustomLog.L("onRestoreInstanceState","savedInstanceState")
        when(Info.BUILD_TYPE){
            BuildType.NAV -> {
                if(CustomLog.flag)CustomLog.L("onRestoreInstanceState","NAV")
                setupBottomNavigationBar()
            }
        }

    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val navGraphIds = listOf(R.navigation.nav_search, R.navigation.nav_fav, R.navigation.nav_newlyword)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            if(CustomLog.flag)CustomLog.L("MainActivity","controller")
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    ////////////////////////////////////////////////

}
