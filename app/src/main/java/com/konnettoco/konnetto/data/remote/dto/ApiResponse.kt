package com.konnettoco.konnetto.data.remote.dto

data class ApiResponse<T>(
    val data: T?,
    val timestamp: String?
)
