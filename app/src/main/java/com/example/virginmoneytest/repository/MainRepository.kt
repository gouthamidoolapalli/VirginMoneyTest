package com.example.virginmoneytest.repository

import com.example.virginmoneytest.network.ApiHelper
import javax.inject.Inject

class MainRepository@Inject constructor(
    private val apiHelper: ApiHelper
){

    suspend fun getContacts() = apiHelper.getContacts()
    suspend fun getRooms()= apiHelper.getRooms()

}