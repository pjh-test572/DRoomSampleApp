package com.sample.pjh.gitusersearch.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.view.fragment.GitInfoRepoFragment
import com.sample.pjh.gitusersearch.view.fragment.GitInfoStarFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 * https://api.github.com/users/JakeWharton/starred
 * https://api.github.com/users/JakeWharton
 * https://api.github.com/repos/JakeWharton/ActionBarSherlock
 * https://api.github.com/user/66577/repos?page=2
 * https://api.github.com/users/JakeWharton/repos
 */
class UserInfoPageAdapter(private val context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    var userLogin = ""

    override fun getItem(position: Int): Fragment = when(position){
        0 -> GitInfoRepoFragment.newInstance().apply {
            this@apply.userLogin = this@UserInfoPageAdapter.userLogin
        }
        else -> GitInfoStarFragment.newInstance().apply {
            this@apply.userLogin = this@UserInfoPageAdapter.userLogin
        }
    }

    override fun getPageTitle(position: Int): CharSequence = when(position){
        0->context.getString(R.string.info_tab1)
        else->context.getString(R.string.info_tab2)
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItemPosition(`object`: Any): Int {
        //return super.getItemPosition(`object`)
        return PagerAdapter.POSITION_NONE
    }
}