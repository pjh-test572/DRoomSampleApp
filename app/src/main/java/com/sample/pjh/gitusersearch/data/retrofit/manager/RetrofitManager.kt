package com.sample.pjh.gitusersearch.data.retrofit.manager

import android.app.Application
import com.sample.pjh.gitusersearch.common.BaseApplication
import com.sample.pjh.gitusersearch.common.type.ServerType
import com.sample.pjh.gitusersearch.common.util.CustomLog
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitManager() {

    // -------- LOCAL VALUE --------
    private var mManager: Retrofit? = null
    private var mCurrentType: ServerType? = null
    // -----------------------------

    ////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////

    constructor (type: ServerType) : this() {
        mCurrentType = type
        mManager = getRetrofit(getClient(
            getCache(BaseApplication.instance),
            getInterceptor(), CustomLog.flag))
    }


    ////////////////////////////////////////////////
    // PUBLIC
    ////////////////////////////////////////////////

    companion object {
        private var instance: RetrofitManager? = null

        @JvmStatic
        fun <S> createService(type: ServerType, service: Class<S>): S {
            if (instance == null) {
                instance = RetrofitManager(type)
            } else {
                if (type != instance!!.mCurrentType) {
                    instance = RetrofitManager(type)
                }
            }
            return instance!!.mManager!!.create(service)
        }


    }

    ////////////////////////////////////////////////
    // PRIVATE
    ////////////////////////////////////////////////

    private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mCurrentType!!.url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    /**
     * @author park jungho
     */
    // Client
    private fun getClient(cache: Cache, interceptor: Interceptor, isLogging: Boolean): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.cache(cache)
        builder.connectTimeout(40, TimeUnit.SECONDS)
        builder.addInterceptor(interceptor)
        if (isLogging) builder.addInterceptor(getLoggingInterceptor())
        return builder.build()
    }

    // Cache
    private fun getCache(application: Application): Cache {
        return Cache(application.cacheDir, (10 * 1024 * 1024).toLong()) // 10MB
    }

    // Interceptor
    private fun getInterceptor(): Interceptor {
        return Interceptor { chain ->
            val builder = chain.request().newBuilder()
            // Header
            //builder.header("X-RateLimit-Limit", "20")
            chain.proceed(builder.build())
        }
    }

    // Logging
    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


}