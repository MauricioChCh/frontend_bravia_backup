package com.example.bravia.domain.model

/**
 * Data class que representa la sesión actual del usuario
 */
data class UserSession(
    val userId: String,
    val username: String,
    val token: String,
    val role: UserRole,
    val authorities: List<String>
) {
    /**
     * Verifica si el usuario tiene un rol específico
     */
    fun hasRole(role: UserRole): Boolean {
        return this.role == role
    }

    /**
     * Verifica si el usuario tiene una autoridad específica
     */
    fun hasAuthority(authority: String): Boolean {
        return authorities.contains(authority)
    }
}