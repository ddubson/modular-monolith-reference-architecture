package io.foxdrift.kernel

interface UserSessionManager {
    fun loginRequest(username: String, password: String): LoginActions
}