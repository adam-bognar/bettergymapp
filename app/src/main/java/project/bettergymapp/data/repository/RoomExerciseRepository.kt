package project.bettergymapp.data.repository

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.dao.ExerciseDao

class RoomExerciseRepository(private val dao: ExerciseDao): IExerciseRepository {
    override fun getAllExercises(): Flow<List<Exercise>> = dao.getAllExercises()

    override suspend fun insert(exercise: Exercise) {
        dao.insert(exercise)
    }

    override suspend fun delete(exercise: Exercise) {
        dao.delete(exercise)
    }

    override suspend fun update(exercise: Exercise) {
        dao.update(exercise)
    }

    override suspend fun save() {
        TODO("Not yet implemented")
    }
}