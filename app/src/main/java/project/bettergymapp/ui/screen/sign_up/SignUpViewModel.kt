package project.bettergymapp.ui.screen.sign_up

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import project.bettergymapp.MainActivity
import project.bettergymapp.SIGN_IN_SCREEN
import project.bettergymapp.SPLASH_SCREEN
import project.bettergymapp.data.service.AccountService
import project.bettergymapp.data.viewmodel.AppViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
) : AppViewModel() {
   val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    fun updateEmail(email: String) {
        this.email.value = email
    }

    fun updatePassword(password: String) {
        this.password.value = password
    }

    fun updateConfirmPassword(confirmPassword: String) {
        this.confirmPassword.value = confirmPassword
    }

    private fun passwordsMatch(): Boolean {
        return password.value == confirmPassword.value
    }

    fun onSignInClick(openAndPopUp: (String) -> Unit) {
        launchCatching {

            openAndPopUp(SIGN_IN_SCREEN)
        }
    }

    fun onSignUpClick(openAndPopUp: (String) -> Unit) {
        if (!passwordsMatch()) {
            //TODO
            return
        }
        launchCatching {
            accountService.signUp(email.value, password.value)

            openAndPopUp(SPLASH_SCREEN)

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SignUpViewModel(MainActivity.accountService)
            }
        }
    }


}