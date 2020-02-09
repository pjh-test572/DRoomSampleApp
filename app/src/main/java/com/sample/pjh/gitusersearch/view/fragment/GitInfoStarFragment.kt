package com.sample.pjh.gitusersearch.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.dialog.LoadingIndicatorUtil
import com.sample.pjh.gitusersearch.common.listener.OnViewModelBaseListener
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.viewmodel.GitInfoStarViewModel
import com.sample.pjh.gitusersearch.data.viewmodel.GitUserSearchViewModel
import com.sample.pjh.gitusersearch.databinding.FragmentGitinfostarBinding
import com.sample.pjh.gitusersearch.databinding.FragmentGitusersearchBinding
import com.sample.pjh.gitusersearch.view.adapter.RepoListAdapter
import com.sample.pjh.gitusersearch.view.fragment.base.BaseFragment
import io.reactivex.disposables.CompositeDisposable


class GitInfoStarFragment : BaseFragment<FragmentGitinfostarBinding>(), OnViewModelBaseListener  {


    // -------- LOCAL VALUE --------

    private lateinit var mViewModel: GitInfoStarViewModel
    var userLogin = ""

    // -----------------------------

    ////////////////////////////////////////////////
    // ABSTRACT
    ////////////////////////////////////////////////

    override val baseTag: String = this@GitInfoStarFragment::class.java.simpleName
    override val layoutId: Int = R.layout.fragment_gitinfostar

    ////////////////////////////////////////////////


    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////

    override fun init() {
        mLoadingIndicatorUtil = LoadingIndicatorUtil(requireContext())
        mDisposable = CompositeDisposable()
        mViewModel = ViewModelProvider(this, viewModelFactory).get(GitInfoStarViewModel::class.java).apply {
            mDisposable = this@GitInfoStarFragment.mDisposable
            userLogin = this@GitInfoStarFragment.userLogin
        }
        mBinding.viewModel = mViewModel

        if (mBinding.recyclerview.itemAnimator is SimpleItemAnimator)
            (mBinding.recyclerview.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        getUserStarList()
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

    private fun getUserStarList(){
        mViewModel.getUserStarList()
        mViewModel.mUserStar.observe(this, Observer {
            mBinding.recyclerview.adapter = RepoListAdapter().apply {
                mContext = requireContext()
                listener = this@GitInfoStarFragment
                setList(it)
            }
        })
    }


    companion object {
        @JvmStatic
        fun newInstance(): GitInfoStarFragment {
            return GitInfoStarFragment()
        }
    }
}