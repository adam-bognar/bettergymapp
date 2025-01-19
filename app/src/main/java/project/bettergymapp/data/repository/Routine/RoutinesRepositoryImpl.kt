package project.bettergymapp.data.repository.Routine

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import project.bettergymapp.data.Routine
import project.bettergymapp.data.repository.IRoutineRepository

class RoutinesRepositoryImpl(
    db: FirebaseFirestore, user: String
) : IRoutineRepository {

    private val collection = db.collection("users").document(user).collection("routines")

    private val _routinesFlow = MutableStateFlow<List<Routine>>(emptyList())

    init {
        // Listen to Firestore updates and keep the flow updated
        collection.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                // Handle exception (log it or propagate)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val routines = snapshot.documents.mapNotNull { it.toObject(Routine::class.java) }
                _routinesFlow.value = routines
            }
        }
    }

    override fun getAllRoutines(): Flow<List<Routine>> = _routinesFlow.asStateFlow()

    override suspend fun upsert(routine: Routine) {
        collection.document(routine.id.toString()).set(routine)
    }

    override suspend fun delete(routine: Routine) {
        val routineDoc = collection.document(routine.id.toString())
        routineDoc.delete()
    }

    override suspend fun highestId(): Int {
        return _routinesFlow.value.maxOfOrNull { it.id } ?: 0
    }

    override suspend fun deleteAll() {
        collection.get().addOnSuccessListener { snapshot ->
            for (document in snapshot.documents) {
                document.reference.delete()
            }
            _routinesFlow.value = emptyList()
        }.addOnFailureListener { exception ->
            // Handle exception
        }
    }

    override suspend fun save() {
        val routines = _routinesFlow.value
        for (routine in routines) {
            val routineDoc = collection.document(routine.id.toString())
            routineDoc.set(routine).addOnFailureListener { exception ->
                // Handle exception
            }
        }
    }
}