package com.example.bravia.domain.model

class UserReport (
    val id: Long,
    val reporterId: Long,
    val reporterName: String,
    val reportedUserId: Long,
    val reportedUserName: String,
    val description: String
)