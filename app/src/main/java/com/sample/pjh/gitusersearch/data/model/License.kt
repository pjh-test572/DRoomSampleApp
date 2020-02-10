package com.sample.pjh.gitusersearch.data.model

class License {
    var key = ""
    var name = ""
    var spdx_id = ""
    var url = ""
    var node_id  = ""

    override fun toString(): String {
        return "License(key='$key', name='$name', spdx_id='$spdx_id', url='$url', node_id='$node_id')"
    }

}