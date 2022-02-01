package com.example.cattest.di

import com.example.cattest.data.remote.CatsApi
import com.example.cattest.data.remote.RemoteDataSource
import com.example.cattest.data.repository.RemoteRepositoryImpl
import com.example.cattest.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    fun provideRemoteRepository(dataSource: RemoteDataSource, api: CatsApi): RemoteRepository =
        RemoteRepositoryImpl(dataSource, api)

    @Provides
    fun providesRemoteDataSource(api: CatsApi) =
        RemoteDataSource(api)

    @Provides
    fun provideCatsApi(retrofit: Retrofit): CatsApi =
        retrofit.create(CatsApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        rxJavaAdapterFactory: CallAdapter.Factory,
        gsonConverterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .client(client)
            .addCallAdapterFactory(rxJavaAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        name = "x-api-key",
                        value = "923a919f-87ba-4845-8e1a-ee0b508654d2"
                    ).build()
                chain.proceed(request)
            })
            .build()
    }

    @Provides
    fun provideRxJava2CallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()
}