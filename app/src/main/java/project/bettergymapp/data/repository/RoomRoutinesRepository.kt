package project.bettergymapp.data.repository

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Routine
import project.bettergymapp.data.dao.RoutineDao
import project.bettergymapp.data.repository.Routine.IRoutineRepository

class RoomRoutinesRepository(private val dao: RoutineDao) : IRoutineRepository {


    override fun getAllRoutines(): Flow<List<Routine>> = dao.getAllRoutines()

    override suspend fun upsert(routine: Routine) {
        dao.insert(routine)
    }

    override suspend fun delete(routine: Routine) {
        dao.delete(routine)
    }

    override suspend fun highestId(): Int {
        TODO("Not yet implemented")
    }


    override suspend fun save() {
        TODO("Not yet implemented")
    }
}