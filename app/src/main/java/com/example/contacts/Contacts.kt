package com.example.contacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("contacts_table")
data class Contacts(
    @ColumnInfo("name") var name: String,
    @ColumnInfo("phone") var phone: String,
    @ColumnInfo("date") var date: String,
    @ColumnInfo("address") var address: String,
) {
    @PrimaryKey(true)
    var id = 0
}