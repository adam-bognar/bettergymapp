package project.bettergymapp.data.repository

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Exercise

interface IExerciseRepository {
    fun getAllExercises(): Flow<List<Exercise>>
    suspend fun insert(exercise: Exercise)
    suspend fun delete(exercise: Exercise)
    suspend fun update(exercise: Exercise)
    suspend fun save()


}