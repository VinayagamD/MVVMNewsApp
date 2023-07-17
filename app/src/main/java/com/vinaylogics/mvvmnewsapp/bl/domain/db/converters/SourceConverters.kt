package com.vinaylogics.mvvmnewsapp.bl.domain.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.vinaylogics.mvvmnewsapp.bl.domain.models.Source

object SourceConverters {

    private val GSON = Gson()

    @TypeConverter
    fun fromSource(source: Source): String {
        return GSON.toJson(source)
    }

    @TypeConverter
    fun toSource(data: String): Source {
        return GSON.fromJson(data, Source::class.java)
    }
}