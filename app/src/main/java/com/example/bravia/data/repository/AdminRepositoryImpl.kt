package com.example.bravia.data.repository

import com.example.bravia.data.mapper.CompanyMapper
import com.example.bravia.data.remote.AdminRemoteDataSource
import com.example.bravia.domain.model.Company
import com.example.bravia.domain.repository.AdminRepository
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val remoteDataSource: AdminRemoteDataSource,
    private val mapper: CompanyMapper
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
                    val domain = mapper.mapToDomain(dto)
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

}

