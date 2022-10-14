package com.example.virginmoneytest.network

import com.example.virginmoneytest.model.Contact
import com.example.virginmoneytest.model.Room
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("people")
    suspend fun getContacts(): Response<List<Contact>>

    @GET("rooms")
    suspend fun getRooms(): Response<List<Room>>

}
