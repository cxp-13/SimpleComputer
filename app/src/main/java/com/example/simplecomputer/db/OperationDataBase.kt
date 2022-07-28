package com.example.simplecomputer.db

import android.content.Context
import androidx.room.*
import com.example.simplecomputer.dao.OperationDao
import com.example.simplecomputer.entity.OperationEntity

@Database(
    entities = [OperationEntity::class],
    version = 2,
//    autoMigrations = [AutoMigration(from = 1, to = 2)],
//    exportSchema=false
)
abstract class OperationDataBase : RoomDatabase() {

    abstract fun getOperationDao(): OperationDao

    companion object {
        private var INSTANCE: OperationDataBase? = null
        fun getInstance(context: Context): OperationDataBase {

            val tmpInstance = INSTANCE
            if (tmpInstance != null) {
                return tmpInstance
            }

            synchronized(this) {

                var instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        OperationDataBase::class.java,
                        "testDb"
                    ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance

            }

//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(
//                    context.applicationContext,
//                    OperationDataBase::class.java,
//                    "test"
//                ).build()
//            }
//            return INSTANCE as OperationDataBase
        }
    }

}