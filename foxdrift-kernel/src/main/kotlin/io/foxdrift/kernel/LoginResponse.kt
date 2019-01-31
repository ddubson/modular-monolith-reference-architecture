package io.foxdrift.kernel

import java.util.*

interface LoginResponse
class AccessDeniedLoginResponse(val reason: String) : LoginResponse
class SuccessfulLoginResponse(val sessionId: UUID) : LoginResponse
