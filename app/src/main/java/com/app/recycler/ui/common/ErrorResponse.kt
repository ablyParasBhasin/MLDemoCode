package com.fatbit.yoyumm.delivery.activity.common

data class ErrorResponse(
    var msg:String,
    var type:Int,
    var error:Throwable?
)

