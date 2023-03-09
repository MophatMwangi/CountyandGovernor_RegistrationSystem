package com.strath.countyandgovernor_registrationsystem.hilt

/*import com.google.gson.GsonBuilder
import com.strath.countyandgovernor_registrationsystem.MainActivity
import com.strath.countyandgovernor_registrationsystem.data.ApiInterface
import com.strath.countyandgovernor_registrationsystem.data.RetrofitInstance
import com.strath.countyandgovernor_registrationsystem.recview.EdFarm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofi(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://edfafarmer.appspot.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    suspend fun getAllProductsService(retrofit: Retrofit): ApiInterface{
     return retrofit.create(ApiInterface::class.java)
    }
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient{
        val duration = Duration.ofSeconds(30)
        return OkHttpClient.Builder()
            .connectTimeout(duration)
            .readTimeout(duration)
            .writeTimeout(duration)
            .build()
    }

}*/