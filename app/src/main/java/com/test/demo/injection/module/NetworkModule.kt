package com.test.demo.injection.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.test.demo.BuildConfig.BASE_URL
import com.test.demo.MainApplication
import com.test.demo.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideNetworkInterface(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)

    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideClientRetrofit(): Retrofit {

        val client = OkHttpClient.Builder().cache(cache()).addInterceptor(offlineInterceptor).addNetworkInterceptor(
            onlineInterceptor
        ).build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    fun hasNetwork(): Boolean {
        var isConnected = false
        val connectivityManager =
            MainApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    private fun cache(): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(MainApplication.getContext().cacheDir, cacheSize.toLong())
    }

    private var onlineInterceptor: Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    private var offlineInterceptor: Interceptor = Interceptor { chain ->
        var request = chain.request()
        if (!hasNetwork()) {
            val maxStale = 60 * 60 * 24 * 30
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        chain.proceed(request)
    }

}