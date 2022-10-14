package com.example.virginmoneytest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.virginmoneytest.R
import com.example.virginmoneytest.databinding.ActivityMainBinding
import com.example.virginmoneytest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.contacts.setOnClickListener {
            val i = Intent(applicationContext, ContactsActivity::class.java)
            startActivity(i)
        }


        binding.rooms.setOnClickListener {
            val i = Intent(applicationContext, RoomsActivity::class.java)
            startActivity(i)
        }
    }
}