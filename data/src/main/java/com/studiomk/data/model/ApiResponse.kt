package com.studiomk.data.model

data class ApiResponse(
    val data: Map<String, Exchange>,
    val status: Status
)