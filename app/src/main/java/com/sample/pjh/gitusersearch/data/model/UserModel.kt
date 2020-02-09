package com.sample.pjh.gitusersearch.data.model


import com.google.gson.annotations.Expose

class UserModel {
    var login = ""
    var id = 0
    var node_id = ""
    var avatar_url = ""
    var gravatar_id = ""
    var url = ""
    var html_url = ""
    var followers_url = ""
    var following_url = ""
    var gists_url = ""
    var starred_url = ""
    var subscriptions_url = ""
    var organizations_url = ""
    var repos_url = ""
    var events_url = ""
    var received_events_url = ""
    var type = ""
    var site_admin = false
    var score : Float = 0f

    var name : String? = ""
    var company : String? = ""
    var blog : String? = ""
    var location : String? = ""
    var email : String? = ""
    var hireable : String? = ""
    var bio : String? = ""
    var public_repos = 0
    var public_gists = 0
    var followers = 0
    var following = 0
    var created_at : String? = ""
    var updated_at : String? = ""

    @Expose
    var isFav : Boolean = false

    override fun toString(): String {
        return "UserModel(login='$login', id=$id, node_id='$node_id', avatar_url='$avatar_url', gravatar_id='$gravatar_id', url='$url', html_url='$html_url', followers_url='$followers_url', following_url='$following_url', gists_url='$gists_url', starred_url='$starred_url', subscriptions_url='$subscriptions_url', organizations_url='$organizations_url', repos_url='$repos_url', events_url='$events_url', received_events_url='$received_events_url', type='$type', site_admin=$site_admin, score=$score, name=$name, company=$company, blog=$blog, location=$location, email=$email, hireable=$hireable, bio=$bio, public_repos=$public_repos, public_gists=$public_gists, followers=$followers, following=$following, created_at=$created_at, updated_at=$updated_at, isFav=$isFav)"
    }


}