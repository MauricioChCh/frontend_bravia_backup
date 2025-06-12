package com.example.bravia.domain.model

/**
 * Enum que define los diferentes tipos de roles de usuario en la aplicación
 */
enum class UserRole(val authority: String, val startDestination: String) {
    STUDENT("ROLE_STUDENT", "home"),
    BUSINESS("ROLE_COMPANY", "businessHome"),
    ADMIN("ROLE_ADMIN", "reportList");

    companion object {
        /**
         * Obtiene el rol del usuario basado en las autoridades
         * @param authorities Lista de autoridades del usuario
         * @return UserRole correspondiente o STUDENT por defecto
         */
        fun fromAuthorities(authorities: List<String>): UserRole {
            return when {
                authorities.contains(ADMIN.authority) -> ADMIN
                authorities.contains(BUSINESS.authority) -> BUSINESS
                authorities.contains(STUDENT.authority) -> STUDENT
                else -> STUDENT // Por defecto
            }
        }

        /**
         * Obtiene el rol basado en el string de autoridad
         */
        fun fromAuthority(authority: String): UserRole? {
            return values().find { it.authority == authority }
        }
    }
}