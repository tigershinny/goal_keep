package com.adidas.sports.goal.net


import java.io.Serializable
import java.util.*

class BaseResponse<T> : Serializable {
    var data: T? = null
    var code: Int = 0
    var message: String? = null
    var msg: Array<String>? = null

    val isOK: Boolean
        get() = code == 0

    val isNoData: Boolean
        get() = data == null

    override fun toString(): String {
        return "BaseResponse " +
                "data=" + data +
                ", code=" + code +
                ", message=" + message +
                ", msg=" + Arrays.toString(msg)
    }
}
