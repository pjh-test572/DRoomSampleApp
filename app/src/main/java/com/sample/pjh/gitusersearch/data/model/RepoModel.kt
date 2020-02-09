package com.sample.pjh.gitusersearch.data.model

class RepoModel {
    var id = 0
    var node_id = ""
    var name = ""
    var full_name = ""
    var private = false

    var owner : UserModel = UserModel()

    var description = ""
    var fork = false
    var url = ""
    var created_at = ""
    var updated_at = ""
    var pushed_at = ""
    var clone_url = ""
    var svn_url = ""
    var homepage = ""
    var size = 0
    var stargazers_count = 0
    var watchers_count = 0
    var language = ""
    var has_issues = false
    var has_projects = false
    var has_downloads = false
    var has_wiki = false
    var has_pages = false
    var forks_count = 0
    var mirror_url : Any? = null
    var archived = false
    var disabled = false
    var open_issues_count = 0
    var forks = 0
    var open_issues = 0
    var watchers = 0
    var default_branch = ""
    var temp_clone_token : Any? = null
    var network_count = 0
    var subscribers_count = 0

    var license : License = License()
    override fun toString(): String {
        return "RepoModel(id=$id, node_id='$node_id', name='$name', full_name='$full_name', private=$private, owner=$owner, description='$description', fork=$fork, url='$url', created_at='$created_at', updated_at='$updated_at', pushed_at='$pushed_at', clone_url='$clone_url', svn_url='$svn_url', homepage='$homepage', size=$size, stargazers_count=$stargazers_count, watchers_count=$watchers_count, language='$language', has_issues=$has_issues, has_projects=$has_projects, has_downloads=$has_downloads, has_wiki=$has_wiki, has_pages=$has_pages, forks_count=$forks_count, mirror_url=$mirror_url, archived=$archived, disabled=$disabled, open_issues_count=$open_issues_count, forks=$forks, open_issues=$open_issues, watchers=$watchers, default_branch='$default_branch', temp_clone_token=$temp_clone_token, network_count=$network_count, subscribers_count=$subscribers_count, license=$license)"
    }


}