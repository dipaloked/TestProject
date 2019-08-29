package dd.sample.testproject.model.repository

import dd.sample.testproject.model.ServiceResponse
import dd.sample.testproject.model.service.APIService
import io.reactivex.Observable

object ItemRepository {
    fun getItems(): Observable<ServiceResponse> {
        return APIService.create().getServiceResponse()
    }
}