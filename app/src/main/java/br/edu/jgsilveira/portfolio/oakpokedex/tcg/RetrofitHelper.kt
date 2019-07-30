package br.edu.jgsilveira.portfolio.oakpokedex.tcg

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    val INSTANCE: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.pokemontcg.io/v1/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .build()
    }

    inline fun <reified T> service(): Lazy<T> = lazy {
        INSTANCE.create(T::class.java)
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    private fun createGson() = GsonBuilder().setDateFormat("MM/dd/yyyy").create()

}