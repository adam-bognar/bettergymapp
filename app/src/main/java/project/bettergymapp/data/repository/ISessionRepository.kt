package project.bettergymapp.data.repository

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Session

interface ISessionRepository {

    fun getAllSessions(): Flow<List<Session>>
    suspend fun upsert(session: Session)
    suspend fun delete(session: Session)
    suspend fun highestId(): Int
}