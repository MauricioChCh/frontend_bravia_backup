package com.example.bravia.domain.error


/**
 * Sealed class representing various domain errors.
 *
 * @property message The error message associated with the domain error.
 */
sealed class DomainError(val message: String) {
    class InterestNotFound(id: Long) : DomainError("Interest with id $id not found")
    class CollegeNotFound(id: Long) : DomainError("College with id $id not found")
    class DegreeNotFound(id: Long) : DomainError("Degree with id $id not found")
    class BusinessAreaNotFound(id: Long) : DomainError("Business area with id $id not found")
    class InternshipNotFound(id: Long) : DomainError("Internship with id $id not found")
    class GeneralError(errorMessage: String) : DomainError(errorMessage)
}