package com.sample.pjh.gitusersearch.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.pjh.gitusersearch.data.model.UserModel
import java.io.Serializable

@Entity(tableName = "git_user_data")
data class GitUserEntity(
    @PrimaryKey(autoGenerate = true)            val idx: Int,
    @ColumnInfo(name = "g_login")               var login : String,
    @ColumnInfo(name = "g_id")                  var id : Int,
    @ColumnInfo(name = "g_node_id")             var node_id : String = "",
    @ColumnInfo(name = "g_avatar_url")          var avatar_url : String = "",
    @ColumnInfo(name = "g_gravatar_id")         var gravatar_id : String = "",
    @ColumnInfo(name = "g_url")                 var url : String = "",
    @ColumnInfo(name = "g_html_url")            var html_url : String = "",
    @ColumnInfo(name = "g_followers_url")       var followers_url : String = "",
    @ColumnInfo(name = "g_following_url")       var following_url : String = "",
    @ColumnInfo(name = "g_gists_url")           var gists_url : String = "",
    @ColumnInfo(name = "g_starred_url")         var starred_url : String = "",
    @ColumnInfo(name = "g_subscriptions_url")   var subscriptions_url : String = "",
    @ColumnInfo(name = "g_organizations_url")   var organizations_url : String = "",
    @ColumnInfo(name = "g_repos_url")           var repos_url : String = "",
    @ColumnInfo(name = "g_events_url")          var events_url : String = "",
    @ColumnInfo(name = "g_ireceived_events_url")var received_events_url : String = "",
    @ColumnInfo(name = "g_type")                var type : String = "",
    @ColumnInfo(name = "g_site_admin")          var site_admin : Boolean = false,
    @ColumnInfo(name = "g_score")               var score: Float = 0f) : Serializable{

    fun getUser():UserModel = UserModel(login, id, node_id, avatar_url, gravatar_id, url, html_url, followers_url, following_url, gists_url, starred_url, subscriptions_url, organizations_url, repos_url, events_url, received_events_url, type, site_admin, score)

}