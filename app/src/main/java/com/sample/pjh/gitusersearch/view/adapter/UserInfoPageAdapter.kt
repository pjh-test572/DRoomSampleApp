package com.sample.pjh.gitusersearch.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import com.sample.pjh.gitusersearch.view.fragment.GitInfoRepoFragment
import com.sample.pjh.gitusersearch.view.fragment.GitInfoStarFragment
import com.sample.pjh.gitusersearch.view.fragment.GitUserFavoriteFragment
import com.sample.pjh.gitusersearch.view.fragment.GitUserSearchFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class UserInfoPageAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
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

}