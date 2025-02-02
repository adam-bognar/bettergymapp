package project.bettergymapp.data.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import project.bettergymapp.data.User

class MockAccountService : AccountService {
    override val currentUser: Flow<User?>
        get() = flowOf(null)
    override val currentUserId: String
        get() = ""
    override fun hasUser(): Boolean = false
    override suspend fun signIn(email: String, password: String) {
        // Mock sign-in logic
    }
    override suspend fun signUp(email: String, password: String) {
        // Mock sign-up logic
    }
    override suspend fun signOut() {
        // Mock sign-out logic
    }
    override suspend fun deleteAccount() {
        // Mock delete account logic
    }
}