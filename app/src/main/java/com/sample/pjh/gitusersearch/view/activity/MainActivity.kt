package com.sample.pjh.gitusersearch.view.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.type.ActType
import com.sample.pjh.gitusersearch.data.retrofit.ServerResponseCallback
import com.sample.pjh.gitusersearch.data.retrofit.server.GitServer
import com.sample.pjh.gitusersearch.view.activity.base.ContentActivity
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
    override fun getLayoutId(): Int = R.layout.activity_scrolling

    ////////////////////////////////////////////////


    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////

    override fun init() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        mDisposable = CompositeDisposable()
        mDisposable.add(GitServer.getSearchUser("test",1,
            ServerResponseCallback(nextTask = {

            },completeTask = {

            },failedTask = {e,t ->

            })
        ))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    ////////////////////////////////////////////////

}
