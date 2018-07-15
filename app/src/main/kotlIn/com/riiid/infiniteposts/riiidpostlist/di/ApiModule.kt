package com.riiid.infiniteposts.riiidpostlist.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.riiid.infiniteposts.riiidpostlist.data.PostApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    private val baseUrl = "https://jsonplaceholder.typicode.com/"


    @Provides
    fun provideGson(): Gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create()

    @Provides
    fun provideRxAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor)
    = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        interceptors().add(httpLoggingInterceptor)
    }

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }


    @Provides
    fun provideRetrofit(gson: Gson, rxAdapter: RxJava2CallAdapterFactory, okHttpClientBuilder: OkHttpClient.Builder): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxAdapter)
            .baseUrl(baseUrl)
            .client(okHttpClientBuilder.build())
            .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)

}