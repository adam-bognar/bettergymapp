package project.bettergymapp.data.service

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.User


interface AccountService {
    val currentUser: Flow<User?>
    val currentUserId: String
    fun hasUser(): Boolean
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
}