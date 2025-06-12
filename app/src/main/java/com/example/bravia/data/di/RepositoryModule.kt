package com.example.bravia.data.di

import com.example.bravia.data.repository.AdminRepositoryImpl
import com.example.bravia.data.repository.BusinessAreaRepositoryImpl
import com.example.bravia.data.repository.CollegeRepositoryImpl
import com.example.bravia.data.repository.CompanyRepositoryImpl
import com.example.bravia.data.repository.DegreeRepositoryImpl
import com.example.bravia.data.repository.InterestRepositoryImpl
import com.example.bravia.data.repository.InternshipRepositoryImpl
import com.example.bravia.data.repository.LocationRepositoryImpl
import com.example.bravia.data.repository.ModalityRepositoryImpl
import com.example.bravia.domain.repository.AdminRepository
import com.example.bravia.domain.repository.BusinessAreaRepository
import com.example.bravia.domain.repository.CollegeRepository
import com.example.bravia.domain.repository.CompanyRepository
import com.example.bravia.domain.repository.DegreeRepository
import com.example.bravia.domain.repository.InterestRepository
import com.example.bravia.domain.repository.InternshipRepository
import com.example.bravia.domain.repository.LocationRepository
import com.example.bravia.domain.repository.ModalityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    //STUDENT AREA

    @Binds
    @Singleton
    abstract fun bindInternshipsRepository(
        internshipRepository: InternshipRepositoryImpl
    ): InternshipRepository



    /**
     * Binds the concrete implementation [BusinessAreaRepositoryImpl] to the [BusinessAreaRepository] interface.
     *
     * @param businessAreaRepositoryImpl The implementation instance to be provided when [BusinessAreaRepository] is requested
     * @return The bound [BusinessAreaRepository] interface
     */
    @Binds
    @Singleton
    abstract fun bindBusinessAreaRepository(
        businessAreaRepositoryImpl: BusinessAreaRepositoryImpl
    ): BusinessAreaRepository

    /**
     * Binds the concrete implementation [CollegeRepositoryImpl] to the [CollegeRepository] interface.
     *
     * @param collegeRepositoryImpl The implementation instance to be provided when [CollegeRepository] is requested
     * @return The bound [CollegeRepository] interface
     */
    @Binds
    @Singleton
    abstract fun bindCollegeRepository(
        collegeRepositoryImpl: CollegeRepositoryImpl
    ): CollegeRepository

    /**
     * Binds the concrete implementation [DegreeRepositoryImpl] to the [DegreeRepository] interface.
     *
     * @param degreeRepositoryImpl The implementation instance to be provided when [DegreeRepository] is requested
     * @return The bound [DegreeRepository] interface
     */
    @Binds
    @Singleton
    abstract fun bindDegreeRepository(
        degreeRepositoryImpl: DegreeRepositoryImpl
    ): DegreeRepository

    /**
     * Binds the concrete implementation [InterestRepositoryImpl] to the [InterestRepository] interface.
     *
     * @param interestRepositoryImpl The implementation instance to be provided when [InterestRepository] is requested
     * @return The bound [InterestRepository] interface
     */
    @Binds
    @Singleton
    abstract fun bindInterestRepository(
        interestRepositoryImpl: InterestRepositoryImpl
    ): InterestRepository


    @Binds
    @Singleton
    abstract fun bindCompanyRepository(
        companyRepositoryImpl: CompanyRepositoryImpl
    ): CompanyRepository

    @Binds
    @Singleton
    abstract fun bindLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository

    @Binds
    @Singleton
    abstract fun bindModalityRepository(
        modalityRepositoryImpl: ModalityRepositoryImpl
    ): ModalityRepository

    @Binds
    @Singleton
    abstract fun bindAdminRepository(
        adminRepositoryImpl: AdminRepositoryImpl
    ): AdminRepository

}