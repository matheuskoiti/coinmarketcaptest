package com.studiomk.data.model

data class ApiResponse<T>(
    val data: T,
    val status: Status
)