package com.sample.pjh.gitusersearch.data.retrofit

class ServerResponseCallback<T> {

    var failedTask: (Throwable, Int) -> Unit
    var completeTask: () -> Unit
    var nextTask: (T) -> Unit


    constructor(nextTask: (T) -> Unit, completeTask: () -> Unit, failedTask: (Throwable, Int) -> Unit) {
        this.nextTask = nextTask
        this.completeTask = completeTask
        this.failedTask = failedTask
    }

}