package com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ch4k4uw.oauthdddlogin.infrastructure.R
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.dao.UserDao
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val userDao: UserDao
    companion object {
        @Volatile
        private var mInstance: AppDatabase? = null
        private var mLock = Object()
        fun initDatabase(context: Context): AppDatabase {
            synchronized(mLock) {
                if (mInstance == null) {
                    val builder = Room
                            .databaseBuilder(context, AppDatabase::class.java, "app")
                    if (context.resources.getBoolean(R.bool.enable_db_mainthread_access)) {
                        builder.allowMainThreadQueries()
                    }

                    mInstance = builder.build()

                }
                return mInstance!!
            }
        }

        val Instance: AppDatabase get() {
            synchronized(mLock) {
                if (mInstance == null) {
                    throw RuntimeException("Need to be started")
                }
                return mInstance!!
            }
        }

    }

    override fun close() {
        synchronized(mLock) {
            super.close()
            mInstance = null
        }
    }
}