package com.example.bravia.domain.error

sealed class DomainError(val message: String) {
    class InternshipNotFound(id: Long) : DomainError("Internship with id $id not found")
    class GeneralError(errorMessage: String) : DomainError(errorMessage)
}