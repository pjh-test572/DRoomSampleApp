package com.sample.pjh.gitusersearch.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.pjh.gitusersearch.data.db.dao.GitUserDao
import com.sample.pjh.gitusersearch.data.db.dao.NewlyWordDao
import com.sample.pjh.gitusersearch.data.db.entity.GitUserEntity
import com.sample.pjh.gitusersearch.data.db.entity.NewlyWordEntity


@Database(entities = arrayOf(GitUserEntity::class,NewlyWordEntity::class), version = 1, exportSchema = false)
abstract class Db : RoomDatabase(){

    abstract fun gitUserDao() : GitUserDao
    abstract fun newlyWordDao() : NewlyWordDao

    companion object {
        private var INSTANCE : Db? = null

        fun getInstance(context : Context) : Db?{
            if(INSTANCE == null){
                synchronized(Db::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        Db::class.java, "TEST_DB")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}