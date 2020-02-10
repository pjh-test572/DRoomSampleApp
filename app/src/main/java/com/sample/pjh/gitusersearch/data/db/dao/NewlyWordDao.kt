package com.sample.pjh.gitusersearch.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.pjh.gitusersearch.data.db.entity.NewlyWordEntity


/**
 * 최근 입력 단어
 */
@Dao
interface NewlyWordDao {

    @Query("SELECT * FROM newly_word_table ORDER By millisecond DESC")
    fun getAll() : List<NewlyWordEntity>

    @Query("SELECT * FROM newly_word_table WHERE searchWord == :searchWord ORDER By searchWord")
    fun get(searchWord : String) : List<NewlyWordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity : NewlyWordEntity)

    @Query("DELETE from newly_word_table")
    fun deleteAll()

    @Query("DELETE from newly_word_table WHERE searchWord == :word")
    fun delete(word : String)


}