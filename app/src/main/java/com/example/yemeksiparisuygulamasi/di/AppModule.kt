package com.example.yemeksiparisuygulamasi.di

import com.example.yemeksiparisuygulamasi.data.datasource.YemeklerDataSource
import com.example.yemeksiparisuygulamasi.data.datasource.SepetYemeklerDataSource
import com.example.yemeksiparisuygulamasi.data.repo.YemeklerRepository
import com.example.yemeksiparisuygulamasi.retrofit.ApiUtils
import com.example.yemeksiparisuygulamasi.retrofit.SepetYemeklerDao
import com.example.yemeksiparisuygulamasi.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideYemeklerDataSource(ydao:YemeklerDao): YemeklerDataSource {
        return YemeklerDataSource(ydao)
    }

    @Provides
    @Singleton
    fun provideSepetYemeklerDataSource(sydao:SepetYemeklerDao): SepetYemeklerDataSource {
        return SepetYemeklerDataSource(sydao)
    }

    @Provides
    @Singleton
    fun provideYemeklerRepository(
        yds: YemeklerDataSource,
        syds: SepetYemeklerDataSource
    ): YemeklerRepository {
        return YemeklerRepository(yds, syds)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao(): YemeklerDao {
        return ApiUtils.getYemeklerDao()
    }

    @Provides
    @Singleton
    fun provideSepetYemeklerDao(): SepetYemeklerDao {
        return ApiUtils.getSepetYemeklerDao()
    }
}
