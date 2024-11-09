package com.example.contacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactsRepository
    val contacts: LiveData<List<Contacts>>

    init {
        val dao = ContactsDB.getDatabase(application).getContactsDao()
        repository = ContactsRepository(dao)
        contacts = repository.contacts
    }

    fun deleteContact(contacts: Contacts) = viewModelScope.launch(Dispatchers.IO) {
        repository.delContact(contacts)
    }

    fun addContact(contacts: Contacts) = viewModelScope.launch(Dispatchers.IO) {
        repository.addContacts(contacts)
    }
}