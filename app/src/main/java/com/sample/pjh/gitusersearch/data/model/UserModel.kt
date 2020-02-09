package com.sample.pjh.gitusersearch.data.model


import com.google.gson.annotations.Expose
import com.sample.pjh.gitusersearch.data.db.entity.GitUserEntity

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


    constructor()

    constructor(
        login: String,
        id: Int,
        node_id: String,
        avatar_url: String,
        gravatar_id: String,
        url: String,
        html_url: String,
        followers_url: String,
        following_url: String,
        gists_url: String,
        starred_url: String,
        subscriptions_url: String,
        organizations_url: String,
        repos_url: String,
        events_url: String,
        received_events_url: String,
        type: String,
        site_admin: Boolean,
        score: Float
    ) {
        this.login = login
        this.id = id
        this.node_id = node_id
        this.avatar_url = avatar_url
        this.gravatar_id = gravatar_id
        this.url = url
        this.html_url = html_url
        this.followers_url = followers_url
        this.following_url = following_url
        this.gists_url = gists_url
        this.starred_url = starred_url
        this.subscriptions_url = subscriptions_url
        this.organizations_url = organizations_url
        this.repos_url = repos_url
        this.events_url = events_url
        this.received_events_url = received_events_url
        this.type = type
        this.site_admin = site_admin
        this.score = score
    }


    fun gitUserEntityData() = GitUserEntity(0,login, id, node_id, avatar_url, gravatar_id, url, html_url, followers_url, following_url, gists_url, starred_url, subscriptions_url, organizations_url, repos_url, events_url, received_events_url, type, site_admin, score)

    override fun toString(): String {
        return "UserModel(login='$login', id=$id, node_id='$node_id', avatar_url='$avatar_url', gravatar_id='$gravatar_id', url='$url', html_url='$html_url', followers_url='$followers_url', following_url='$following_url', gists_url='$gists_url', starred_url='$starred_url', subscriptions_url='$subscriptions_url', organizations_url='$organizations_url', repos_url='$repos_url', events_url='$events_url', received_events_url='$received_events_url', type='$type', site_admin=$site_admin, score=$score, name=$name, company=$company, blog=$blog, location=$location, email=$email, hireable=$hireable, bio=$bio, public_repos=$public_repos, public_gists=$public_gists, followers=$followers, following=$following, created_at=$created_at, updated_at=$updated_at, isFav=$isFav)"
    }


}