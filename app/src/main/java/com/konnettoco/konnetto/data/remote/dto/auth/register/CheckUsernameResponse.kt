package com.konnettoco.konnetto.data.remote.dto.auth.register

import com.google.gson.annotations.SerializedName

data class CheckUsernameResponse(

	@field:SerializedName("data")
	val data: DataUsername? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class DataUsername(

	@field:SerializedName("is_available")
	val isAvailable: Boolean? = null
)
