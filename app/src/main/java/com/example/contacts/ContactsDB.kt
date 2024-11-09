package com.example.contacts

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contacts::class], version = 2, exportSchema = false)
abstract class ContactsDB : RoomDatabase() {
    abstract fun getContactsDao(): ContactsDao

    companion object {
        private var INSTANCE: ContactsDB? = null
        fun getDatabase(context: Context): ContactsDB {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactsDB::class.java,
                    "contacts_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}