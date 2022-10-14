package com.example.virginmoneytest.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virginmoneytest.ActivityContactsBinding
import com.example.virginmoneytest.R
import com.example.virginmoneytest.databinding.ActivityRoomsBinding
import com.example.virginmoneytest.network.Status
import com.example.virginmoneytest.view.adapters.ContactsAdapter
import com.example.virginmoneytest.view.adapters.RoomsAdapter
import com.example.virginmoneytest.viewmodel.ContactsViewModel
import com.example.virginmoneytest.viewmodel.RoomsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomsBinding
    private lateinit var viewModel: RoomsViewModel
    private lateinit var adapter: RoomsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoomsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[RoomsViewModel::class.java]
        adapter = RoomsAdapter()
        binding.roomsList.layoutManager = LinearLayoutManager(this)
        binding.roomsList.adapter = adapter


        viewModel.myRoomsResponse.observe(this) {
            println(it.toString())
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.roomsList.visibility = View.VISIBLE
                    it.data?.let { it1 -> adapter.submitList(it1) }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.roomsList.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.roomsList.visibility = View.VISIBLE
                }
            }
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.getAllRooms()


    }

}