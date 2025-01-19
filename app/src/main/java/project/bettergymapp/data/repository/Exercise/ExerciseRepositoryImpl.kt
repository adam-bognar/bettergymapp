package project.bettergymapp.data.repository.Exercise

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.repository.IExerciseRepository

class ExerciseRepositoryImpl(
    db: FirebaseFirestore, user: String
): IExerciseRepository {

    private val collection = db.collection("users").document(user).collection("exercises")

    private val _exercisesFlow = MutableStateFlow<List<Exercise>>(emptyList())

    init {
        // Listen to Firestore updates and keep the flow updated
        collection.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                // Handle exception (log it or propagate)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val exercises = snapshot.documents.mapNotNull { it.toObject(Exercise::class.java) }
                _exercisesFlow.value = exercises
            }
        }
    }

    override fun getAllExercises(): Flow<List<Exercise>> = _exercisesFlow.asStateFlow()

    override suspend fun upsert(exercise: Exercise) {
        collection.document(exercise.id.toString()).set(exercise)
    }

    override suspend fun delete(exercise: Exercise) {
        val routineDoc = collection.document(exercise.id.toString())
        routineDoc.delete()
    }




}