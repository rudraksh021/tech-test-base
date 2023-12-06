package com.cleanarch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Aanal Shah on 05/12/2023
 */
@Entity(tableName = "movies_remote_keys")
data class MovieRemoteKeyDbData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?
)