package com.example.contacts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class MainActivity : AppCompatActivity(), ContactsAdapter.ContactClickListener {

    private lateinit var viewModel: ContactsViewModel
    private lateinit var nameET: EditText
    private lateinit var phoneET: EditText
    private lateinit var saveBTN: Button
    private lateinit var recyclerRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        recyclerRV.layoutManager = LinearLayoutManager(this)
        val adapter = ContactsAdapter(this, this)
        recyclerRV.adapter = adapter


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ContactsViewModel::class.java]

        viewModel.contacts.observe(this, Observer { list ->
            list?.let { adapter.updateList(it) }
        })
    }

    override fun onItemClick(contacts: Contacts) {
        viewModel.deleteContact(contacts)
    }

    fun saveData(view: View) {
        val contactName = nameET.text.toString()
        val contactPhone = phoneET.text.toString()
        val contactDate = formatDate(Date().time)
        if (contactName.isNotEmpty()) {
            viewModel.addContact(Contacts(contactName, contactPhone, contactDate))
            nameET.text.clear()
            phoneET.text.clear()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(time: Long): String {
        val dateFormat = SimpleDateFormat("MM.dd.yyyy\n hh:mm")
        dateFormat.timeZone = TimeZone.getTimeZone("GMT +04")
        return dateFormat.format(Date(time))
    }

    private fun init() {
        nameET = findViewById(R.id.nameET)
        phoneET = findViewById(R.id.phoneET)
        saveBTN = findViewById(R.id.saveBTN)
        recyclerRV = findViewById(R.id.recyclerRV)
    }
}