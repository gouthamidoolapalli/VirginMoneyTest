package com.example.virginmoneytest.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virginmoneytest.ActivityContactsBinding
import com.example.virginmoneytest.R
import com.example.virginmoneytest.network.Status
import com.example.virginmoneytest.view.adapters.ContactsAdapter
import com.example.virginmoneytest.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsActivity : AppCompatActivity() {
    lateinit var viewModel: ContactsViewModel
    lateinit var binding: ActivityContactsBinding
    lateinit var adapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts)
        viewModel = ViewModelProvider(this).get(ContactsViewModel::class.java)
        adapter = ContactsAdapter()
        binding.contactsList.layoutManager = LinearLayoutManager(this)
        binding.contactsList.adapter = adapter

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        viewModel.myContactsResponse.observe(this) {
            println(it.toString())
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.contactsList.visibility = View.VISIBLE
                    it.data?.let { it1 -> adapter.submitList(it1) }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.contactsList.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.contactsList.visibility = View.VISIBLE
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

        viewModel.getAllContacts()

    }
}