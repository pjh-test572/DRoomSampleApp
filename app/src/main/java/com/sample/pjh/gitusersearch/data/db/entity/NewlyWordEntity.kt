package com.sample.pjh.gitusersearch.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * 최근 입력 단어
 */
@Entity(tableName = "newly_word_table")
data class NewlyWordEntity(
    @PrimaryKey(autoGenerate = true)            val idx: Int,
    @ColumnInfo(name = "searchWord")            var searchWord : String,
    @ColumnInfo(name = "totalCount")            var totalCount : Int,
    @ColumnInfo(name = "millisecond")           var millisecond : Long = 0L) : Serializable