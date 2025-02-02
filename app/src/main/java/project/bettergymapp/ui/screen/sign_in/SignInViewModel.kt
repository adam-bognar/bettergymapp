package project.bettergymapp.ui.screen.sign_in

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import project.bettergymapp.MainActivity
import project.bettergymapp.SIGN_UP_SCREEN
import project.bettergymapp.SPLASH_SCREEN
import project.bettergymapp.data.service.AccountService
import project.bettergymapp.data.viewmodel.AppViewModel
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val accountService: AccountService,
) : AppViewModel() {
   val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(email: String) {
        this.email.value = email
    }

    fun updatePassword(password: String) {
        this.password.value = password
    }

    fun onSignInClick(openAndPopUp: (String) -> Unit) {
        launchCatching {
            accountService.signIn(email.value, password.value)
            openAndPopUp(SPLASH_SCREEN)
        }
    }

    fun onSignUpClick(openAndPopUp: (String) -> Unit) {
        openAndPopUp(SIGN_UP_SCREEN)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SignInViewModel(MainActivity.accountService)
            }
        }
    }


}