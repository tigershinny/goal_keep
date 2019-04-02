package com.adidas.sports.goal.db


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class StringListConverter {

    var gson = Gson()

    @TypeConverter
    fun fromString(data: String?): List<String>? {

        if (data == null){
            return Collections.emptyList()
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)


    }

    @TypeConverter
    fun listToString(someObjects: List<String>?): String? {
        return gson.toJson(someObjects)
    }
}

class ScheduledCourseStateEntityConverter {

    @TypeConverter
    fun listToJson(value: List<GoalEntity?>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String?): List<GoalEntity?>? {
        return if (value.isNullOrEmpty()){
            null
        } else {
            val objects = Gson().fromJson(value, Array<GoalEntity?>::class.java)
            objects?.toList()
        }

    }
}