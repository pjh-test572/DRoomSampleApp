package com.sample.pjh.gitusersearch.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import com.sample.pjh.gitusersearch.common.listener.OnViewModelBaseListener
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.viewmodel.GitInfoRepoViewModel
import com.sample.pjh.gitusersearch.data.viewmodel.GitInfoStarViewModel
import com.sample.pjh.gitusersearch.data.viewmodel.GitUserSearchViewModel
import com.sample.pjh.gitusersearch.databinding.FragmentGitinforepoBinding
import com.sample.pjh.gitusersearch.databinding.FragmentGitusersearchBinding
import com.sample.pjh.gitusersearch.view.adapter.RepoListAdapter
import com.sample.pjh.gitusersearch.view.fragment.base.BaseFragment
import io.reactivex.disposables.CompositeDisposable


class GitInfoRepoFragment : BaseFragment<FragmentGitinforepoBinding>() , OnViewModelBaseListener {


    // -------- LOCAL VALUE --------

    private lateinit var mViewModel: GitInfoRepoViewModel
    var userLogin = ""

    // -----------------------------

    ////////////////////////////////////////////////
    // ABSTRACT
    ////////////////////////////////////////////////

    override val baseTag: String = this@GitInfoRepoFragment::class.java.simpleName
    override val layoutId: Int = R.layout.fragment_gitinforepo

    ////////////////////////////////////////////////


    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////

    override fun init() {
        mLoadingIndicatorUtil = LoadingIndicatorUtil(requireContext())
        mDisposable = CompositeDisposable()
        mViewModel = ViewModelProvider(this, viewModelFactory).get(GitInfoRepoViewModel::class.java).apply {
            mDisposable = this@GitInfoRepoFragment.mDisposable
            userLogin = this@GitInfoRepoFragment.userLogin
        }
        mBinding.viewModel = mViewModel

        if (mBinding.recyclerview.itemAnimator is SimpleItemAnimator)
            (mBinding.recyclerview.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        getUserRepoList()
    }

    override fun onClick(type: Int, position: Int, value: Any?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        try{
            Db.destroyInstance()
        }catch (e : Exception){

        }
    }

    ////////////////////////////////////////////////


    private fun getUserRepoList(){
        mViewModel.getUserRepoList()
        mViewModel.mUserRepos.observe(this, Observer {
            mBinding.recyclerview.adapter = RepoListAdapter().apply {
                mContext = requireContext()
                listener = this@GitInfoRepoFragment
                setList(it)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(): GitInfoRepoFragment {
            return GitInfoRepoFragment()
        }
    }
}