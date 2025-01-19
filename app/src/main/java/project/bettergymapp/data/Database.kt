package project.bettergymapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import project.bettergymapp.data.dao.ExerciseDao
import project.bettergymapp.data.dao.RoutineDao

@Database(
    entities = [Routine::class, Exercise::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun routineDao(): RoutineDao
}