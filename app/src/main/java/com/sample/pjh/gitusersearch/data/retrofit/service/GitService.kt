package com.sample.pjh.gitusersearch.data.retrofit.service

import com.sample.pjh.gitusersearch.common.Info
import com.sample.pjh.gitusersearch.data.model.RepoModel
import com.sample.pjh.gitusersearch.data.model.SearchUserModel
import com.sample.pjh.gitusersearch.data.model.UserModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitService {

    @GET("/search/users")
    fun getSearchUser(@Query("q") q: String, @Query("page") page : Int, @Query("per_page") per_page : Int = Info.PAGE_PER_ITEM): Observable<SearchUserModel>


    @GET("/users/{user}")
    fun getUserInfo(@Path("user") user: String): Observable<UserModel>


    @GET("/users/{user}/repos")
    fun getUserRepos(@Path("user") user: String): Observable<ArrayList<RepoModel>>


    @GET("/users/{user}/starred")
    fun getUserStars(@Path("user") user: String): Observable<ArrayList<RepoModel>>

}