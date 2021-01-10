package go.easy.skel.api

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Downloader
import com.squareup.picasso.OkHttp3Downloader
import go.easy.skel.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.ParameterizedType
import java.util.concurrent.TimeUnit

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for easy_go_skel on 03.04.20 0:55.
 */
abstract class ApiClient<T>(
  private val context: Context
) {

  companion object {
    private const val CONNECTION_TIMEOUT_VALUE = 10L
    private val CONNECTION_TIMEOUT_UNIT = TimeUnit.SECONDS
  }

  private val httpCache: Cache by lazy {
    Cache(context.externalCacheDir ?: context.cacheDir, Long.MAX_VALUE)
  }

  protected open val interceptors: List<Interceptor>? = null
  protected open val networkInterceptors: List<Interceptor>? = null

  private val httpClient: OkHttpClient by lazy {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
      level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
      } else {
        HttpLoggingInterceptor.Level.NONE
      }
    }

    val client = OkHttpClient.Builder()
      .addNetworkInterceptor(loggingInterceptor)
      .apply {
        interceptors?.forEach {
          addInterceptor(it)
        }
        networkInterceptors?.forEach {
          addNetworkInterceptor(it)
        }
      }
      .connectTimeout(CONNECTION_TIMEOUT_VALUE, CONNECTION_TIMEOUT_UNIT)
      .build()

    client
  }

  val picassoDownloader: Downloader by lazy {
    OkHttp3Downloader(httpClient)
  }

  @Suppress("UNCHECKED_CAST")
  private val typeOfT: Class<T> =
    ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>)

  protected open val gson: Gson
    get() = GsonBuilder()
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .setLenient()
      .create()

  protected abstract val apiDevelopServiceUrl: String
  protected abstract  val apiProductionServiceUrl: String

  val apiService: T by lazy {
    val apiServiceUrl = if (BuildConfig.DEBUG) {
      apiDevelopServiceUrl
    } else {
      apiProductionServiceUrl
    }

    Retrofit.Builder()
      .client(httpClient)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .baseUrl(apiServiceUrl)
      .build()
      .create(typeOfT)
  }
}
