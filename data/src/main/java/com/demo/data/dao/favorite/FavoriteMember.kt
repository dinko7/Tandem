package com.demo.data.dao.favorite

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavoriteMember {
    @PrimaryKey
    @NonNull
    var id: String? = null
}