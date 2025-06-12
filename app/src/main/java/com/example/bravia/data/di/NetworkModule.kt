package com.example.bravia.data.di

import com.example.bravia.data.remote.api.BusinessService
import com.example.bravia.data.local.AuthPreferences
import com.example.bravia.data.remote.api.AdminService
import com.example.bravia.data.remote.api.AuthService
import com.example.bravia.data.remote.api.CVService
import com.example.bravia.data.remote.api.InternshipService
import com.example.bravia.data.remote.api.SignUpService
import com.example.bravia.data.remote.api.StudentService
import com.example.bravia.data.remote.dto.InterestDTO
import com.example.bravia.data.remote.dto.InternshipDTO
import com.example.bravia.data.remote.interceptor.AuthInterceptor
import com.example.bravia.data.remote.serializer.InterestDeserializer
import com.example.bravia.data.remote.serializer.InternshipDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
//    val BASE_URL = "http://192.168.100.96:8080/api/v1/"
//
    private const val BASE_URL = "https://bravia-app-v01-bbd26053b419.herokuapp.com/api/v1/"
    private const val DATE_FORMAT = "yyyy-MM-dd"

    /**
     * Provides a singleton Gson instance configured with custom type adapters.
     *
     * @return Configured [Gson] instance
     */
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setDateFormat(DATE_FORMAT)
        .registerTypeAdapter(InternshipDTO::class.java, InternshipDeserializer())
        .registerTypeAdapter(InterestDTO::class.java, InterestDeserializer())
        .create()
    //TODO meter esto .registerTypeAdapter(InterestDTO::class.java, InterestDeserializer()) a ver si funciona


    /**
     * Provides the auth interceptor for adding authentication headers to requests.
     *
     * @param authPreferences The preferences storing authentication data
     * @return Configured [AuthInterceptor]
     */
    @Provides
    @Singleton
    fun provideAuthInterceptor(authPreferences: AuthPreferences): AuthInterceptor =
        AuthInterceptor(authPreferences)


    /**
     * Provides a logging interceptor for HTTP request/response logging.
     *
     * @return Configured [HttpLoggingInterceptor]
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }


    /**
     * Provides a configured OkHttpClient with interceptors.
     *
     * @param loggingInterceptor The logging interceptor to be added to the client
     * @return Configured [OkHttpClient]
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provides a configured Retrofit instance.
     *
     * @param okHttpClient The HTTP client to use
     * @param gson The Gson instance for JSON conversion
     * @return Configured [Retrofit] instance
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /**
     * Provides the SignUpService implementation.
     *
     * @param retrofit The Retrofit instance to create the service
     * @return [SignUpService] implementation
     */
    @Provides
    @Singleton
    fun provideSignUpService(retrofit: Retrofit): SignUpService =
        retrofit.create(SignUpService::class.java)

    @Provides
    @Singleton
    fun provideStudentAreaService(retrofit: Retrofit): StudentService =
        retrofit.create(StudentService::class.java)

    /**
     * Provides the AuthService implementation for authentication operations.
     *
     * @param retrofit The Retrofit instance
     * @return Implementation of [AuthService]
     */
    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    /**
     * Provides the BusinessService implementation.
     *
     * @param retrofit The Retrofit instance to create the service
     * @return [BusinessService] implementation
     */
    @Provides
    @Singleton
    fun provideBusinessService(retrofit: Retrofit): BusinessService =
        retrofit.create(BusinessService::class.java)

    /**
     * Provides the InternshipService implementation for internship-related operations.
     *
     * @param retrofit The Retrofit instance to create the service
     * @return [InternshipService] implementation
     */
    @Provides
    @Singleton
    fun provideInternshipService(retrofit: Retrofit): InternshipService =
        retrofit.create(InternshipService::class.java)

    @Provides
    @Singleton
    fun provideAdminService(retrofit: Retrofit): AdminService =
        retrofit.create(AdminService::class.java)


    @Provides
    @Singleton
    fun provideCVService(retrofit: Retrofit): CVService =
        retrofit.create(CVService::class.java)


}