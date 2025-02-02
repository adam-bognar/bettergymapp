package project.bettergymapp.data.repository.Session

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import project.bettergymapp.data.Session
import project.bettergymapp.data.repository.ISessionRepository

class MockSessionRepository : ISessionRepository {
    override fun getAllSessions(): Flow<List<Session>> {
        return flowOf(listOf(
            Session()
        ))
    }

    override suspend fun upsert(session: Session) {
    }

    override suspend fun delete(session: Session) {
    }

    override suspend fun highestId(): Int {
        return 0
    }

}