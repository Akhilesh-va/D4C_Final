package com.example.d4cfinalassignment.di

import android.content.Context
import com.example.d4cfinalassignment.data.api.OtpAPI
import com.example.d4cfinalassignment.utils.Const.BASE_URl
import com.example.d4cfinalassignment.utils.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URl)
            .build()

    }
    @Singleton
    @Provides
    fun providesOtpAPI(retrofit: Retrofit): OtpAPI {
        return retrofit.create(OtpAPI::class.java)
    }
    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext appContext: Context): TokenManager {
        return TokenManager(appContext)
    }


}