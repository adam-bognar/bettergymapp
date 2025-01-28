package project.bettergymapp.data.repository.Exercise

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import project.bettergymapp.data.Exercise

class MockExerciseRepository: IExerciseRepository {
    override fun getAllExercises(): Flow<List<Exercise>> {
        return flowOf(listOf(
            Exercise(name = "Push Up"),
            Exercise(name = "Squat")
        ))
    }

    override suspend fun upsert(item: Exercise) {

    }

    override suspend fun delete(item: Exercise) {

    }

}