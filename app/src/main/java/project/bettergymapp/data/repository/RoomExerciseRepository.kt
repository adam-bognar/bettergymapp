package project.bettergymapp.data.repository

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.dao.ExerciseDao

class RoomExerciseRepository(private val dao: ExerciseDao) {
     fun getAllExercises(): Flow<List<Exercise>> = dao.getAllExercises()

     suspend fun insert(exercise: Exercise) {
        dao.insert(exercise)
    }

     suspend fun delete(exercise: Exercise) {
        dao.delete(exercise)
    }

     suspend fun update(exercise: Exercise) {
        dao.update(exercise)
    }

     suspend fun save() {
        TODO("Not yet implemented")
    }
}