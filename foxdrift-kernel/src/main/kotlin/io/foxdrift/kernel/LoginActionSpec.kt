package io.foxdrift.kernel

import java.util.*

interface LoginActionSpec<T> {
    fun onLoggedIn(loginSuccess: (UUID) -> Unit): LoginActionSpec<T>

    fun onLoginDenied(loginDenied: (String) -> Unit): LoginActionSpec<T>

    fun login(): T
}

