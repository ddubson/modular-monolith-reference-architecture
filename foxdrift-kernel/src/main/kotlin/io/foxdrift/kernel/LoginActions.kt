package io.foxdrift.kernel

import java.util.*

class LoginActions(val loginRequest: LoginRequest,
                   val loginSuccess: (UUID) -> Unit = { _ -> },
                   val loginDenied: (String) -> Unit = { _ -> }) {
    fun onLoggedIn(loginSuccess: (UUID) -> Unit): LoginActions {
        return LoginActions(this.loginRequest, loginSuccess, this.loginDenied)
    }

    fun onLoginDenied(loginDenied: (String) -> Unit): LoginActions {
        return LoginActions(this.loginRequest, this.loginSuccess, loginDenied)
    }

    fun login(): LoginResponse {
        return if(loginRequest.username != "hello" && loginRequest.password != "world") {
            AccessDeniedLoginResponse()
        } else {
            SuccessfulLoginResponse()
        }
    }
}