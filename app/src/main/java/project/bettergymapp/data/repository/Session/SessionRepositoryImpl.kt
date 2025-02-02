package project.bettergymapp.data.repository.Routine

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import project.bettergymapp.data.Session
import project.bettergymapp.data.repository.ISessionRepository

class SessionRepositoryImpl(
    db: FirebaseFirestore, user: String
) : ISessionRepository {

    private val collection = db.collection("users").document(user).collection("sessions")

    private val _sessionsFlow = MutableStateFlow<List<Session>>(emptyList())

    init {
        // Listen to Firestore updates and keep the flow updated
        collection.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                // Handle exception (log it or propagate)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val sessions = snapshot.documents.mapNotNull { it.toObject(Session::class.java) }
                _sessionsFlow.value = sessions
            }
        }
    }

    override fun getAllSessions(): Flow<List<Session>> = _sessionsFlow.asStateFlow()

    override suspend fun upsert(session: Session) {
        collection.document(session.id.toString()).set(session)
    }

    override suspend fun delete(session: Session) {
        val routineDoc = collection.document(session.id.toString())
        routineDoc.delete()
    }

    override suspend fun highestId(): Int {
        return _sessionsFlow.value.maxOfOrNull { it.id } ?: 0
    }

}