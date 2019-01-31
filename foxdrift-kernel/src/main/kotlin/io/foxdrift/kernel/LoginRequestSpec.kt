package io.foxdrift.kernel

interface LoginRequestSpec<T> {
    fun loginRequest(username: String, password: String): T
}