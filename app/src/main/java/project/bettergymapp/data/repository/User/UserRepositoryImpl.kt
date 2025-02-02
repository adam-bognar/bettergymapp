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



}