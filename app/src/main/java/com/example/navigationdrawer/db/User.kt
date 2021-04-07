package com.example.navigationdrawer.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey(autoGenerate = true) val id: Int?,
        @ColumnInfo(name = "username") val username: String?,
        @ColumnInfo(name = "name") val name: String?,
        @ColumnInfo(name = "email") val email: String?,
        @ColumnInfo(name = "password") val password: String?,
        @ColumnInfo(name = "mobile") val mobile: String?
) {
    constructor(username: String, name: String?, email: String?, password: String?, mobile: String?): this(null, username, name, email, password, mobile)
}