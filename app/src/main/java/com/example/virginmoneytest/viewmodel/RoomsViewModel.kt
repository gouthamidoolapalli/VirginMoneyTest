package com.example.virginmoneytest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.virginmoneytest.model.Contact
import com.example.virginmoneytest.model.Room
import com.example.virginmoneytest.network.Resource
import com.example.virginmoneytest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(private  val mainRepository: MainRepository): ViewModel() {

    var myRoomsResponse = MutableLiveData<Resource<List<Room>>>()
    val loading = MutableLiveData<Boolean>()

    fun getAllRooms() {
        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            myRoomsResponse.postValue(Resource.loading(null))
            mainRepository.getRooms().let {
                if (it.isSuccessful){
                    myRoomsResponse.postValue(Resource.success(it.body()))
                }else{
                    myRoomsResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }

}