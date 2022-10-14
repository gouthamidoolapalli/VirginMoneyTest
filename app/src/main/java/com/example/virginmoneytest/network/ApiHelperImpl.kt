package com.example.virginmoneytest.network

import com.example.virginmoneytest.model.Contact
import com.example.virginmoneytest.model.Room
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl  @Inject constructor(
    private val apiService: ApiService
):ApiHelper{

    override suspend fun getContacts(): Response<List<Contact>> = apiService.getContacts()

    override suspend fun getRooms(): Response<List<Room>> = apiService.getRooms()


}
