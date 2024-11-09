package com.example.contacts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contacts: Contacts)

    @Delete
    suspend fun delContact(contacts: Contacts)

    @Query("SELECT * FROM contacts_table ORDER BY id ASC")
    fun getAllContacts(): LiveData<List<Contacts>>

    @Query("DELETE FROM contacts_table")
    fun deleteBase()
}
