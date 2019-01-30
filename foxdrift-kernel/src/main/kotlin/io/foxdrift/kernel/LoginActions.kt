package io.foxdrift.kernel

import java.util.*

class LoginActions(val loginRequest: LoginRequest) {

    fun onLoggedIn(loginSuccess: (UUID) -> Unit): LoginActions {
        return this
    }

    fun onLoginDenied(loginDenied: (String) -> Unit): LoginActions {
        return this
    }

    fun login() {

    }
}