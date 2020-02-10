package com.sample.pjh.gitusersearch.data.model

open class RepoBaseModel {

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
}