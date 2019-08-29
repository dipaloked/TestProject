package dd.sample.testproject.model.service

import dd.sample.testproject.model.ServiceResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getServiceResponse(): Observable<ServiceResponse>

    companion object {
        fun create(): APIService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://dl.dropboxusercontent.com/")
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}