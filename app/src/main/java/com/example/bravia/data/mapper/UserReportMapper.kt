package com.example.bravia.data.mapper

import com.example.bravia.data.remote.dto.StudentResponseDTO
import com.example.bravia.data.remote.dto.UserReportResponseDTO
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.UserReport
import javax.inject.Inject

class UserReportMapper @Inject constructor(){
    fun mapToDomain(dto: UserReportResponseDTO): UserReport {
        return UserReport(
             id = dto.id,
            reporterId= dto.reporterId,
            reporterName= dto.reporterName,
            reportedUserId= dto.reportedUserId,
            reportedUserName= dto.reportedUserName,
            description= dto.description
        )
    }
}