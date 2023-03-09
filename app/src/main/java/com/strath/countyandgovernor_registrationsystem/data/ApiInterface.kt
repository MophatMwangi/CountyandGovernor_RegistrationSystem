package com.strath.countyandgovernor_registrationsystem.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/farmsData")
    suspend fun getData(): Response<FarmData>


}