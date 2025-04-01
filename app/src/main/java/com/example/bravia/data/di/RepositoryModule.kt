package com.example.bravia.data.di

import com.example.bravia.data.repository.BusinessAreaRepositoryImpl
import com.example.bravia.data.repository.CollegeRepositoryImpl
import com.example.bravia.data.repository.DegreeRepositoryImpl
import com.example.bravia.data.repository.InterestRepositoryImpl
import com.example.bravia.domain.repository.BusinessAreaRepository
import com.example.bravia.domain.repository.CollegeRepository
import com.example.bravia.domain.repository.DegreeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract  class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindBusinessAreaRepository(
        businessAreaRepositoryImpl: BusinessAreaRepositoryImpl
    ): BusinessAreaRepository

    @Binds
    @Singleton
    abstract fun bindCollegeRepository(
        collegeRepositoryImpl: CollegeRepositoryImpl
    ): CollegeRepository

    @Binds
    @Singleton
    abstract fun bindDegreeRepository(
        degreeRepositoryImpl: DegreeRepositoryImpl
    ): DegreeRepository

    @Binds
    @Singleton
    abstract fun bindInterestRepository(
        interestRepositoryImpl: InterestRepositoryImpl
    ): InterestRepositoryImpl
    // Add other repository bindings here as needed
}