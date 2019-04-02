package com.adidas.sports.goal.exception

data class ServerException(
        val code: Int,
        override val message: String): RuntimeException(message)

data class AccessThrowable(
        val code: Int,
        override val message: String): Throwable(message)

object ERROR {

    const val UNKNOWN = 1000

    const val ERROR_PARSE = 1001

    const val ERROR_NETWORK = 1002

    const val ERROR_HTTP = 1003

    const val ERROR_SSL = 1005

    const val ERROR_SQL = 1006

    const val SOCKET_ERROR = 1007

    const val ERROR_TOKEN_EXPIRED = 11

    const val ERROR_NEED_UPDATE = 12
}