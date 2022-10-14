package com.example.virginmoneytest.network

import com.example.virginmoneytest.model.Contact
import com.example.virginmoneytest.model.Room
import retrofit2.Response

interface ApiHelper {
    suspend fun getContacts():Response<List<Contact>>
    suspend fun getRooms(): Response<List<Room>>
}
