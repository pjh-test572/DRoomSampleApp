package com.sample.pjh.gitusersearch.data.model

import com.google.gson.annotations.SerializedName

class SearchUserModel {


    @SerializedName("total_count")
    var total_count: Int = 0

    @SerializedName("incomplete_results")
    var incomplete_results: Boolean? = null

    // list 로 내려오는 데이터
    @SerializedName("items")
    var list: ArrayList<UserModel> = arrayListOf()

}