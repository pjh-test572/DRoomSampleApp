package com.sample.pjh.gitusersearch.data.db.dao

import androidx.room.*
import com.sample.pjh.gitusersearch.data.db.entity.GitUserEntity


@Dao
interface GitUserDao {

    @Query("SELECT * FROM git_user_data ORDER By g_score DESC")
    fun getAll() : List<GitUserEntity>

    @Query("SELECT * FROM git_user_data WHERE g_id == :id ORDER By g_id")
    fun get(id : Int) : List<GitUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity : GitUserEntity)

    @Query("DELETE from git_user_data")
    fun deleteAll()

    @Query("DELETE from git_user_data WHERE g_id == :id")
    fun delete(id : Int)


}