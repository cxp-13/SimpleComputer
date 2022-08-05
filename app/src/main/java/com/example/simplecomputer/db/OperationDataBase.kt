package com.example.simplecomputer.db

import android.content.Context
import androidx.room.*
import com.example.simplecomputer.dao.OperationDao
import com.example.simplecomputer.entity.OperationEntity

/**
 * @Author:cxp
 * @Date: 2022/8/4 10:27
 * @Description:数据库
 */

@Database(
    entities = [OperationEntity::class],
    version = 1,
//    autoMigrations = [AutoMigration(from = 1, to = 2)],
//    exportSchema=false
)
abstract class OperationDataBase : RoomDatabase() {
    //    对于与数据库关联的每个 DAO 类，数据库类必须定义一个具有零参数的抽象方法，并返回 DAO 类的实例。
    abstract fun getOperationDao(): OperationDao

    companion object {
        private var INSTANCE: OperationDataBase? = null

        //        获取数据库的唯一实例(懒汉式)
        fun  getInstance(context: Context): OperationDataBase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            OperationDataBase::class.java,
                            "testDb"
                        ).build()
                    return INSTANCE
                }
            }
            return INSTANCE
        }
    }
}