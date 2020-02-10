package com.sample.pjh.gitusersearch.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.listener.OnViewModelBaseListener
import com.sample.pjh.gitusersearch.data.model.RepoModel
import com.sample.pjh.gitusersearch.databinding.LayoutItemRepoBinding
import com.sample.pjh.gitusersearch.view.adapter.base.BaseViewHolder


class RepoListAdapter : RecyclerView.Adapter<BaseViewHolder<out ViewDataBinding, RepoModel>>() {

    // -------- LOCAL VALUE --------
    lateinit var mContext : Context
    lateinit var listener : OnViewModelBaseListener

    var mList = mutableListOf<RepoModel>()

    // -----------------------------


    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<out ViewDataBinding, RepoModel> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RepoItemHolder(DataBindingUtil.inflate(layoutInflater, R.layout.layout_item_repo, parent, false) as LayoutItemRepoBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<out ViewDataBinding, RepoModel>, position: Int) =
        (holder as RepoItemHolder).bind(mList[position],position)

    override fun getItemCount(): Int = mList.size

    ////////////////////////////////////////////////


    ////////////////////////////////////////////////

    fun setList(list : MutableList<RepoModel>){
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    ////////////////////////////////////////////////



    ////////////////////////////////////////////////
    // inner holder
    ////////////////////////////////////////////////

    inner class RepoItemHolder(binding: LayoutItemRepoBinding) : BaseViewHolder<LayoutItemRepoBinding, RepoModel>(binding.root) {
        override fun bind(item: RepoModel, position: Int) {
            binding.item = item
            binding.executePendingBindings()
            /*binding.item = item
            binding.imageviewUserAvatar.setImageURI(item.avatar_url)
            mDisposable.add(Observable.fromCallable<List<GitUserEntity>> {
                db.gitUserDao().get(item.id)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->
                    if(result.isNotEmpty()) {
                        item.isFav = true
                        binding.imageviewUserFav.setImageResource(R.drawable.big_star_yellow)
                    }else {
                        item.isFav = false
                        binding.imageviewUserFav.setImageResource(R.drawable.big_star_grey)
                    }
                    binding.executePendingBindings()
                })
            binding.imageviewUserFav.setOnClickListener { if(::listener.isInitialized)listener.onClick(R.id.imageview_user_fav, position, item) }
            binding.constraintLayout.setOnClickListener { if(::listener.isInitialized)listener.onClick(R.id.constraintLayout, position, item) }
            binding.executePendingBindings()*/
        }
    }

    ////////////////////////////////////////////////

}