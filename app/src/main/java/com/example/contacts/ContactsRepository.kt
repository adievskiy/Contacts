package com.example.contacts

import androidx.lifecycle.LiveData

class ContactsRepository(private val contactsDao: ContactsDao) {
    val contacts: LiveData<List<Contacts>> = contactsDao.getAllContacts()

    suspend fun addContacts(contacts: Contacts) {
        contactsDao.addContact(contacts)
    }

    suspend fun delContact(contacts: Contacts) {
        contactsDao.delContact(contacts)
    }
}
