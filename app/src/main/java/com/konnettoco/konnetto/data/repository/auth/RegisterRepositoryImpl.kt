package com.konnettoco.konnetto.data.repository.auth

import com.konnettoco.konnetto.data.remote.api.AuthApiService
import com.konnettoco.konnetto.data.remote.dto.auth.register.RegisterRequest
import com.konnettoco.konnetto.domain.model.RegisterResult
import com.konnettoco.konnetto.domain.repository.auth.RegisterRepository
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val api: AuthApiService
): RegisterRepository {

    override suspend fun register(
        fullName: String,
        email: String,
        username: String,
        password: String
    ): Result<RegisterResult> {
        return try {
            val response = api.register(
                RegisterRequest(
                    fullName = fullName,
                    email = email,
                    username = username,
                    password = password
                )
            )

            // Ambil Data dari ApiResponse -> Data
            val domain = response.data?.let {
                RegisterResult(
                    userId = it.userId ?: "",
                    otpExpiredAt = it.otpExpiredAt ?: ""
                )
            } ?: return Result.failure(Exception("SERVER_DATA_NULL"))

            Result.success(domain)

        } catch (e: HttpException) {
            val code = e.code()
            val message = when(code) {
                409 -> "Email or username already taken, please choose another email or username."
                400 -> "Some of the information you entered is not valid, please try again.."
                500 -> "An error occurred on our server, please try again later..."
                else -> "An error occurred. Code: $code"
            }
            Result.failure(Exception(message))

        } catch (e: UnknownHostException) {
            Result.failure(Exception("No internet connection"))
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("Connection timed out, please try again"))
        } catch (e: Exception) {
            Result.failure(Exception("An error occurred, please try again."))
        }
    }

    override suspend fun checkUsername(username: String): Result<Unit> {
        return try {
            val response = api.checkUsername(username)
            if (response.data?.isAvailable == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Username already taken"))
            }
        } catch (e: UnknownHostException) {
            Result.failure(Exception("No internet connection"))
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("Connection timed out, please try again"))
        } catch (e: Exception) {
            Result.failure(Exception("An error occurred, please try again."))
        }
    }

    override suspend fun checkEmail(email: String): Result<Unit> {
        return try {
            val response = api.checkEmail(email)
            if (response.data?.isAvailable == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("This email already taken"))
            }
        } catch (e: UnknownHostException) {
            Result.failure(Exception("No internet connection"))
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("Connection timed out, please try again"))
        } catch (e: Exception) {
            Result.failure(Exception("An error occurred, please try again."))
        }
    }
}
