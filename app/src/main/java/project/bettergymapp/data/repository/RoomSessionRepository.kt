package project.bettergymapp.data.repository

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Session
import project.bettergymapp.data.dao.SessionDao

class RoomSessionRepository(private val dao: SessionDao) : ISessionRepository {
    override fun getAllSessions(): Flow<List<Session>> {
       return dao.getAllSessions()
    }

    override suspend fun insert(session: Session) {
        dao.insert(session)
    }

    override suspend fun delete(session: Session) {
        dao.delete(session)
    }

    override suspend fun update(session: Session) {
        dao.update(session)
    }

    override suspend fun save() {
        TODO("Not yet implemented")
    }

}