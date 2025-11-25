package com.konnettoco.konnetto.data.remote.dto.auth.register

import com.google.gson.annotations.SerializedName

data class CheckEmailResponse(

	@field:SerializedName("data")
	val data: DataCheckEmail? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class DataCheckEmail(

	@field:SerializedName("is_available")
	val isAvailable: Boolean? = null
)
