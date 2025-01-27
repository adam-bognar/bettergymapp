package project.bettergymapp.data.repository.Routine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.Routine

class MockRoutineRepository : IRoutineRepository {
    override fun getAllRoutines(): Flow<List<Routine>> {
        return flowOf(listOf(
            Routine(name = "Sample Routine", description = "This is a sample routine", exercises = listOf(
                Exercise(name = "Push Up"),
                Exercise(name = "Squat")
            ))
        ))
    }

    override suspend fun highestId(): Int {
        return 1
    }

    override suspend fun upsert(item: Routine) {
        // Mock implementation
    }

    override suspend fun delete(item: Routine) {
        // Mock implementation
    }

    override suspend fun save() {
        // Mock implementation
    }
}