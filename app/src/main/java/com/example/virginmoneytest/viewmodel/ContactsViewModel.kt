package com.example.virginmoneytest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.virginmoneytest.model.Contact
import com.example.virginmoneytest.network.Resource
import com.example.virginmoneytest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel  @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    var myContactsResponse = MutableLiveData<Resource<List<Contact>>>()
    val loading = MutableLiveData<Boolean>()


    fun getAllContacts() {
        Log.d("Thread Outside", Thread.currentThread().name)

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            myContactsResponse.postValue(Resource.loading(null))
            mainRepository.getContacts().let {
                if (it.isSuccessful){
                    myContactsResponse.postValue(Resource.success(it.body()))
                }else{
                    myContactsResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }
}
