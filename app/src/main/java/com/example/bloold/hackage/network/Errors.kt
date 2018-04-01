package com.example.bloold.hackage.network

import java.lang.RuntimeException

/**
 * Created by dmitry on 20.11.17.
 */
class Error(val message: String? = "Неизвестная ошибка")

class NetworkError(val error: String?
) : RuntimeException(error) {
    companion object ErrorCodes {
        val CODE_INCORRECT_TOKEN = 417
        val CODE_INCORRECT_DATA_FORMAT = 411
        val CODE_ADDRESS_NOT_EXISTS = 414
        val CODE_USER_NOT_AUTHORIZED = 403
        val CODE_USER_NOT_ACTIVATED = 412
        val CODE_USER_ALREADY_REGISTERED = 413
        val CODE_SMS_SENT_ERROR = 420
        val CODE_THIS_USER_NOT_REGISTERED = 415
    }

    override val message: String?
        get() = error
}