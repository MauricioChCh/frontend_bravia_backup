package com.example.bravia.domain.repository

import com.example.bravia.domain.model.AuthResult

/**
 * Repository interface for authentication-related operations.
 * Defines methods for user authentication and session management.
 */
interface AuthRepository {
    /**
     * Authenticates a user with the provided credentials.
     *
     * @param email The user's email
     * @param password The user's password
     * @return [Result] indicating success or failure of the authentication
     */
    suspend fun login(email: String, password: String): Result<AuthResult>

    /**
     * Ends the user's authenticated session.
     *
     * @return [Result] indicating success or failure of the logout operation
     */
    suspend fun logout(): Result<Unit>

    /**
     * Checks if the user is currently authenticated.
     *
     * @return [Result] wrapping a boolean indicating authentication status
     */
    suspend fun isAuthenticated(): Result<Boolean>

    /**
     * Retrieves the current user's email if authenticated.
     *
     * @return [Result] wrapping the authenticated email or null
     */
    suspend fun getCurrentUser(): Result<String?>
}