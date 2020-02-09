package com.sample.pjh.gitusersearch.view.fragment

import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import com.sample.pjh.gitusersearch.databinding.FragmentMainBinding
import com.sample.pjh.gitusersearch.view.activity.base.BaseActivity
import com.sample.pjh.gitusersearch.view.adapter.SectionsPagerAdapter
import com.sample.pjh.gitusersearch.view.fragment.base.BaseNavHostFragment
import com.sample.pjh.gitusersearch.view.fragment.base.MyFragmentNavigator

class MainFragment : BaseNavHostFragment<FragmentMainBinding>(){

    override val baseTag: String = this@MainFragment::class.java.simpleName
    override val layoutId: Int = R.layout.fragment_main

    override fun init() {
        mLoadingIndicatorUtil = LoadingIndicatorUtil(requireContext())

        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), (requireContext() as BaseActivity).supportFragmentManager).apply {
            this@apply.mLoadingIndicatorUtil = this@MainFragment.mLoadingIndicatorUtil
        }
        mBinding.viewPager.adapter = sectionsPagerAdapter
        mBinding.tabs.setupWithViewPager(mBinding.viewPager)
        mBinding.viewPager.addOnPageChangeListener(sectionsPagerAdapter)
    }

    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
        //return super.createFragmentNavigator()
        return MyFragmentNavigator(requireContext(), childFragmentManager,R.id.nav_host_fragment)
    }
}