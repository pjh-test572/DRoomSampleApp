package com.sample.pjh.gitusersearch.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import com.sample.pjh.gitusersearch.view.fragment.GitUserFavoriteFragment
import com.sample.pjh.gitusersearch.view.fragment.GitUserSearchFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT), ViewPager.OnPageChangeListener{
    lateinit var gitUserFavFrag : GitUserFavoriteFragment
    lateinit var gitUserSearchFrag : GitUserSearchFragment
    lateinit var mLoadingIndicatorUtil : LoadingIndicatorUtil

    override fun getItem(position: Int): Fragment = when(position){
        0 -> GitUserSearchFragment.newInstance().apply {
            gitUserSearchFrag = this
            this@apply.mLoadingIndicatorUtil =  this@SectionsPagerAdapter.mLoadingIndicatorUtil
        }
        else -> GitUserFavoriteFragment.newInstance().apply {
            gitUserFavFrag = this
            this@apply.mLoadingIndicatorUtil =  this@SectionsPagerAdapter.mLoadingIndicatorUtil
        }
    }

    override fun getPageTitle(position: Int): CharSequence = when(position){
        0->context.getString(R.string.main_tab1)
        else->context.getString(R.string.main_tab2)
    }

    override fun getCount(): Int {
        return 2
    }

    override fun onPageScrollStateChanged(state: Int) {  }
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {  }
    override fun onPageSelected(position: Int) {
        if(::gitUserSearchFrag.isInitialized && position == 0) gitUserSearchFrag.onPageSelected(position)
        if(::gitUserFavFrag.isInitialized && position == 1) gitUserFavFrag.onPageSelected(position)
    }
}