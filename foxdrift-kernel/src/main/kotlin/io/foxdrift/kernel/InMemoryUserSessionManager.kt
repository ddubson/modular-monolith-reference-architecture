package io.foxdrift.kernel

class InMemoryUserSessionManager : UserSessionManager {
    override fun loginRequest(username: String, password: String): LoginActions {
        return LoginActions(LoginRequest())
    }
}