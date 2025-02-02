package project.bettergymapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun fromMap(map: Map<Int, Int>): String {
        return Gson().toJson(map)
    }

    @TypeConverter
    fun toMap(value: String): Map<Int, Int> {
        val mapType = object : TypeToken<Map<Int, Int>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromExerciseList(exercises: List<Exercise>): String {
        return Gson().toJson(exercises)
    }

    @TypeConverter
    fun toExerciseList(value: String): List<Exercise> {
        val listType = object : TypeToken<List<Exercise>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromExerciseLogList(logs: List<ExerciseLog>): String {
        return Gson().toJson(logs)
    }

    @TypeConverter
    fun toExerciseLogList(value: String): List<ExerciseLog> {
        val listType = object : TypeToken<List<ExerciseLog>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromLocalDate(logs: LocalDate): String {
        return Gson().toJson(logs)
    }

    @TypeConverter
    fun toLocalDate(value: String): LocalDate {
        val listType = object : TypeToken<LocalDate>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromLog(logs: ExerciseLog): String {
        return Gson().toJson(logs)
    }

    @TypeConverter
    fun toLog(value: String): ExerciseLog {
        val listType = object : TypeToken<ExerciseLog>() {}.type
        return Gson().fromJson(value, listType)
    }








}