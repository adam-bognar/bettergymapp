package project.bettergymapp.data.repository.User

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.Routine
import project.bettergymapp.data.Session
import project.bettergymapp.data.User

class UserRepositoryImpl : IUserRepository {

    private val db = Firebase.firestore
    private val user = Firebase.auth.currentUser?.uid.toString()
    private val docRef = db.collection("users").document(user)

    private val _userFlow = MutableStateFlow<User?>(null)

    init {
        fetchUserFromFirestore()
    }

    private fun fetchUserFromFirestore() {
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                val user = document.toObject(User::class.java)
                _userFlow.value = user
            } else {
                // Handle error
            }
        }.addOnFailureListener { exception ->
            // Handle error
        }
    }

    override suspend fun getExercises(): MutableStateFlow<List<Exercise>> {
        return _userFlow.value?.exercises?.let { MutableStateFlow(it) } ?: MutableStateFlow(emptyList())
    }

    override suspend fun setExercises(exercises: List<Exercise>) {
        val user = _userFlow.value?.copy(exercises = exercises)
        _userFlow.value = user
    }

    override suspend fun getRoutines(): MutableStateFlow<List<Routine>> {

        return _userFlow.value?.routines?.let { MutableStateFlow(it) } ?: MutableStateFlow(emptyList())
    }

    override suspend fun setRoutines(routines: List<Routine>) {
        val user = _userFlow.value?.copy(routines = routines)
        _userFlow.value = user
    }

    override suspend fun getSessions(): MutableStateFlow<List<Session>> {
        return _userFlow.value?.sessions?.let { MutableStateFlow(it) } ?: MutableStateFlow(emptyList())
    }

    override suspend fun setSessions(sessions: List<Session>) {
        val user = _userFlow.value?.copy(sessions = sessions)
        _userFlow.value = user
    }

    override suspend fun save() {

        val user = _userFlow.value
        val data = hashMapOf(
            "exercises" to user?.exercises,
            "routines" to user?.routines,
            "sessions" to user?.sessions
        )
        docRef.set(data)
    }


}