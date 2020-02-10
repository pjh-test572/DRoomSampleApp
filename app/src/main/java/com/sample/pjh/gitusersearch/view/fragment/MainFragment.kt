package com.sample.pjh.gitusersearch.view.fragment

import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import com.sample.pjh.gitusersearch.databinding.FragmentMainBinding
import com.sample.pjh.gitusersearch.view.activity.base.BaseActivity
import com.sample.pjh.gitusersearch.view.adapter.MainPagerAdapter
import com.sample.pjh.gitusersearch.view.fragment.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(){

    override val baseTag: String = this@MainFragment::class.java.simpleName
    override val layoutId: Int = R.layout.fragment_main

    override fun init() {
        mLoadingIndicatorUtil = LoadingIndicatorUtil(requireContext())

        val sectionsPagerAdapter = MainPagerAdapter(requireContext(), (requireContext() as BaseActivity).supportFragmentManager).apply {
            this@apply.mLoadingIndicatorUtil = this@MainFragment.mLoadingIndicatorUtil
        }
        mBinding.viewPager.adapter = sectionsPagerAdapter
        mBinding.tabs.setupWithViewPager(mBinding.viewPager)
        mBinding.viewPager.addOnPageChangeListener(sectionsPagerAdapter)
    }

}