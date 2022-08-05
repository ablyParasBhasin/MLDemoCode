package com.fatbit.yoyumm.delivery.activity.common

class MyResponse {
    var tag: Int? = -1
        private set

    var error: Exception? = null
        private set

   constructor(t: Exception) {
       error = t
   }

    constructor(tag: Int?,  t: Exception) {
        this.tag = tag
        error = t
    }
}