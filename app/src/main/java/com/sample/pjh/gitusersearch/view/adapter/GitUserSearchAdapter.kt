package com.sample.pjh.gitusersearch.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sample.pjh.gitusersearch.R
import com.sample.pjh.gitusersearch.common.listener.OnViewModelBaseListener
import com.sample.pjh.gitusersearch.data.db.Db
import com.sample.pjh.gitusersearch.data.db.entity.GitUserEntity
import com.sample.pjh.gitusersearch.data.model.UserModel
import com.sample.pjh.gitusersearch.databinding.LayoutItemGituserBinding
import com.sample.pjh.gitusersearch.view.adapter.base.BaseViewHolder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class GitUserSearchAdapter : RecyclerView.Adapter<BaseViewHolder<out ViewDataBinding, UserModel>>() {

    // -------- LOCAL VALUE --------
    lateinit var mContext : Context
    lateinit var listener : OnViewModelBaseListener
    lateinit var mDisposable : CompositeDisposable
    lateinit var db : Db

    var mList = mutableListOf<UserModel>()

    // -----------------------------


    ////////////////////////////////////////////////
    // OVERRIDE
    ////////////////////////////////////////////////


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<out ViewDataBinding, UserModel> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GitUserSearchItemHolder(DataBindingUtil.inflate(layoutInflater, R.layout.layout_item_gituser, parent, false) as LayoutItemGituserBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<out ViewDataBinding, UserModel>, position: Int) =
        (holder as GitUserSearchItemHolder).bind(mList[position],position)

    override fun getItemCount(): Int = mList.size

    ////////////////////////////////////////////////


    ////////////////////////////////////////////////

    fun setList(list : MutableList<UserModel>){
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    ////////////////////////////////////////////////



    ////////////////////////////////////////////////
    // inner holder
    ////////////////////////////////////////////////

    inner class GitUserSearchItemHolder(binding: LayoutItemGituserBinding) : BaseViewHolder<LayoutItemGituserBinding, UserModel>(binding.root) {
        override fun bind(item: UserModel, position: Int) {
            binding.item = item
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
            binding.executePendingBindings()
        }
    }

    ////////////////////////////////////////////////

}