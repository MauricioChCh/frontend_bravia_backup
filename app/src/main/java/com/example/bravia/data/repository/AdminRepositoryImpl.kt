package com.example.bravia.data.repository

import com.example.bravia.data.mapper.CompanyMapper
import com.example.bravia.data.mapper.StudentMapper
import com.example.bravia.data.mapper.UserReportMapper
import com.example.bravia.data.remote.AdminRemoteDataSource
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.model.Student
import com.example.bravia.domain.model.UserReport
import com.example.bravia.domain.repository.AdminRepository
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val remoteDataSource: AdminRemoteDataSource,
    private val companyMapper: CompanyMapper,
    private val studentMapper: StudentMapper,
    private val userReportMapper: UserReportMapper,
) : AdminRepository {

    override suspend fun findAllCompanies(): List<Company> {
        val result = remoteDataSource.getAllCompanies()

        return when {
            result.isSuccess -> {
                val dtos = result.getOrNull() ?: emptyList()
                android.util.Log.d("CompanyRepositoryImpl", "DTOs recibidos: ${dtos.size}")
                dtos.forEach { dto ->
                    android.util.Log.d("CompanyRepositoryImpl", "DTO: id=${dto.id}, name=${dto.name}")
                }

                val domainList = dtos.map { dto ->
                    val domain = companyMapper.mapToDomain(dto)
                    android.util.Log.d("CompanyRepositoryImpl", "Company mapeada: id=${domain.id}, name=${domain.name}")
                    domain
                }

                android.util.Log.d("CompanyRepositoryImpl", "Total de compañías mapeadas: ${domainList.size}")
                domainList
            }
            else -> {
                val error = result.exceptionOrNull()
                android.util.Log.e("CompanyRepositoryImpl", "Error al obtener compañías: ${error?.message}")
                throw error ?: Exception("Unknown error fetching companies")
            }
        }
    }

    override suspend fun findAllStudents(): List<Student> {
        val result = remoteDataSource.getAllStudents()
        return when {
            result.isSuccess -> {
                val dtos = result.getOrNull() ?: emptyList()
                android.util.Log.d("AdminRepositoryImpl", "DTOs recibidos: ${dtos.size}")
                dtos.forEach { dto ->
                    android.util.Log.d("AdminRepositoryImpl", "DTO: id=${dto.id}, name=${dto.userInput.firstName}")
                }

                val domainList = dtos.map { dto ->
                    val domain = studentMapper.mapToDomain(dto)
                    android.util.Log.d("AdminRepositoryImpl", "Student mapeado: id=${domain.id}, name=${dto.userInput.firstName}")
                    domain
                }

                android.util.Log.d("CompanyRepositoryImpl", "Total de compañías mapeadas: ${domainList.size}")
                domainList
            }
            else -> {
                val error = result.exceptionOrNull()
                android.util.Log.e("CompanyRepositoryImpl", "Error al obtener compañías: ${error?.message}")
                throw error ?: Exception("Unknown error fetching companies")
            }
        }

    }

    override suspend fun findAllUserReports(): List<UserReport> {
        val result = remoteDataSource.getAllUserReports()
        return when {
            result.isSuccess -> {
                val dtos = result.getOrNull() ?: emptyList()
                android.util.Log.d("AdminRepositoryImpl", "DTOs recibidos: ${dtos.size}")
                dtos.forEach { dto ->
                    android.util.Log.d("AdminRepositoryImpl", "DTO: id=${dto.id}")
                }

                val domainList = dtos.map { dto ->
                    val domain = userReportMapper.mapToDomain(dto)
                    android.util.Log.d("AdminRepositoryImpl", "Reporte mapeado: id=${domain.id}")
                    domain
                }

                android.util.Log.d("AdminRepositoryImpl", "Total de reportes mapeados: ${domainList.size}")
                domainList
            }
            else -> {
                val error = result.exceptionOrNull()
                android.util.Log.e("AdminRepositoryImpl", "Error al obtener compañías: ${error?.message}")
                throw error ?: Exception("Unknown error fetching companies")
            }
        }
    }

    override suspend fun findUserReportById(reportId: Long): UserReport {
        val result = remoteDataSource.getUserReportById(reportId)

        return when {
            result.isSuccess -> {
                val dto = result.getOrNull()
                if (dto == null) {
                    android.util.Log.e("AdminRepositoryImpl", "No se encontró el reporte con ID: $reportId")
                    throw IllegalStateException("No se encontró el reporte con ID $reportId")
                }

                android.util.Log.d("AdminRepositoryImpl", "DTO recibido: id=${dto.id}")
                val domain = userReportMapper.mapToDomain(dto)
                android.util.Log.d("AdminRepositoryImpl", "Reporte mapeado: id=${domain.id}")
                domain
            }

            else -> {
                val error = result.exceptionOrNull()
                android.util.Log.e("AdminRepositoryImpl", "Error al obtener el reporte con ID $reportId: ${error?.message}")
                throw error ?: Exception("Unknown error fetching user report with ID $reportId")
            }
        }
    }

}

