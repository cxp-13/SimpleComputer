package com.example.simplecomputer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplecomputer.dao.OperationDao

@Database(entities = [OperationDataBase::class], version = 1)
abstract class OperationDataBase : RoomDatabase() {

    abstract fun getOperationDao(): OperationDao


    companion object {
        private lateinit var INSTANCE: OperationDataBase
        fun getInstance(context: Context): OperationDataBase {

            if (INSTANCE == null) {
                INSTANCE = synchronized(this) {
                    Room.databaseBuilder(
                        context = context.applicationContext,
                        OperationDataBase::class.java,
                        "test"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

}