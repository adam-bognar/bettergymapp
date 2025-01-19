package project.bettergymapp.data.repository

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Session
import project.bettergymapp.data.dao.SessionDao

class RoomSessionRepository(private val dao: SessionDao)  {
     fun getAllSessions(): Flow<List<Session>> {
       return dao.getAllSessions()
    }

     suspend fun insert(session: Session) {
        dao.insert(session)
    }

     suspend fun delete(session: Session) {
        dao.delete(session)
    }

     suspend fun update(session: Session) {
        dao.update(session)
    }

     suspend fun save() {
        TODO("Not yet implemented")
    }

}