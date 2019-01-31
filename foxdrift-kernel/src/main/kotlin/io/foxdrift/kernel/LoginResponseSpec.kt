package io.foxdrift.kernel

interface LoginResponseSpec<T> {
    fun mapLoginSuccess(map: (SuccessfulLoginResponse) -> T): LoginResponseSpec<T>

    fun mapLoginDenied(map: (AccessDeniedLoginResponse) -> T): LoginResponseSpec<T>

    fun collectResponse(): T
}