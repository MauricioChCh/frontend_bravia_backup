package com.example.bravia.domain.error

sealed class DomainError(val message: String) {
    class InterestNotFound(id: Int) : DomainError("Interest with id $id not found")
    class InternshipNotFound(id: Long) : DomainError("Internship with id $id not found")
    class GeneralError(errorMessage: String) : DomainError(errorMessage)
}