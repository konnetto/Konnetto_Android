package com.konnettoco.konnetto.data.repository.auth

import com.konnettoco.konnetto.data.remote.api.AuthApiService
import com.konnettoco.konnetto.data.remote.dto.auth.login.LoginRequest
import com.konnettoco.konnetto.domain.model.LoginResult
import com.konnettoco.konnetto.domain.repository.auth.LoginRepository
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: AuthApiService
): LoginRepository {
    override suspend fun login(
        emailOrUserName: String,
        password: String
    ): Result<LoginResult> {
        return try {
            val response = apiService.login(
                LoginRequest(
                    emailOrUserName = emailOrUserName,
                    password = password
                )
            )

            val domain = response.data?.let {
                LoginResult(
                    accessToken = it.accessToken ?: "",
                    refreshToken = it.refreshToken ?: "",
                    role = it.role ?: ""
                )
            } ?: return Result.failure(Exception("SERVER_DATA_NULL"))

            Result.success(domain)
        } catch (e: HttpException) {
            val code = e.code()
            val message = when(code) {
                409 -> "Email or username do not exist."
                400 -> "Your email/username or password is incorrect, please try again.."
                500 -> "Something went wrong on our server. Please try again later."
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
}