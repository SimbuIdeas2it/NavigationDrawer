package com.example.navigationdrawer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteDatabaseHook
import net.sqlcipher.database.SupportFactory

@Database(entities = [User::class], version = 1)
abstract class UserDataBase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDataBase? = null

        @Synchronized
        fun getInstance(ctx: Context): UserDataBase {
            if (instance == null) {
                val passphrase: ByteArray =
                        SQLiteDatabase.getBytes("P@s5P4ras3VeryL0n9".toCharArray())

                val builder = Room.databaseBuilder(
                        ctx.applicationContext,
                        UserDataBase::class.java, "user.db"
                )
                val factory = SupportFactory(passphrase, object : SQLiteDatabaseHook {
                    override fun preKey(database: SQLiteDatabase?) = Unit

                    override fun postKey(database: SQLiteDatabase?) {
                        database?.rawExecSQL("PRAGMA cipher_memory_security = OFF")
                    }
                })
                builder.openHelperFactory(factory)
                instance = builder.fallbackToDestructiveMigration().addCallback(roomCallback).build()
//                instance =
//                    Room.databaseBuilder(ctx.applicationContext, UserDataBase::class.java, "user")
//                        .fallbackToDestructiveMigration()
//                        .addCallback(roomCallback)
//                        .build()



            }
            return instance!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(
                    instance!!
                )
            }
        }

        private fun populateDatabase(db: UserDataBase) {

        }
    }
}